public class AutomataBuild
{
    private static void build(Automata.Regime regime) {//Создание автомата
        control(new Automata(regime));
    }
    private static void control(Automata automata) {//Запуск - Выключение
        automata.on(); automata.start(); automata.off();
    }
    public static void manual() {//Режим ручного ввода комманд c консоли
        build(Automata.Regime.MANUAL);
    }
    public static void random() {//Режим генерирования случайных комманд
        build(Automata.Regime.RANDOM);
    }
    public static void command(String[] commands) {//Режим заданной последовательности комманд
        AutomataLoger.setTestQuickModes(false, false);
        if (AutomataCom.setCheck(commands)) build(Automata.Regime.COMMAND);
    }
    public static void commandQuick(String[] commands) {//Режим быстрого вывода на экран
        AutomataLoger.setTestQuickModes(false, true);
        if (AutomataCom.setCheck(commands)) build(Automata.Regime.COMMAND);
    }
    public static void commandTest(String[] commands) {//Режим для тестирования (без вывода на экран)
        AutomataLoger.setTestQuickModes(true, true);
        if (AutomataCom.setCheck(commands)) build(Automata.Regime.COMMAND);
    }
}
