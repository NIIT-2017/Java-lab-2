import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String str = "10";
        InputStream in = new ByteArrayInputStream(str.getBytes());
        Scanner sc = new Scanner(in);
        System.out.println(sc.next());
    }
}
