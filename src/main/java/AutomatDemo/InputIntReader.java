package AutomatDemo;

import java.io.InputStream;
import java.util.Scanner;

public class InputIntReader {

    Scanner scanner;

    public InputIntReader(InputStream in){
        scanner = new Scanner(in);
    }

    public int getInt(){
        int i = 0;
        try {
            if (scanner.hasNext())
            i = Integer.parseInt(scanner.next());
        }catch (NumberFormatException nfe){
            System.err.println("Введены неверные символы!");
        }
        return i;
    }
}
