package HomeworkLab2;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
public class Automata {
    enum STATES{Off,Wait,Accept,Check,Cook};
    private int cash=0;
    private int drinkIndex;
    private ArrayList<String> menu;
    private ArrayList<Integer> prices;
    private STATES state=STATES.Off;
    private Boolean checkResult=false;
    private void loadMenu(String fileName) throws Exception
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            menu=new ArrayList<String>();
            prices=new ArrayList<Integer>();
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] splitedLine = line.split(" ");
                String name = splitedLine[0];
                menu.add(name);
                int price = Integer.parseInt(splitedLine[1]);
                prices.add(price);
            }
            if(menu.size()==0)
                throw new Exception("File is empty");
            if(prices.size()!=menu.size())
                throw  new Exception("File is incomplete");

        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public void on()
    {
        try
        {
            loadMenu("Resources/Menu.txt");
            if(state==STATES.Off)
            {
                state=STATES.Wait;
            }
        }
        catch (Exception ex)
        {
        }
    }
    public  void off()
    {
        if(state==STATES.Wait)
        {
            state=STATES.Off;
        }
    }
    public void coin(int money)
    {
        if(state==STATES.Wait||state==STATES.Accept)
        {
            cash += money;
            state=STATES.Accept;
        }
    }
    public ArrayList<String> getMenu()
    {
        return  menu;
    }
    public STATES getState()
    {
        return  state;
    }
    public void choice(String name)
    {
        checkResult=false;
        for(int i=0;i<menu.size();i++)
        {
            if(!(name==menu.get(i)))
            {
                checkResult = check(i);
                drinkIndex=i;
                break;
            }
        }
    }
    private Boolean check(int nameIndex)
    {
        state=STATES.Check;
        if(prices.get(nameIndex)<=cash)
            return  true;
        else
            return  false;
    }
    public int cancel()
    {int tmpCash=0;
        if(state==STATES.Accept||state==STATES.Check)
        {
            state=STATES.Wait;
            tmpCash=cash;
            cash = 0;
        }
        return  tmpCash;
    }
    public  Drink cook( )
    {
        state=STATES.Cook;
        cash-=prices.get(drinkIndex);
        return new Drink(menu.get(drinkIndex));
    }
    public int finish()
    {
        state=STATES.Wait;
        cash=0;
        return cash;
    }
}
