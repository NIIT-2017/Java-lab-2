public class Main {
    public static void main(String[] args) {
        //Здесь представлен небольшой сценарий работы с автоматом по продаже напитков
        Automata Aut = new Automata("menu.json");
        //Включение автомаата
        System.out.println(Aut.on());
        //Внесение наличных средств
        System.out.println(Aut.coin(20));
        //Получение статуса готовности автомата
        System.out.println(Aut.getState());
        //Порлучение информации о меню
        for(String mystring: Aut.getMenu()){
            System.out.println(mystring);
        }
        //Внесение дополнительных средств
        System.out.println(Aut.coin(100));
        //Выбор напитка
        System.out.println(Aut.choise(3));
        //Получение статуса готовности автомата
        System.out.println(Aut.getState());
        //Завершение работы и возврат оставшихся средств
        System.out.println(Aut.finish("YES"));
    }
}
