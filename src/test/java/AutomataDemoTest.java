import static org.junit.Assert.*;

public class AutomataDemoTest
{
    private static String form(double val) {
        return String.format("%(.2f", val);
    }

    @org.junit.Test
    public void scenariosLog1() {
        String[] scenario = {"", "", "EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "Сценарий корректен.\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "Автомат включен.\n" +
                "\n" +
                "Состояние ON. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание начато.\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние WAIT. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание завершено.\n" +
                "\n" +
                "Состояние OFF. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Автомат выключен.\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog2() {
        String[] scenario = {"1.00", "", "COIN,CHANGE,EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "Сценарий корректен.\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "Автомат включен.\n" +
                "\n" +
                "Состояние ON. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание начато.\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(1)+".\n" +
                "\n" +
                "Сдача равна: "+form(1)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние WAIT. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание завершено.\n" +
                "\n" +
                "Состояние OFF. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Автомат выключен.\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog3() {
        String[] scenario = {"2.00", "Coffee Espresso", "COIN,DRINK,CHANGE,EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "Сценарий корректен.\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "Автомат включен.\n" +
                "\n" +
                "Состояние ON. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание начато.\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(2)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(2)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(2)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "1\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(2)+".\n" +
                "\n" +
                "Сумма к списанию: "+form(1.59)+"\n" +
                "\n" +
                "Состояние COOK. Действие DRINK. Сумма "+form(0.41)+".\n" +
                "\n" +
                "Идет приготовление напитка: Coffee Espresso\n" +
                "...........................\n" +
                "\n" +
                "Приготовление напитка Coffee Espresso завершено.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.41)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(0.41)+".\n" +
                "\n" +
                "Сдача равна: "+form(0.41)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние WAIT. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание завершено.\n" +
                "\n" +
                "Состояние OFF. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Автомат выключен.\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog4() {
        String[] scenario = {"0.50,1.00,1.50",
                "Coffee Espresso,Coffee Americano,Coffee Cappuccino",
                "COIN,DRINK,COIN,DRINK,COIN,DRINK,CHANGE,EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "Сценарий корректен.\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "Автомат включен.\n" +
                "\n" +
                "Состояние ON. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание начато.\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(0.50)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(0.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "1\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(0.50)+".\n" +
                "\n" +
                "Недостаточно средств.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние ACCEPT. Действие COIN. Сумма "+form(0.50)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1.00)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(1.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "2\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(1.50)+".\n" +
                "\n" +
                "Недостаточно средств.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние ACCEPT. Действие COIN. Сумма "+form(1.50)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1.50)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(3.00)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(3.00)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "3\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(3.00)+".\n" +
                "\n" +
                "Сумма к списанию: "+form(2.80)+"\n" +
                "\n" +
                "Состояние COOK. Действие DRINK. Сумма "+form(0.20)+".\n" +
                "\n" +
                "Идет приготовление напитка: Coffee Cappuccino\n" +
                "...........................\n" +
                "\n" +
                "Приготовление напитка Coffee Cappuccino завершено.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.20)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(0.20)+".\n" +
                "\n" +
                "Сдача равна: "+form(0.20)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние WAIT. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание завершено.\n" +
                "\n" +
                "Состояние OFF. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Автомат выключен.\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog5() {
        String[] scenario = {"1.30,0.50,1.00,1.50,1.00,2.50,0.50",
                "Coffee Cappuccino,Coffee Espresso,Coffee Americano,Coffee Glasses",
                "COIN,CHANGE,COIN,DRINK,CHANGE,COIN,COIN,DRINK,CHANGE,COIN,DRINK,COIN,COIN,DRINK,CHANGE,EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "Сценарий корректен.\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "----------------\n" +
                "\n" +
                "Автомат включен.\n" +
                "\n" +
                "Состояние ON. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание начато.\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1.30)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1.30)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(1.30)+".\n" +
                "\n" +
                "Сдача равна: "+form(1.30)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(0.50)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(0.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "3\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(0.50)+".\n" +
                "\n" +
                "Недостаточно средств.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(0.50)+".\n" +
                "\n" +
                "Сдача равна: "+form(0.50)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние ACCEPT. Действие COIN. Сумма "+form(1)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1.50)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(2.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(2.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "1\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(2.50)+".\n" +
                "\n" +
                "Сумма к списанию: "+form(1.59)+"\n" +
                "\n" +
                "Состояние COOK. Действие DRINK. Сумма "+form(0.91)+".\n" +
                "\n" +
                "Идет приготовление напитка: Coffee Espresso\n" +
                "...........................\n" +
                "\n" +
                "Приготовление напитка Coffee Espresso завершено.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.91)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(0.91)+".\n" +
                "\n" +
                "Сдача равна: "+form(0.91)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние WAIT. Действие COIN. Сумма "+form(0)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(1)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(1)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "2\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(1)+".\n" +
                "\n" +
                "Недостаточно средств.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(1)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние ACCEPT. Действие COIN. Сумма "+form(1)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(2.50)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(3.50)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние ACCEPT. Действие COIN. Сумма "+form(3.50)+".\n" +
                "\n" +
                "Сумма:\n" +
                form(0.50)+"\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(4)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "1\n" +
                "\n" +
                "Состояние ACCEPT. Действие DRINK. Сумма "+form(4)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "Номер напитка:\n" +
                "4\n" +
                "\n" +
                "Состояние CHECK. Действие DRINK. Сумма "+form(4)+".\n" +
                "\n" +
                "Сумма к списанию: "+form(3.28)+"\n" +
                "\n" +
                "Состояние COOK. Действие DRINK. Сумма "+form(0.72)+".\n" +
                "\n" +
                "Идет приготовление напитка: Coffee Glasses\n" +
                "...........................\n" +
                "\n" +
                "Приготовление напитка Coffee Glasses завершено.\n" +
                "\n" +
                "Состояние ACCEPT. Действие NULL. Сумма "+form(0.72)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.\n" +
                "Номер:\n" +
                "3\n" +
                "\n" +
                "Состояние ACCEPT. Действие CHANGE. Сумма "+form(0.72)+".\n" +
                "\n" +
                "Сдача равна: "+form(0.72)+"\n" +
                "\n" +
                "Состояние WAIT. Действие NULL. Сумма "+form(0)+".\n" +
                "\n" +
                "1 - Coffee Espresso - "+form(1.59)+"$.  2 - Coffee Americano - "+form(2.10)+"$.  3 - Coffee Cappuccino - "+form(2.80)+"$.  4 - Coffee Glasses - "+form(3.28)+"$.  \n" +
                "\n" +
                "1 - Внести сумму, 2 - Выход.\n" +
                "Номер:\n" +
                "2\n" +
                "\n" +
                "Состояние WAIT. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Обслуживание завершено.\n" +
                "\n" +
                "Состояние OFF. Действие EXIT. Сумма "+form(0)+".\n" +
                "\n" +
                "Автомат выключен.\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog6() {
        String[] scenario = {"-0.50,-1.00,-1.50",
                "Cofe Espresso,Coffee America,Coffee Glases,Coffee Capucino",
                "DRINK,DRINK,COIN,DRINK,EXIT,EXIT,DRINK,CHANGE,CHANGE,COIN,CHANGE"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "1. 1-я, 2-я, 3-я вносимая сумма должна быть больше нуля.\n" +
                "2. 1-й, 2-й, 3-й, 4-й напиток в меню не найден.\n" +
                "3. Первой должна быть комманда <COIN>.\n" +
                "4. Количество комманд <COIN> должно быть равно количеству coins.\n" +
                "5. Если комманда <CHANGE> не перед <EXIT>, то после нее должна быть комманда <COIN>.\n" +
                "6. Комманда <CHANGE> не может повторяться более 1-го раза подряд.\n" +
                "7. Перед коммандой <EXIT> должна быть комманда <CHANGE>.\n" +
                "8. Комманда <EXIT> должна быть одна и последней.\n" +
                "9. Последней должна быть комманда <EXIT>.\n" +
                "Сценарий некорректен! Необходимо внести исправления!\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog7() {
        String[] scenario = {"1.00", "", "COIN,CHANGE"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "1. Перед коммандой <EXIT> должна быть комманда <CHANGE>.\n" +
                "2. Последней должна быть комманда <EXIT>.\n" +
                "Сценарий некорректен! Необходимо внести исправления!\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog8() {
        String[] scenario = {"2.00", "", "COIN,DRINK,CHANGE,EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "1. Ни один напиток не указан.\n" +
                "Сценарий некорректен! Необходимо внести исправления!\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }

    @org.junit.Test
    public void scenariosLog9() {
        String[] scenario = {"2.00", "Coffee A", "COIN,DRINK,CHANGE,EXIT"};
        String log = ("\n" +
                "----------------\n" +
                "\n" +
                "Проверка сценария:\n" +
                "1. 1-й напиток в меню не найден.\n" +
                "Сценарий некорректен! Необходимо внести исправления!\n" +
                "\n" +
                "----------------\n");
        assertEquals(log, AutomataDemo.scenariosLog(scenario));
    }
}
