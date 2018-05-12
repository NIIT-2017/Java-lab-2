import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class AutomataDemo {

public static void main(String[] args) {

    File fileXML = new File("./src/main/resources/coffeeMachineMenu.xml"); // прописываем относительный путь к файлу
    Automata  coffeeMachine = new Automata(fileXML); // передаём фаил как аргумент

    coffeeMachine.on();
    coffeeMachine.coin(5);
    coffeeMachine.coin(10);
    coffeeMachine.coin(100);
    coffeeMachine.choice(5);
    coffeeMachine.off();
}
}

class Automata {
    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK};

    private STATES state; // текущее состояние автомата
    private int disposableCup; // количество одноразовых стаканчиков
    private int water; // количество воды в мл

    public String errorLog; // запись причин отказа в обслуживании
    public  int cash; // хранение текущей суммы
    public int cashBack; // сдача
    public String menu []; // массив строк названий напитков
    public int volume[]; // массив объёма порций напитков (соттветствует массиву menu)
    public int prices []; // массив цен напитков (соттветствует массиву menu)


    Automata(File menuFile){
        state = STATES.OFF;
        disposableCup = 100;
        water =10000;
        cash = 0;
        cashBack = 0;
        /*переделано на ввод массивов из файла*/
        //this.menu = new String[]{"эспрессо", "американо", "кофе с молоком", "капучино", "капучино с шоколадом",
        //"мокачино", "чай чёрный", "горячий шоколад", "двойной шоколад", "молочный шоколад"};
        //this.volume = new int[]{50, 100, 150, 150, 150, 150, 150, 150, 150, 150};
        //this.prices = new int[]{25, 25, 30, 40, 40, 40, 20, 30, 40, 35};

        parserXML(menuFile); // запускаем парсинг файла
    }

    private void parserXML (File fileXML) { // чтение из XML файла, должна быть приватной

        /***********************************Валидация файла*********************************/

        /*Поиск и создание экземпляра фабрики для языка XML Schema*/
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        // Компиляция схемы
        // Схема загружается в объект типа java.io.File,
        // но можно так же использовать  классы java.net.URL и javax.xml.transform.Source
        File schemaLocation = new File("./src/main/resources/menuSchema.xsd");
        Schema schema = null;
        try {
            schema = factory.newSchema(schemaLocation);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        /*Создание валидатора для схемы*/
        Validator validator = schema.newValidator();

        /*Разбор проверяемого документа*/
        Source source = new StreamSource(fileXML);

        /*Валидация документа*/
        try {
            try {
                validator.validate(source);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(fileXML + " is valid.");
        }
        catch (SAXException ex) {
            System.out.println(fileXML + " is not valid because ");
            System.out.println(ex.getMessage());
        }

        /**********************************Чтение данных из файла***************************************/

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // создали фабрику "строителей"

        try {
            DocumentBuilder db = dbf.newDocumentBuilder(); // создали конкретного "строителя" документа
            Document doc = db.parse(fileXML); // "строитель" построил документ

            NodeList nodeList = doc.getElementsByTagName("item"); // возвращает "список узлов" всех элементов с именем item

            /*создаём массивы с количеством элементов равным количеству нодов item*/
            this.menu = new String[nodeList.getLength()];
            this.volume = new int[nodeList.getLength()];
            this.prices = new int[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i); // текущий нод

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // getNodeType() - возращает тип node
                    //ELEMENT_NODE - это конкретный тип node (здесь это item), может иметь потомков (в отличае от TEXT_NODE)

                    Element element = (Element) node;

                    /*заполняем массивы с названием напитка объёма и цены*/
                    this.menu[i] = element.getElementsByTagName("name").item(0).getTextContent();
                    this.volume[i] = parseInt( element.getElementsByTagName("volume").item(0).getTextContent());
                    this.prices[i] = parseInt( element.getElementsByTagName("price").item(0).getTextContent());
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

   /*режим ожидания*/
    public byte on() { // должна быть публичной, первый раз вызываем её из main
        /*включение автомата*/
        if (state == STATES.OFF) { // здесь может быть несколько условий
            state = STATES.WAIT; // включаем, переходит в режим ожидания (WAIT) из состояния выключено (OFF)
            printStates();
            /*инструкция по использованию и меню*/
            System.out.print("Добро пожаловать. Опустите в монетоприёмник 1, 2, 5, 10 рублей. Или в банкнотоприёмник " +
                    "50 или 100 рублей. \nВведите число соответствующее номеру напитка. Дождитесь пока автомат приготовит его.\n" +
                    "Возьмите ваш напиток. Если ваша сумма превысит стоимость напитка, автомат выдаст вам сдачу.\n");
            printMenu();
            return 1;
        }

        else
            return 0;
    }

    /*выключение автомата*/
    public byte off() { // должна быть публичной, вызываем её из main
        if (state == STATES.WAIT) { // выключаем, переходим в состояние выключено (OFF) из состояния ожидания (WAIT)
            state = STATES.OFF;
            printStates();
            return 1;
        }

        else
            return 0;
    }

    /*внесение денег*/
    public byte coin(int money){ // должна быть публичной вызывается из main

        if (state == STATES.WAIT) { // переход в режим приёма денег (ACCEPT) из состояния ожидания (WAIT)
            cash += money;
            state = STATES.ACCEPT;
            printStates();
            System.out.println("Вы внесли " + money + " руб.");
            System.out.println("Общая сумма " + cash + " руб.");
            return 1;
        }

        else if (state == STATES.ACCEPT) { // продолжаем принимать деньги
            printStates();
            cash += money;
            System.out.println("Вы внесли " + money + " руб.");
            System.out.println("Общая сумма " + cash + " руб.");
            return 2;
        }

        else
            return 0;
    }

    /*отображение меню с напитками и ценами для пользователя*/
    public void printMenu() {
        System.out.print("Выберите напиток: \n");
        for (int i = 0; i < this.menu.length; i++) {
            System.out.print("<" + (i+1) + "> " + this.menu[i] + " " + this.volume[i] + " мл. за " + this.prices[i] + " рублей.\n");
        }
    }

    public void printStates() { // отображение текущего состояния для пользователя

        if (state == STATES.OFF)
            System.out.print("\nСостояние: Автомат выключен.\n");

        else if (state == STATES.WAIT)
            System.out.print("\nСостояние: Автомат в режиме ожидания.\n");

        else if (state == STATES.ACCEPT)
            System.out.print("\nСостояние: Автомат в режиме приёма денег.\n");

        else if (state == STATES.CHECK)
            System.out.print("\nСостояние: Автомат в режиме проверки.\n");

        else if (state == STATES.COOK)
            System.out.print("\nСостояние: Автомат в режиме приготовления напитка.\n");
    }

    public STATES getState () { // метод для тестирования, возвращает состояние
        return state;
    }

    public void setWater(int water) { // метод для тестирования, устанавливает значение water
        if (state == STATES.OFF) { // "заливка воды" только из OFF
            if (water > 1000) // 1000 максимальное значение
                this.water = 1000;
            else
                this.water = water;
        }
    }

    public void setDisposableCup (int disposableCup) { // метод для тестирования, устанавливает значение disposableCup
        if (state == STATES.OFF) { // "манипуляции со стаканчиками" только из OFF
            if (disposableCup > 100)
                this.disposableCup = 100; // 100 максимальное значение
            else
                this.disposableCup = disposableCup;
        }
    }

    /*выбор напитка*/
    public byte choice (int chosen) { // можно сделать через switch и значение menu[]
        if (state == STATES.ACCEPT){
           state = STATES.CHECK;
            printStates();

            System.out.print("\nВы выбрали " + this.menu[chosen - 1] + " за " + this.prices[chosen - 1] + " руб.\n");

            check(chosen);
            return 1;
        }

        else
            return 0;
    }

    /*отмена операции*/
    public byte cancel() { // должна быть публичной так как может вызывается из main

        if (state == STATES.ACCEPT) { // отмена операции пользователем
            cashBack = cash;
            cash = 0;
            System.out.print("Заберите обратно " + cashBack + " руб.\n"); // возврат внесённых денег
            // cashBack = 0; // не занулено для тестирования
            state = STATES.WAIT;
            printStates();
            printMenu();
            return 1;
        }

        else if (state == STATES.CHECK) { // при провале проверки (CHECK) решил сделать возвращение в (ACCEPT), а не сразу в (WAIT)
            System.out.print(errorLog + "\n");
            state = STATES.ACCEPT;
            printStates();
            return 2; // не используется
        }

        else
            return 0;
    }

    /*проверка*/
    private void check(int chosen) { // должен быть приватным и запускаться только из choice()
        if ( state == STATES.CHECK) { // запуск только из состояния проверки(CHECK)

            /*(chosen - 1) для соответствия нумерации в prices[]*/

            if (cash < prices[chosen - 1]) {
                errorLog = "Внесённых денег " + cash + " руб. не хватает для выбора " + menu[chosen - 1] + " за " +
                        prices[chosen - 1] + " руб.";
                cancel();
            }

            else if (this.water < volume[chosen - 1]) {
                errorLog = "Извините в автомате закончилась вода.";
                cancel();
            }

            else if (disposableCup <= 0) {
                errorLog = "Извините в автомате закончились стаканчики.";
                cancel();
            }

            else { // if (cash >= prices[chosen - 1]) { // проверка соответствия cash цене напитка prices
                cashBack = cash - prices[chosen - 1];
                if (cashBack > 0) { // выдача сдачи
                    System.out.print("Заберите вашу сдачу " + cashBack + " руб.\n");
                    cash = 0;
                }
                cook(chosen);
            }
        }

        else
            return;

    }

    /*имитация процесса приготовления напитка*/
    private void cook(int chosen) { // вызывается только из check()
        if (state == STATES.CHECK) {
            state = STATES.COOK;
            printStates();

            disposableCup--; // расходуем стаканчик
            water -= volume[chosen - 1]; // расходуем воду

            /*задержка убрана для более быстрых тестов*/
            /*try { // имитация задержки, можно сделать через цикл
                Thread.currentThread().sleep(3000);
                System.out.print("\nЖдите\n");
                Thread.currentThread().sleep(3000);
                System.out.print("\nЖдите\n");
                Thread.currentThread().sleep(3000);
                System.out.print("\nЖдите\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            System.out.print("\nЖдите\n");
            System.out.print("\nЖдите\n");
            System.out.print("\nЖдите\n");

            System.out.print("\nВаш " + menu[chosen - 1] + " готов.\n");
            //System.out.print("\nТест количества воды " + water + "\n");
            //System.out.print("\nТест количества стаканов " + disposableCup + "\n");
            finish();
        }
    }

    /*возврат в режим ожидания после приготовления напитка*/
    private void finish() {
        if (state == STATES.COOK) {
            state = STATES.WAIT;
            //cashBack = 0; // не занулено для тестирования
            printStates();
            printMenu();
        }
    }
}
