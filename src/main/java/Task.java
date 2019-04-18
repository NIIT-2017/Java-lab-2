import java.util.ArrayList;

public class Task {
    public static void main(String[] args){
        Automata auto = new Automata();
        auto.on();
        System.out.println("Status: " + auto.getState());
        ArrayList[] buf = auto.getMenu();
        for (int i=0; i<buf[0].size();i++) {
            for(int j=0; j<buf.length;j++)
            System.out.print(buf[j].get(i) + " ");
            System.out.println();
        }
        System.out.println("Cash: " + auto.coin(200)+" coins");
        System.out.println("Your choice is: " + auto.choice(3));
//        auto.cancel();
        if (auto.check() == STATES.ACCEPT) {
            System.out.println("Status: " + auto.getState());
            auto.cook();
            System.out.println("Status: " + auto.getState());
            System.out.println(auto.finish());
            System.out.println("Status: " + auto.getState());
            auto.off();
            System.out.println("Status: " + auto.getState());
        }
        else {
            System.out.println("You don't have enough money!");
            System.out.println("Get your " + auto.change + " coins back!");
        }

    }

}
