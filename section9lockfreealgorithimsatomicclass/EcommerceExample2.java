package section9lockfreealgorithimsatomicclass;

import java.util.concurrent.atomic.AtomicInteger;

public class EcommerceExample2 {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncremetingThread incremetingThread = new IncremetingThread(inventoryCounter);
        DecremetingThread decremetingThread = new DecremetingThread(inventoryCounter);

        /*
         * Não esta excutando a thread de forma simultanea, portanto o resultado será ZERO.
         */
//        incremetingThread.start();
//
//        incremetingThread.join();
//
//        decremetingThread.start();
//
//        decremetingThread.join();

        /*
         * Excutando a thread de forma simultanea o resultado varia, inclusive aparecendo resultados negativos, tendo em
         * vista que estamos utilizando/compartilhando o mesmo objeto 'InventoryCounter'.
         * O problema do resultado ocorre porque temos o incrementing e decrementing methods, que não são operações atômicas,
         * executando ao mesmo tempo em threads paralelas e elas concorrem pelo mesmo objeto 'InventoryCounter'.
         * Esse é o problema que pode ocorrer no banco de dados.
         *
         */
        incremetingThread.start();
        decremetingThread.start();

        incremetingThread.join();
        decremetingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class DecremetingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public DecremetingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++){
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncremetingThread extends Thread {
        private InventoryCounter inventoryCounter;

        public IncremetingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++){
                inventoryCounter.increment();
            }
        }
    }

    // Contador do estoque
    private static class InventoryCounter{

        private AtomicInteger items = new AtomicInteger(0);

        // sem o synchronized
   //    public void increment(){
   //        items++;
   //    }

   //    public void decrement () {
   //        items --;
   //    }

        // primeira solução
        public void increment(){
            items.incrementAndGet();
        }

        public void decrement () {
            items.decrementAndGet();
        }

        public int getItems() {
            return items.get();
        }

        // monitor
//        Object lock = new Object();
//
//        public void increment(){
//            synchronized (lock){
//                items++;
//            }
//        }
//
//        public void decrement () {
//            synchronized (lock){
//                items --;
//            }
//        }
//
//        public int getItems() {
//            synchronized (lock){
//                return items;
//            }
//        }
    }
}
