//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

//import static org.junit.jupiter.api.Assertions.*;

public class AutomataTest {

    static ByteArrayInputStream bais;
    static ByteArrayOutputStream baos = new ByteArrayOutputStream();

    static Automata Atm;

//    @AfterEach
//    void step(){
//        baos.reset();
//    }

    @Test
    public void work1() {
        baos.reset();
        String testingstring = """
            on
            getmenu
            coin 200
            choice coffee
            check
            cook
            exit""";

        bais = new ByteArrayInputStream(testingstring.getBytes());
        Atm = new Automata(bais, baos);
        Atm.work();

        String answer = """
                [Automate] The automate is disable. Possible actions : ON
                [Client] on
                [Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE
                [Client] getmenu
                [Automate] Menu:
                [Automate] tea : 50
                [Automate] coffee : 100
                [Automate] juice : 150
                [Client] coin 200
                [Automate] You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE
                [Automate] cash : 200.0
                [Client] choice coffee
                [Automate] You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE
                [Client] check
                [Automate] Cash is enough, you can cook the beverage
                [Client] cook
                [Automate] The cooking has started. Possible actions : just relax and wait
                [Automate] cooking...
                [Automate] Please, your coffee
                [Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE
                [Client] exit
                """;
        assertArrayEquals(answer.split("\r?\n"), baos.toString().split("\r?\n"));
    }

    @Test
    public void work2(){
        baos.reset();
        String testingstring = """
            on
            coin 100
            chiose juice
            choice juice
            cook
            cancel
            coin 100
            choice juice
            check
            cook
            """;

        bais = new ByteArrayInputStream(testingstring.getBytes());
        Atm = new Automata(bais, baos);
        Atm.work();
        String answer = """
                [Automate] The automate is disable. Possible actions : ON
                [Client] on
                [Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE
                [Client] coin 100
                [Automate] You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE
                [Automate] cash : 100.0
                [Client] chiose juice
                [Automate] Unknown command "chiose"
                [Client] choice juice
                [Automate] You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE
                [Client] cook
                [Automate] Cash = 100.0
                [Automate] Price of juice = 150
                [Automate] You have to add 50.0
                [Client] cancel
                [Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE
                [Client] coin 100
                [Automate] You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE
                [Automate] cash : 200.0
                [Client] choice juice
                [Automate] You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE
                [Client] check
                [Automate] Cash is enough, you can cook the beverage
                [Client] cook
                [Automate] The cooking has started. Possible actions : just relax and wait
                [Automate] cooking...
                [Automate] Please, your juice
                [Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE
                [Warning!] All input commands have red. Last command must be "exit". Forced termination.
                """;
        assertArrayEquals(answer.split("\r?\n"), baos.toString().split("\r?\n"));
    }
}