package section2threadcreation;

public class Example2 extends Thread {

    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.start();
    }

    /*
        Quando criamos uma classe que extend 'Thread' obtemos o beneficio de poder acessar 
        todos os metodos diretamente atraves do this apenas instanciando a classe que criamos
     */ 
    private static class NewThread extends Thread{

        @Override
        public void run() {
            System.out.println("Hello from " + this.getName());
        }

    }

}
