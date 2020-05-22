public class ButtonsInDisplayOut {
    private Automata automata;
    private double cash;
    private double change;
    private Automata.STATES state;
    enum VARIANTS {BREAK,NOBREAK}

//constructor
    ButtonsInDisplayOut(Automata automata){
        this.automata = automata;
        this.cash = 0.0;
        this.change = 0.0;
        this.state = Automata.STATES.OFF;
    }

//methods
    void buttonsIn(BUTTONS button){
        if (button == BUTTONS.ON){
            this.automata.on();
        }
        else if (button == BUTTONS.OFF){
            this.automata.off();
        }
        else if (button == BUTTONS.CANCEL){
            this.automata.cancel();
        }
        else if (button == BUTTONS.COIN){
            this.automata.coin(10);
        }
        else if (button == BUTTONS.MENU1){
            this.automata.choice(1);
        }
        else if (button == BUTTONS.MENU2){
            this.automata.choice(2);
        }
        else if (button == BUTTONS.MENU3){
            this.automata.choice(3);
        }
        //add more menu buttons if it needs
    }

    void displayOut(String line, VARIANTS variant){
        if (variant == VARIANTS.BREAK)
            System.out.println(line);
        else
            System.out.print(line);
    }
}
