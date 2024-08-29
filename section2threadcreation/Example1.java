package section2threadcreation;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {

        // Thread thread = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println("We are now in thread: " + Thread.currentThread().getName());
        //         System.out.println("We are now in thread priority: " + Thread.currentThread().getPriority());

        //     }
        // });
        // thread.setName("minha thread");

        // // Define a prioridade de execucao da thread de 1 a 10, quanto mais alto, mais
        // // alta a prioridade de execucao
        // thread.setPriority(Thread.MAX_PRIORITY);

        // System.out.println("We are in thread: " + Thread.currentThread() + " before starting a new thread");
        // thread.start();
        // System.out.println("We are in thread: " + Thread.currentThread() + " after starting a new thread");

        // Thread.sleep(10000);

        // Captando exceptions que podem ser lançadas
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Intentional Exception");

            }
        });
        thread.setName("minha thread");

        // Define a prioridade de execucao da thread de 1 a 10, quanto mais alto, mais
        // alta a prioridade de execucao
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
                
            }
            
        });
        thread.start();
    }

}