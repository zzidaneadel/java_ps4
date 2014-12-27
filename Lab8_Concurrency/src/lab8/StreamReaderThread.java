package lab8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

class StreamReaderThread implements Runnable 
{
    public Thread t;
    private final String threadName;
    private final FileInputStream threadInStream;
    private final Semaphore semaphore1;
    private final Semaphore semaphore2;
    private final Semaphore semaphore3;
    private final BlockingQueue queue;
   
    StreamReaderThread( String name, FileInputStream streamin, Semaphore sem1, Semaphore sem2, Semaphore sem3, BlockingQueue bq)
    {
        this.threadName = name;
        this.threadInStream = streamin;
        this.semaphore1 = sem1;
        this.semaphore2 = sem2;
        this.semaphore3 = sem3;
        this.queue = bq;
        System.out.println(threadName + ": creating thread");
    }
    
    @Override
    public void run() 
    {
        System.out.println(threadName + ": running thread");
        //start reading
        try 
        {
            String data;
            byte b = 2;
            //\1 - end of stream
            while (b != 1)
            {
                data = "";
                b = (byte) this.threadInStream.read();
                //\0 - end of message
                while ((b != 0)&&(b != 1))
                {
                    data += (char) b;
                    b = (byte) this.threadInStream.read();
                }
                
                if (data.length() != 0 )
                {
                    this.semaphore1.acquire();                    
                    while (!this.queue.offer(this.threadName+":"+data));                    
                    this.semaphore2.acquire();
                }
            }
            System.out.println(threadName + ": end of data stream");
        }  
        catch (IOException | InterruptedException ex) 
        {
            System.out.println(threadName + ": " + ex.toString());
        }
        
        System.out.println(threadName + ": exit");
        try 
        {
            this.semaphore3.acquire();
        } 
        catch (InterruptedException ex) 
        {
            System.out.println(threadName + ": " + ex.toString());
        }
    }
   
    public void start ()
    {
        System.out.println(threadName + ": start");
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
        this.semaphore2.release(1);
    }
}