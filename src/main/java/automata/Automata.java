package automata;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

/**
 * Created by kortez on 01/04/18.
 */
public class Automata{
    int getCompliteDrinkIndex() {
        return compliteDrinkIndex;
    }

    private int compliteDrinkIndex;

    double getMoney() {
        return money;
    }

    //Members
    enum States {OFF, WAIT,CHECK,COOK}
    private States state;
    private double cash;
    private double money;
    private int countDrinks;//доступное колличество напитков
    private int indexSelectedDrink;
    private Hashtable<Integer,String[]> drinks;
//Methods
    double getCash() {
        return cash;
    }

    String getState() {
        return state.toString();
    }

    //метод переводит автомат в состояние on
    void on(){
        //включение возможено только из off
        if (state == States.OFF) state = States.WAIT;
        work();
    }

    //метод переводит автомат в состояние off
    void off(){
        //выключение только из состояния wait
        if (state == States.WAIT) state = States.OFF;
        work();
    }

    public Automata(String path) {
        this.state = States.OFF;
        countDrinks=0;
        cash = 0.0f;
        money = 0.0f;
        drinks = new Hashtable<Integer, String[]>();
        indexSelectedDrink=0;
        File file = new File(path);
        readFromXML(file);
        //readMenuFromFile(file,"  "); для чтения из текстового файла
    }

    //вывод меню
    ArrayList<String> getMenu(){
        ArrayList<String> arrayStrign = new ArrayList<String>();
        arrayStrign.add("Доступные напитки:");
        for (int i = 1; i <= countDrinks; i++) {
            String[] drink = drinks.get(i);
            arrayStrign.add(i+" "+drink[0]+" "+drink[1]);
        }
        return arrayStrign;
    }

    //выбор напитка
    void choiceDrink(int drink){
        if(state==States.WAIT) {
            indexSelectedDrink = drink;
            state = States.CHECK;
            work();
        }
    }

    private void pay( double price){
        if (state==States.CHECK){
            //вход в состояние готовки только из состояния check
            cash-=price;
            money+=price;
            state=States.COOK;
        }
        work();
    }

    //метод внесения денег на счет в автомате
    void coin(double cash){
        if (cash>0.0) {
            //занесение денег только из состояния check
            if (state == States.CHECK) this.cash += cash;
            work();
        }
    }

    //прочитать меню из файла
    private void readMenuFromFile(File file, String spliter){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int i=1;
            while((line = reader.readLine())!=null){
                drinks.put(i,line.split(spliter));
                i++;
                countDrinks+=1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //напиток приготовлен
    private void complite() {
        compliteDrinkIndex = indexSelectedDrink;
        state=States.WAIT;
    }
    //возвращает деньги со счета
    double returnMoney() {
        double result=cash;
        cash=0.0f;
        return result;
    }

    //цыкл работы автомата
    private void work() {
        switch (state){
            //waiting
            case WAIT:  break;
            //checking
            case CHECK: checkMoney();   break;
            //cooling drink
            case COOK:  cookDrink();    break;
            default:    break;
        }
    }
    private void checkMoney(){
        //проверить хватает ли на счете денег
        double price =Double.parseDouble(drinks.get(indexSelectedDrink)[1]);
        if (price <= cash) pay(price);
    }

    private void cookDrink(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complite();
    }
    public void readFromXML(File file){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList nodeList = doc.getElementsByTagName("drink");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    String[] str=new String[2];
                    str[0]=element.getElementsByTagName("name").item(0).getTextContent();
                    str[1]=element.getElementsByTagName("cost").item(0).getTextContent();
                    this.drinks.put(++countDrinks,str);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
