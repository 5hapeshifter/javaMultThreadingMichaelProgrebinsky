package section3threadingcoordination;

import java.math.BigInteger;

public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("20000"), new BigInteger("100000000")));
        thread.setDaemon(true); // Mesmo com o calculo grande em execucao, quando a thread main termina, a aplicacao para.
        thread.start();

        /*
         * Em calculo de valores muito grandes, não adianta chamar apenas o metodo interrupt
         * temos que fazer uma verificação no for validando se a execução foi interrompida em algum momento e,
         * se sim, terminamos a execução.
         * Uma solução mais simples é setar a Daemon thread como true, dessa forma a thread main encerrando a aplicacao sera encerrada tambem.
         */
        
        Thread.sleep(100);
        thread.interrupt();
    
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power){
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power){
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)){
                // if(Thread.currentThread().isInterrupted()){
                //     System.out.println("Prematurely interrupted computation");
                //     return BigInteger.ZERO;
                // }
                
                result = result.multiply(base);
            }
            return result;
        }
    }

}
