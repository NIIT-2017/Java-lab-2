import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderTest {

    FileReader testObject = new FileReader ();

    @Test
    public void setMenu() {
        assertArrayEquals(new String[]{"эспрессо", "американо", "кофе с молоком", "капучино", "капучино с шоколадом",
                "мокачино", "чай чёрный", "горячий шоколад", "двойной шоколад", "молочный шоколад"}, testObject.setMenu());
    }

    @Test
    public void setVolume() {
        assertArrayEquals(new int[]{50, 100, 150, 150, 150, 150, 150, 150, 150, 150}, testObject.setVolume());
    }

    @Test
    public void setPrices() {
        assertArrayEquals(new int[]{25, 25, 30, 40, 40, 40, 20, 30, 40, 35}, testObject.setPrices());
    }
}