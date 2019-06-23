package Automat;

import java.util.ArrayList;


class Main {
    public static void main(String[] args){
        Automata makeMeHappy = new Automata("drinks.txt");
        makeMeHappy.on();
        ArrayList<String>menu = makeMeHappy.getMenu();
        for (String i: menu){
            System.out.println(i);
        }
        makeMeHappy.coin(150);
        makeMeHappy.choice(6);
        makeMeHappy.coin(150);
        makeMeHappy.choice(6);
        makeMeHappy.off();
        makeMeHappy.coin(30);
        makeMeHappy.choice(3);
    }
}