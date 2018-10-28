import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestAutomat {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Test
    public void main(){
    String k = "COOKMENU";
        int myInt = DemoAutomat.checkString(k) ? 1 : 0;
        assertEquals(0,myInt);
    }

    @Test
    public void choice(){
String choice;
        Map<String,Integer> menu = new HashMap<String, Integer>() {
            {
                put("milk", 37);
            }
        };
        choice=Automat.choice(menu);
        assertEquals("milk",choice);
        int price = menu.get(choice);
        assertEquals(37,price);
    }


    @Test
    public void printmenu() {
        Map<String, Integer> menu = new HashMap<String, Integer>() {
            {
                put("milk", 37);
                put("water", 50);
                put("coffee", 83);
                put("juce", 20);
                put("beer", 130);
            }
        };
        ArrayList<String> s = Automat.printMenu(menu);
        ArrayList<String> s_res = new ArrayList<String>();
        s_res.add("coffee " + 83);
        s_res.add("milk " +  37);
        s_res.add("juce " + 20);
        s_res.add("water " + 50);
        s_res.add("beer " + 130);
        assertEquals(s_res,s);
    }
    @Test
    public void check(){
        Automat.Cash = 150;
        int res = Automat.check(100);
        if (res<0)
            Automat.EnCook = 1;

        assertEquals(1,Automat.EnCook);
    }

    @Test
    public void coin() {
        Automat.Cash = 0;
        Automat.coin(100);
        assertEquals(100,Automat.Cash);

    }

}
