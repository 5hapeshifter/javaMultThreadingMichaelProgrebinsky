package section3threadingcoordination;

public class Example1 {

    public static void main(String[] args) {
        // Exception InterruptedException é lançada quando o metodo interrupt é chamado
        Thread thread = new Thread( new BlockingTask());
        thread.start();
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try{
                Thread.sleep(500000);
            } 
            catch(InterruptedException e){
                System.out.println("Exiting blocking thread");    
            }
            

        }

    }



}
