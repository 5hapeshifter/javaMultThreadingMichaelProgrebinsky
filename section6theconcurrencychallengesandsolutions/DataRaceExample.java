package section6theconcurrencychallengesandsolutions;

public class DataRaceExample {

    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }
        });
        thread1.start();
        thread2.start();
    }

    public static class SharedClass {
        private volatile int x = 0;
        private volatile int y = 0;
        // ocorre erro sem a keyword volatile
//        private int x = 0;
//        private int y = 0;

        public void increment () {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if(y > x){
                System.out.println("y = " + y + " > " + " x = " + x + " Data race is detected");
            }
        }
    }
}
