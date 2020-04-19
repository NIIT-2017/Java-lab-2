import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private ArrayList<String> menu;
    private ArrayList<Double> prices;
//constructor
    Menu(String fileDirection) throws IOException {
        List<String> lines = Collections.EMPTY_LIST;
        this.menu = new ArrayList<String>();
        this.prices = new ArrayList<Double>();
        lines = Files.readAllLines(Paths.get(fileDirection));

        for (String line:lines) {
            this.menu.add(line);
            String[] pr = line.split(" ",2);
            this.prices.add(Double.parseDouble(pr[1]));
        }
    }
//getters
    ArrayList<String> getMenu(){return this.menu;}
    ArrayList<Double> getPrices(){return this.prices;}
}
