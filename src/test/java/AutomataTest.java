import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutomataTest {
    static Automata a = new Automata(new String[]{"Coca-cola", "Tea", "Coffee"}, new int[]{12, 10, 15});

    @Test
    public void test01() throws Exception {
        assertEquals(STATES.OFF, a.printState());
    }
    @Test
    public void test02() throws Exception {
        assertEquals(STATES.WAIT, a.on());
    }
    @Test
    public void test03() throws Exception {
        assertEquals(STATES.WAIT, a.printState());
    }
    @Test
    public void test04() throws Exception {
        assertEquals(100, a.coin(100));
    }
    @Test
    public void test05() throws Exception {
        assertEquals("[[Coca-cola, 12], [Tea, 10], [Coffee, 15]]", Arrays.deepToString(a.printMenu()));
    }
    @Test
    public void test06() throws Exception {
        assertEquals("Coca-cola", a.choice(0));
    }
    @Test
    public void test07() throws Exception {
        assertEquals(100, a.getCash());
    }
    @Test
    public void test08() throws Exception {
        assertEquals(100, a.cancel());
    }
    @Test
    public void test09() throws Exception {
        assertEquals("Too little money for your choice...", a.choice(0));
    }
    @Test
    public void test10() throws Exception {
        assertEquals(STATES.WAIT, a.printState());
    }
    @Test
    public void test11() throws Exception {
        assertEquals("Too little money for your choice...", a.cook());
    }
    @Test
    public void test12() throws Exception {
        assertEquals(STATES.WAIT, a.printState());
    }
    @Test
    public void test13() throws Exception {
        assertEquals(0, a.getCash());
    }
    @Test
    public void test14() throws Exception {
        assertEquals(18, a.coin(18));
    }
    @Test
    public void test15() throws Exception {
        assertEquals("Tea",a.choice(1));
    }
    @Test
    public void test16() throws Exception {
        assertEquals("Enjoy your drink...",a.cook());
    }
    @Test
    public void test17() throws Exception {
        assertEquals(8,a.finish());
    }
    @Test
    public void test18() throws Exception {
        assertEquals(10,a.getCash());
    }
    @Test
    public void test19() throws Exception {
        assertEquals(STATES.WAIT,a.printState());
    }


}
