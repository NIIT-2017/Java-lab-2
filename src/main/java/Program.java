import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;

public class Program {
    public static void main(String[] args) throws IOException {
        String fileDirection;
        if (args.length != 0)
            fileDirection = args[0];
        else
            fileDirection = "./src/main/resources/menu.txt";
        
        try{
            Menu menu1 = new Menu(fileDirection);
            Automata automata1 = new Automata(menu1.getMenu(), menu1.getPrices());
            ButtonsInDisplayOut bdio1 = new ButtonsInDisplayOut(automata1);
            automata1.setBdio(bdio1);

            bdio1.buttonsIn(BUTTONS.ON);
            bdio1.buttonsIn(BUTTONS.OFF);
            bdio1.buttonsIn(BUTTONS.ON);
            bdio1.buttonsIn(BUTTONS.COIN);
            bdio1.buttonsIn(BUTTONS.COIN);
            bdio1.buttonsIn(BUTTONS.CANCEL);
            bdio1.buttonsIn(BUTTONS.COIN);
            bdio1.buttonsIn(BUTTONS.COIN);
            bdio1.buttonsIn(BUTTONS.MENU1);
        }
        catch (NoSuchFileException e){
            System.out.println("File not found");
            System.exit(1);
        }
        catch (InvalidPathException e){
            System.out.println("Wrong direction of file");
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println("File error");
            System.exit(1);
        }
    }
}
