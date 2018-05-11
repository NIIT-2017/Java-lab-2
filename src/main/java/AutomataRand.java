import java.util.Random;

public class AutomataRand
{
    public static int randNum(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
    public static double randCoin(double min, double max) {
        return min + ((max - min) * new Random().nextDouble());
    }
    public static int getIntMin() {
        return (int)AutomataMenu.getMinPrice() + 1;
    }
    public static int getIntMax() {
        return (int)AutomataMenu.getMaxPrice();
    }
}
