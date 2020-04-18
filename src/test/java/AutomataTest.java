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
        String testingstring = "on\n" +
                               "getmenu\n" +
                               "coin 200\n" +
                               "choice coffee\n" +
                               "check\n" +
                               "cook\n" +
                               "exit";

        bais = new ByteArrayInputStream(testingstring.getBytes());
        Atm = new Automata(bais, baos);
        Atm.work();

        String answer = "[Automate] The automate is disable. Possible actions : ON\n" +
                        "[Client] on\n" +
                        "[Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE\n" +
                        "[Client] getmenu\n" +
                        "[Automate] Menu:\n" +
                        "[Automate] tea : 50\n" +
                        "[Automate] coffee : 100\n" +
                        "[Automate] juice : 150\n" +
                        "[Client] coin 200\n" +
                        "[Automate] You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE\n" +
                        "[Automate] cash : 200.0\n" +
                        "[Client] choice coffee\n" +
                        "[Automate] You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE\n" +
                        "[Client] check\n" +
                        "[Automate] Cash is enough, you can cook the beverage\n" +
                        "[Client] cook\n" +
                        "[Automate] The cooking has started. Possible actions : just relax and wait\n" +
                        "[Automate] cooking...\n" +
                        "[Automate] Please, your coffee\n" +
                        "[Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE\n" +
                        "[Client] exit\n";
        assertArrayEquals(answer.split("\r?\n"), baos.toString().split("\r?\n"));
    }

    @Test
    public void work2(){
        baos.reset();
        String testingstring = "on\n" +
                               "coin 100\n" +
                               "chiose juice\n" +
                               "choice juice\n" +
                               "cook\n" +
                               "cancel\n" +
                               "coin 100\n" +
                               "choice juice\n" +
                               "check\n" +
                               "cook\n";

        bais = new ByteArrayInputStream(testingstring.getBytes());
        Atm = new Automata(bais, baos);
        Atm.work();
        String answer = "[Automate] The automate is disable. Possible actions : ON\n" +
                        "[Client] on\n" +
                        "[Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE\n" +
                        "[Client] coin 100\n" +
                        "[Automate] You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE\n" +
                        "[Automate] cash : 100.0\n" +
                        "[Client] chiose juice\n" +
                        "[Automate] Unknown command \"chiose\"\n" +
                        "[Client] choice juice\n" +
                        "[Automate] You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE\n" +
                        "[Client] cook\n" +
                        "[Automate] Cash = 100.0\n" +
                        "[Automate] Price of juice = 150\n" +
                        "[Automate] You have to add 50.0\n" +
                        "[Client] cancel\n" +
                        "[Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE\n" +
                        "[Client] coin 100\n" +
                        "[Automate] You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE\n" +
                        "[Automate] cash : 200.0\n" +
                        "[Client] choice juice\n" +
                        "[Automate] You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE\n" +
                        "[Client] check\n" +
                        "[Automate] Cash is enough, you can cook the beverage\n" +
                        "[Client] cook\n" +
                        "[Automate] The cooking has started. Possible actions : just relax and wait\n" +
                        "[Automate] cooking...\n" +
                        "[Automate] Please, your juice\n" +
                        "[Automate] The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE\n" +
                        "[Warning!] All input commands have red. Last command must be \"exit\". Forced termination.\n";
        assertArrayEquals(answer.split("\r?\n"), baos.toString().split("\r?\n"));
    }
}