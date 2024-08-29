package section3threadingcoordination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Training1 {

    public static void main(String[] args) {
        List<Long> numbers = Arrays.asList(123L, 4556L, 1067899999900009999L,25L,5L);
        List<Thread> threads = new ArrayList<>();

        for (Long num : numbers) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(pow(num));
                }
            });
            // aplicação pode terminar mesmo sem os cálculos terminarem
            thread.setDaemon(true);
            threads.add(thread);
        }

        threads.forEach(t -> {
            t.start();
            try {
                // time limit to calc
                t.join(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public static BigInteger pow(Long num){
        BigInteger initial = BigInteger.ONE;
        BigInteger other = BigInteger.valueOf(num);
        for(long i = num; i > 0 ; i--){
            initial =  initial.multiply(new BigInteger(String.valueOf(num)));
        }
        return initial;
    }
}
