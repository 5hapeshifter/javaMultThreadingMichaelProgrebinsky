package section9lockfreealgorithimsatomicclass;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferencesExample {

    public static void main(String[] args) {
        String oldName = "oldName";
        String newName =  "new name";
        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);

        // a linha abaixo ativa o else
        //atomicReference.set("Unexpected name");
        if (atomicReference.compareAndSet(oldName, newName)) {
            System.out.printf("New value is " + atomicReference.get());
        }
        else {
            System.out.println("Nothing Changed");
        }
    }


}
