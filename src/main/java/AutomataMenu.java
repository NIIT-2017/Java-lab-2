public class AutomataMenu
{
    private static String menu[] = {"Coffee Espresso", "Coffee Americano", "Coffee Cappuccino", "Coffee Glasses"};
    private static double prices[] = {1.59, 2.10, 2.80, 3.28};
    public static int getMenuLength() {
        return menu.length;
    }
    public static String getDrink(int num) {
        return menu[num];
    }
    public static double getPrice(int num) {
        return prices[num];
    }
    public static int getDrinksNum(String title) {
        int num = -1;
        if (title.equals("")) num = -2;
        for(int i=0; i < menu.length; i++) if(title.equals(menu[i])) num = i;
        return num;
    }
    public static double getMinPrice() {
        double minPrice = 0;
        for(int i=0; i < menu.length; i++) if(prices[i] < minPrice) minPrice = prices[i];
        return minPrice;
    }
    public static double getMaxPrice() {
        double maxPrice = 0;
        for(int i=0; i < menu.length; i++) if(prices[i] > maxPrice) maxPrice = prices[i];
        return maxPrice;
    }
}
