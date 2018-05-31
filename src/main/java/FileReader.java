import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class FileReader {
    private String menu []; // массив строк названий напитков
    private int volume[]; // массив объёма порций напитков (соттветствует массиву menu)
    private int prices []; // массив цен напитков (соттветствует массиву menu)

    FileReader () {
        parserXML(); // запускаем парсинг файла
    }

    private class MyResource implements AutoCloseable {

        // вспомогательный класс для использования  try with resources
        // и прописывания путей к файлам

        File fileXML = new File("./src/main/resources/coffeeMachineMenu.xml");

        File schemaLocation = new File("./src/main/resources/menuSchema.xsd");

        public void close() throws Exception {
            //System.out.println("Closing!");
        }
    }


    private void parserXML () { // чтение из XML файла, должна быть приватной

        try(MyResource MyFile = new MyResource()) { // создаём объект служебного класса
            /*MyFile будет содержать пути к файлам и интерфейс AutoCloseable*/

            /***********************************Валидация файла*********************************/

            /*Поиск и создание экземпляра фабрики для языка XML Schema*/
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            // Компиляция схемы
            // Схема загружается в объект типа java.io.File,
            // но можно так же использовать  классы java.net.URL и javax.xml.transform.Source
            //File schemaLocation = new File("./src/main/resources/menuSchema.xsd"); ///!!!
            Schema schema = null;
            try {
                schema = factory.newSchema(MyFile.schemaLocation);
            } catch (SAXException e) {
                e.printStackTrace();
            }

            /*Создание валидатора для схемы*/
            Validator validator = schema.newValidator();

            /*Разбор проверяемого документа*/
            Source source = new StreamSource(MyFile.fileXML);

            /*Валидация документа*/
            try {
                try {
                    validator.validate(source);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(MyFile.fileXML + " is valid.");
            } catch (SAXException ex) {
                System.out.println(MyFile.fileXML + " is not valid because ");
                System.out.println(ex.getMessage());
            }

            /**********************************Чтение данных из файла***************************************/

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // создали фабрику "строителей"

            try {
                DocumentBuilder db = dbf.newDocumentBuilder(); // создали конкретного "строителя" документа
                Document doc = db.parse(MyFile.fileXML); // "строитель" построил документ

                NodeList nodeList = doc.getElementsByTagName("item"); // возвращает "список узлов" всех элементов с именем item

                /*создаём массивы с количеством элементов равным количеству нодов item*/
                this.menu = new String[nodeList.getLength()];
                this.volume = new int[nodeList.getLength()];
                this.prices = new int[nodeList.getLength()];

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i); // текущий нод

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        // getNodeType() - возращает тип node
                        //ELEMENT_NODE - это конкретный тип node (здесь это item), может иметь потомков (в отличае от TEXT_NODE)

                        Element element = (Element) node;

                        /*заполняем массивы с названием напитка объёма и цены*/
                        this.menu[i] = element.getElementsByTagName("name").item(0).getTextContent();
                        this.volume[i] = parseInt(element.getElementsByTagName("volume").item(0).getTextContent());
                        this.prices[i] = parseInt(element.getElementsByTagName("price").item(0).getTextContent());
                    }
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String[] setMenu () {
        return Arrays.copyOf(this.menu, this.menu.length);
    }

    public int[] setVolume () {
        return Arrays.copyOf(this.volume, this.volume.length);
    }

    public int[] setPrices () {
        return Arrays.copyOf(this.prices, this.prices.length);
    }

}
