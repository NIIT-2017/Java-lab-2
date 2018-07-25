import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {
        @org.junit.Test
        public void on() throws Exception {
            Automata automata = new Automata();
            assertEquals(Automata.STATES.OFF,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());

            automata.on();
            assertEquals(Automata.STATES.WAIT,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());

        }

        @org.junit.Test
        public void getState() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.coin(5);
            assertEquals(Automata.STATES.ACCEPT,automata.getState());

        }

        @org.junit.Test
        public void off() throws Exception {
            Automata automata = new Automata();
            automata.off();
            assertEquals(Automata.STATES.OFF,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
        }

        @org.junit.Test
        public void coin() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.coin(20);
            automata.coin(20);
            assertEquals(40, automata.getCash());
        }

        @org.junit.Test
        public void getCash() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.coin(10);
            assertEquals(Automata.STATES.ACCEPT,automata.getState());
            automata.off();
            assertEquals(Automata.STATES.ACCEPT,automata.getState());
            assertEquals(10,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());

        }

        @org.junit.Test
        public void cancel() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.coin(5);
            assertEquals(Automata.STATES.ACCEPT,automata.getState());
            assertEquals(5,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.cancel();
            assertEquals(Automata.STATES.WAIT,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.coin(10);
            automata.coin(5);
            assertEquals(Automata.STATES.ACCEPT,automata.getState());
            assertEquals(15,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.choice(2);
            assertEquals(Automata.STATES.CHECK,automata.getState());
            assertEquals(15,automata.getCash());
            assertEquals(2,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.cancel();
            assertEquals(Automata.STATES.WAIT,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
        }

        @org.junit.Test
        public void choice() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.coin(60);
            automata.choice(0);
            assertEquals(Automata.STATES.CHECK,automata.getState());
            assertEquals(60,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
        }

        @org.junit.Test
        public void check() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.coin(30);
            automata.choice(0);
            automata.check();
            assertEquals(Automata.STATES.CHECK,automata.getState());
            assertEquals(30,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(true,automata.getChecked());

        }

        @org.junit.Test
        public void cook() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.cook();
            assertEquals(Automata.STATES.WAIT,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.coin(60);
            automata.choice(3);
            automata.check();
            assertEquals(Automata.STATES.CHECK,automata.getState());
            assertEquals(60,automata.getCash());
            assertEquals(3,automata.getChoice());
            assertEquals(true,automata.getChecked());

            automata.cook();
            assertEquals(Automata.STATES.COOK,automata.getState());
            assertEquals(15,automata.getCash());
            assertEquals(3,automata.getChoice());
            assertEquals(false,automata.getChecked());
        }

        @org.junit.Test
        public void finish() throws Exception {
            Automata automata = new Automata();
            automata.on();
            automata.finish();
            assertEquals(Automata.STATES.WAIT,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.coin(80);
            automata.choice(4);
            automata.check();
            assertEquals(Automata.STATES.CHECK,automata.getState());
            assertEquals(80,automata.getCash());
            assertEquals(4,automata.getChoice());
            assertEquals(true,automata.getChecked());
            automata.cook();
            assertEquals(Automata.STATES.COOK,automata.getState());
            assertEquals(30,automata.getCash());
            assertEquals(4,automata.getChoice());
            assertEquals(false,automata.getChecked());
            automata.finish();
            assertEquals(Automata.STATES.WAIT,automata.getState());
            assertEquals(0,automata.getCash());
            assertEquals(0,automata.getChoice());
            assertEquals(false,automata.getChecked());
        }

    }