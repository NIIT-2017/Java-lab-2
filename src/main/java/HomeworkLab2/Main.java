package HomeworkLab2;
public class Main {
   public static  void main(String argv[])
    {
        Automata vendor=new Automata();
        System.out.println(vendor.getState());
        vendor.on();
        System.out.println(vendor.getState());
        vendor.coin(5);
        System.out.println(vendor.getState());
        vendor.cancel();
        System.out.println(vendor.getState());
        vendor.coin(5);
        System.out.println(vendor.getState());
        vendor.choice("Tea");
        System.out.println(vendor.getState());
        vendor.cook();
        System.out.println(vendor.getState());
        vendor.finish();
        System.out.println(vendor.getState());
    }
}
