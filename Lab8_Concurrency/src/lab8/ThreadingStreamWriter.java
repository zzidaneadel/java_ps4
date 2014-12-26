package lab8;

class StreamWriterThread implements Runnable 
{
    public Thread t;
    private final String threadName;
    private String data;
    private String code;
    
    StreamWriterThread( String name, String a, String b)
    {
        
        this.threadName = name;
        if (a.startsWith("DATA:"))
        {
            this.data = a.substring(5, a.length());
            this.code = b.substring(5, b.length());
        }
        else
        {
            if (a.startsWith("CODE:"))
            {
                this.data = b.substring(5, b.length());
                this.code = a.substring(5, a.length());
            }
        }
        System.out.println(threadName + ": creating thread");
    }
    
    @Override
    public void run() 
    {
        System.out.println(threadName + ": running thread");
        //encode data by XOR and XOR again to decode                
        String encoded = DataXORCode(data,code);
        String decoded = DataXORCode(DataXORCode(data,code),code);
        
        System.out.println(this.threadName+": Encoding: (data: "+data+", key "+code+": "+encoded+ ")");
        System.out.println(this.threadName+": Checking (data: "+decoded+ ")");
        
        if (data.equals(decoded))
        {
            System.out.println(this.threadName+": It is correct");
        }
        else
        {
            System.out.println(this.threadName+": It is incorrect");
        }
        System.out.println(this.threadName+": exit");   
    }
   
    public void start ()
    {
        System.out.println(threadName + ": start");
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
    
    public static String DataXORCode(String data, String code)
    {
        while (data.length() > code.length())
        {
            code += code;
        }
        
        if (data.length() < code.length())
        {
            code = code.substring(0, data.length());
        }
        
        byte[] data_b = data.getBytes();
        byte[] code_b = code.getBytes();
        
        for (int i = 0; i<data_b.length; i++)
        {
            data_b[i] = (byte) (data_b[i]^code_b[i]);
        }
        
        String res = new String(data_b);
        
        return res;
    }
}