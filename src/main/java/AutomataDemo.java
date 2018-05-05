import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


    class Automata {

        public int cash;
        public int price;
        public int change;

        public Automata() {

        }


        //     public int change;
        public Properties getMenu() {
            return menu;
        }

        enum STATES {
            OFF,
            WAIT,//switch-on state
            ACCEPT,
            CHECK,
            COOK
        }

        private STATES state;
        private Properties menu;

        Automata(Properties menu) {
            STATES state = STATES.OFF;
        }

        public void on() {
            state = STATES.WAIT;
            System.out.println("Automata is switched on");
        }

        public int cancel(int moneyForReturn){
            state=STATES.WAIT;
            cash=moneyForReturn;
            System.out.println("Take your money back: "+cash);
            return cash;
        }

        public void off() {
            state = STATES.OFF;
            System.out.println("Automata is switched off");
        }

        public int coin(int addMoney) {
            //передать деньги int coin(10)
            if(state == STATES.WAIT) {
                state = STATES.ACCEPT;
                cash+=addMoney;
            }else {
                state = STATES.ACCEPT;
                cash+=addMoney;
            }
//        cash=inputMoney;
            System.out.println("Money accepted. You give: "+cash+" rubles");
            return cash;
        }

        public void setMenu(Properties menu) {
            this.menu = menu;
        }

        public STATES choice(int priceOfDrink) throws InterruptedException {
            /* call check cook finish */
            price=priceOfDrink;
            System.out.println("Please, choose your drink...");
            System.out.println("price is: "+price);
            if(cash>=price){
                check(cash,price);
                cook();
                finish();
                return state;
            }else
                check(cash,price);
            return state;
        }

        private void check(int cashInput, int priceOfDrink) throws InterruptedException {
            state=STATES.CHECK;
            cash=cashInput;
            price=priceOfDrink;
            if (cash == price) {
                state = STATES.COOK;
//                System.out.println("state: "+state);
            }
            else if(cash>price) {
                state = STATES.COOK;
                returnChange(cashInput, priceOfDrink);
                System.out.println("Take your change..."+returnChange(cashInput,priceOfDrink));
//                System.out.println("state: "+state);
            }
            else if (cash < price) {
                state = STATES.WAIT;
                System.out.println("Not enough money. If you want to continue, add some coins...");
                coin(30);
                System.out.println(cash);
                if(cash>=price) {
                    cook();
                }else{
                    System.out.println("Still not enough money.");
                    cancel(cash);
                }
//                System.out.println("state: "+state);

            } else {
                state = STATES.WAIT;
                System.out.println("If you don't want to continue, press CANCEL");
                cancel(cash);
            }
        }

        private void cook() throws InterruptedException {//InterruptedException сигнализирует о том, что поток просят завершить его работу. При этом вас не просят немедленно завершить свою работу. Вас просят корректно завершить работу. На это может понадобится некоторое время
            System.out.println("Please wait 5 sec. Your drink is preparing...");
            Thread.sleep(5000);
            state=STATES.WAIT;
        }
        public int returnChange(int cashInput, int priceOfDrink){
            state=STATES.CHECK;
            cash=cashInput;
            price=priceOfDrink;
            change=cash-price;
            return change;
        }
        private void finish(){
            state=STATES.WAIT;
            System.out.println("Done.");

        }

    }

    public class AutomataDemo {//сценарий в этом классе с описанием работы

        public static void main(String args[]) throws InterruptedException {
            File file = new File("src/main/resources/menu.properties");
            Properties properties = new Properties();
            try {
                FileInputStream fileInput = new FileInputStream(file);
                properties.load(fileInput);
                Integer latte=Integer.parseInt(properties.getProperty("db.latte"));
                Integer capuccino=Integer.parseInt(properties.getProperty("db.capuccino"));
                Integer coffeBlack=Integer.parseInt(properties.getProperty("db.coffeBlack"));
                Integer espresso=Integer.parseInt(properties.getProperty("db.espresso"));
                System.out.println("latte: "+latte+
                        ", capuccino: " +capuccino+
                        " ,coffeBlack: "+coffeBlack+
                        " ,espresso: "+espresso);
                fileInput.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Automata nescafe = new Automata(properties);
            nescafe.on();//включение
            nescafe.coin(30);
            Properties menu = nescafe.getMenu();
//            System.out.println(properties);
            int ourChoice;
            ourChoice=Integer.parseInt(properties.getProperty("db.latte"));
            System.out.println("You choice is: "+"latte "+ourChoice);
            nescafe.choice(ourChoice);
//            int change = nescafe.cancel();
            nescafe.off();
        }

    }

