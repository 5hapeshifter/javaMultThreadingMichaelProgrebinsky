package section6theconcurrencychallengesandsolutions;

import java.util.Random;

public class Example1 {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
        BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogicThread1.start();
        businessLogicThread2.start();
        metricsPrinter.start();

    }

    public static class MetricsPrinter extends Thread{
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics){
            this.metrics = metrics;
        }

        @Override
        public void run(){
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e){

                }
                double currentAverage = metrics.getAverage();

                System.out.println("Current Average is " + currentAverage);
            }
        }
    }


    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {

            while (true) {
                long start = System.currentTimeMillis();

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                }

                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {

        private long count = 0;
        // variavel com alto risco de problemas com thread, por isso adicionado volatile para garantir que o uso dessa variável é atômico
        private volatile double average = 0.0;

        // Method com alto risco de problema usando threads
        public synchronized void addSample(long sample){
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        // safethread
        public double getAverage(){
            return average;
        }

    }
}
