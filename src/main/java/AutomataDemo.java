
import java.io.*;
import java.net.URL;


public class AutomataDemo {
    public static void main(String[] args) {
        URL file=System.class.getResource("/menuPrice.TXT");

        Automata nescafe = new Automata(new File(file.getFile()));

        nescafe.on();
        nescafe.coin(5);
        nescafe.choice(3);
        nescafe.choice(1);
//nescafe.off();
        nescafe.coin(25);
        nescafe.choice(0);


    }
}

class Automata {
    private boolean productErrorMaxPosition=false;
    private int cashRevenue;
    private int cashUser;
    private Integer cashNeedAdd;
    private String[][] menu;
    private String productName;
    private int productPrice;
    private int cashBack;
    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}    ;
    private STATES state;
   private int marker;
    Automata() {
       this.state = STATES.OFF;
        this.cashRevenue = 0;
        this.cashUser = 0;
        this.cashBack = 0;
        this.cashNeedAdd=0;
        this.menu = setMenu();

    }
String [][] getMenu(){
	return menu;
}
		Automata(File in) {
        this.marker='#';
        this.state = STATES.OFF;
        this.cashRevenue = 0;
        this.cashUser = 0;
        this.cashBack = 0;
        this.cashNeedAdd = 0;
        setMenu(in);
    }

    String getNameMethod() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    int getPrice(int productPosition) {
        return Integer.parseInt(menu[productPosition][1]);
    }

    STATES getState() {
    	return state;
    }
    private String[][] setMenu() {
        return new String[][]{{"coffee", "20"}, {"tea", "10"}};
    }

    private void setMenu(File in) {
        BufferedReader fin;

        int sizeArray=0;

        try {
            FileInputStream stream=new FileInputStream(in);

            fin = new BufferedReader(new FileReader(in));
          //  fin.mark(0);
            int ch=' ';

            for(ch = fin.read();ch!=-1;ch = fin.read())
                if(ch ==marker)
                    sizeArray++;
            fin.close();
            fin = new BufferedReader(new FileReader(in));
            menu=new String[sizeArray][2];
            ch=' ';
            int i=0;

            do {
                ch = fin.read();
                if(ch==marker) {
                    menu[i][0]  = fin.readLine();//the name of product
                    menu[i][1] = fin.readLine();//the price of product
                    i++;
                }
            }while (ch!=-1);
        } catch (FileNotFoundException exc) {
            System.out.println("Error Input of the file: " + in.getName() + " " + exc);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    void on() {
if(menu!=null) {
    if (state == STATES.OFF)
        state = STATES.WAIT;
}else
    System.out.println("Error: don't download menu ");
    }

    void off() {
      //  if (state == STATES.WAIT)
            state = STATES.OFF;
    }

    void coin(int cashInput) {

        if (state == STATES.WAIT || state == STATES.ACCEPT) {
            cashUser += cashInput;
            state = STATES.ACCEPT;
        }

        print(getNameMethod());
    }

    void choice(int productPosition) {
      //  cashNeedAdd = 0;
        if (state == STATES.ACCEPT || cashUser > 0) {
            try {

                productName = menu[productPosition][0];
            } catch (ArrayIndexOutOfBoundsException exc) {
                productErrorMaxPosition = true;
                print(getNameMethod());
                productErrorMaxPosition = false;
                return;
            }


            productPrice = getPrice(productPosition);

            print(getNameMethod());
            check();
        }

    }

    private void check() {

        if (productPrice <= cashUser) {
                cook(productName);
        } else {
            cashNeedAdd = productPrice - cashUser;//the information only for print to the user
            productName = "";
            productPrice = 0;

        }

        print(getNameMethod());
        cashNeedAdd=0;
    }

    void cancel() {
        if (state == STATES.CHECK || state == STATES.COOK)
            state = STATES.ACCEPT;

        if (state == STATES.ACCEPT) {
            productPrice = 0;
            productName = "";
            cashNeedAdd = 0;

            if (cashUser > 0) {
                cashBack = cashUser;// if need, you can use the cashBack for return to user
                cashUser = 0;
                cashBackCancel();
            } else {
                state = STATES.WAIT;
            }
            print(getNameMethod());
        }

    }

    private void cashBackCancel() {
        print(getNameMethod());
        cashBack = 0;
        state = STATES.WAIT;
    }

    private void cook(String productName) {
        state = STATES.COOK;
        cashUser-=productPrice;
        cashRevenue += productPrice;
        print(getNameMethod());
        cancel();
        finish();
    }
    int getCashUser(){
        return cashUser;
    }

    int getCashRevenue(){
        return cashRevenue;
    }

    private void finish()  {
        state = STATES.WAIT;
        print(getNameMethod());

    }

    void print(String method) {

        if(productErrorMaxPosition==true) {
            System.out.println("Attention: This product is not available for selection");
            return;
        }
            System.out.print(method + " - " + state + "  -  ");
        if (state == STATES.ACCEPT && productPrice > 0) {
            System.out.println("Your choice :" + productName + ", price = " + productPrice + " rub.");
            return;
        }
        if (state == STATES.ACCEPT && cashUser > 0 && productPrice == 0) {
            System.out.println("Your cash: " + cashUser);
            if (cashNeedAdd > 0) {
                System.out.println("Your cash is: " + cashUser + ", You need add " + cashNeedAdd + " rub");
            }
            return;
        }
        if (state == STATES.ACCEPT && cashBack > 0) {
            System.out.println("CashBack: " + cashBack);
            return;
        }
        if (state == STATES.COOK) {
            System.out.println("Your  " + productName + " is cooking, wait please");
            try {
                Thread.sleep(2000);// time for cooking
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Your " + productName + " is ready.");
            return;
        }


    }
}