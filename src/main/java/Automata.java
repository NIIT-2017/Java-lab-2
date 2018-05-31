public class Automata {
    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK};

    private STATES state; // текущее состояние автомата
    private int disposableCup; // количество одноразовых стаканчиков
    private int water; // количество воды в мл
    private String menu []; // массив строк названий напитков
    private int volume[]; // массив объёма порций напитков (соттветствует массиву menu)
    private int prices []; // массив цен напитков (соттветствует массиву menu)

    /*используются в тестах*/
    public String errorLog; // запись причин отказа в обслуживании
    public int cash; // хранение текущей суммы
    public int cashBack; // сдача


    Automata(){
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

        FileReader startReading = new FileReader();
        this.menu = startReading.setMenu();
        this.volume = startReading.setVolume();
        this.prices = startReading.setPrices();
    }

    /*режим ожидания*/
    public byte on() { // должна быть публичной, первый раз вызываем её из main
        /*включение автомата*/
        if (state == STATES.OFF) { // здесь может быть несколько условий
            state = STATES.WAIT; // включаем, переходит в режим ожидания (WAIT) из состояния выключено (OFF)
            printStates();
            /*инструкция по использованию и меню*/
            /*System.out.print("Добро пожаловать. Опустите в монетоприёмник 1, 2, 5, 10 рублей. Или в банкнотоприёмник " +
                    "50 или 100 рублей. \nВведите число соответствующее номеру напитка. Дождитесь пока автомат приготовит его.\n" +
                    "Возьмите ваш напиток. Если ваша сумма превысит стоимость напитка, автомат выдаст вам сдачу.\n");*/
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
            //System.out.println("Вы внесли " + money + " руб.");
            //System.out.println("Общая сумма " + cash + " руб.");
            return 1;
        }

        else if (state == STATES.ACCEPT) { // продолжаем принимать деньги
            printStates();
            cash += money;
            //System.out.println("Вы внесли " + money + " руб.");
            //System.out.println("Общая сумма " + cash + " руб.");
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

            //System.out.print("\nВы выбрали " + this.menu[chosen - 1] + " за " + this.prices[chosen - 1] + " руб.\n");

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
            //System.out.print("Заберите обратно " + cashBack + " руб.\n"); // возврат внесённых денег
            // cashBack = 0; // не занулено для тестирования
            state = STATES.WAIT;
            printStates();
            printMenu();
            return 1;
        }

        else if (state == STATES.CHECK) { // при провале проверки (CHECK) решил сделать возвращение в (ACCEPT), а не сразу в (WAIT)
            //System.out.print(errorLog + "\n");
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
                    //System.out.print("Заберите вашу сдачу " + cashBack + " руб.\n");
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

            //System.out.print("\nЖдите\n");
            //System.out.print("\nЖдите\n");
            //System.out.print("\nЖдите\n");

            //System.out.print("\nВаш " + menu[chosen - 1] + " готов.\n");
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

