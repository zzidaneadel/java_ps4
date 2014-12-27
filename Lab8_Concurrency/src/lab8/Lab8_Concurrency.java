package lab8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Lab8_Concurrency 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
    	File filedata = new File("data.txt"); File filecode = new File("code.txt");
        PrepareFileStreams("messagenumber1\0messagenumber2\0\1", "thiscode1\0anothercode2\0\1", filedata, filecode);
        
        FileInputStream datain = new FileInputStream(filedata);
        FileInputStream codein = new FileInputStream(filecode);
        
        Semaphore sem1= new Semaphore(0);
        Semaphore sem2= new Semaphore(0);
        Semaphore sem3= new Semaphore(0);
        BlockingQueue bq = new ArrayBlockingQueue(2);
        
        StreamReaderThread streamreadt1 = new StreamReaderThread("DATA", datain, sem1, sem2, sem3, bq);
        streamreadt1.start();
        StreamReaderThread streamreadt2 = new StreamReaderThread("CODE", codein, sem1, sem2, sem3, bq);
        streamreadt2.start();
                
        try
        {
            sem2.acquire(2);
        }
        catch (InterruptedException ex) 
        {
            System.out.println("MAIN: " + ex.toString());
        }
        
        int output_id = 0;
        
        while ((streamreadt1.t.isAlive())||(streamreadt2.t.isAlive()))
        {
            
            while ((sem1.getQueueLength()<2)&&(sem3.getQueueLength()==0));
            
            
            if (sem3.getQueueLength()!=0)
            {
                break;
            }
            
            //synchronization
            System.out.println("MAIN: information can be input");
            sem1.release(2);
            
            String a="";
            String b="";
            try 
            {
                a = (String) bq.take();
                b = (String) bq.take();
            }
            catch (InterruptedException ex)
            {
                System.out.println("MAIN: " + ex.toString());
            }
            
            while ((sem2.getQueueLength()<2)&&(sem3.getQueueLength()==0));
            
            if (sem3.getQueueLength()!=0)
            {
                break;
            }
            
            //synchronization
            System.out.println("MAIN: input is done");
            sem2.release(2);
            
            
            output_id++;       
            StreamWriterThread TSW1 = new StreamWriterThread ("OUTPUT#"+output_id, a,b);
            TSW1.start();
        }

        
        while (sem3.getQueueLength()<2)
        {
            sem1.release(sem1.getQueueLength());
            sem2.release(sem2.getQueueLength());
        }
        
        sem3.release(2);
        
        datain.close();
        codein.close();
       
        System.out.println("MAIN: exit");
    }

    public static void PrepareFileStreams(String data, String code, File filedata, File filecode)
    {
        try 
        {
            FileOutputStream dataout = new FileOutputStream("data.txt");
            dataout.write(data.getBytes());
            FileOutputStream codeout = new FileOutputStream("code.txt");
            codeout.write(code.getBytes());
            dataout.close();
            codeout.close();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("MAIN: " + ex.toString());
        } 
        catch (IOException ex) 
        {
            System.out.println("MAIN: " + ex.toString());
        }
    }
}