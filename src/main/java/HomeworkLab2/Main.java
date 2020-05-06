package HomeworkLab2;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
   public static  void main(String argv[]) throws URISyntaxException
    {
        Automata vendor=new Automata("Menu.json");
        System.out.print("Enter nmber of operation:\n0) On \n1) Coin \n2) Cancel \n3) Choice\n 4) Cook \n5) Finish \n6) Off\n7) Get menu \n");
        int operation;
        Scanner scanner=new Scanner(System.in);
        while(true)
        {
            operation = Integer.parseInt(scanner.next());

            switch (operation) {
                case 0:
                    vendor.on();
                    break;
                case 1:
                    System.out.println("Insert money\n");
                    int money=Integer.parseInt(scanner.next());
                    vendor.coin(money);
                    break;
                case 2:
                    vendor.cancel();
                    break;
                case 3:
                    vendor.choice("Tea");
                    break;
                case 4:
                    vendor.cook();
                    break;
                case 5:
                    vendor.finish();
                    break;
                case 6:
                    vendor.off();
                    break;
                case 7:
                    ArrayList<String> menu= vendor.getMenu();
                    if(menu!=null)
                    {
                        for (int i = 0; i < menu.size(); i++) {
                            System.out.println(menu.get(i));
                        }
                    }
                    break;
            }
            System.out.println(vendor.getState());
        }
    }
}
