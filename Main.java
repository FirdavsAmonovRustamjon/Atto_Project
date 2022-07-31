import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        Managing managing = new Managing();

        Card object = managing.addCard(123, 17000);
        Card object1 = managing.addCard(124, 18000);
        Card object2 = managing.addCard(125, 19000);

        Terminal obj1 = managing.addTerminal(221, "Chilonzor 22-kvartal");
        Terminal obj2 = managing.addTerminal(221, "Mirobod 22-kvartal");
        Terminal obj3 = managing.addTerminal(221, "Shuhrat 22-kvartal");

        managing.makeTransaction(221, 123);
        managing.makeTransaction(223, 123);
        managing.makeTransaction(222, 123);


        Transaction[] arr = managing.transactionListByTerminal(221);

        for (Transaction x : arr) {
            if (x != null) {
                System.out.println(x);
            }
        }


    }
}