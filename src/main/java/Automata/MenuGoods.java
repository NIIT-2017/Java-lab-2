package Automata;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * Класс меню, предназначен для хранения информации о продуктах и оперированию ей
 */
class MenuGoods {
    private Goods[] meMenu;
    MenuGoods(String fileName)  {
        Gson gson = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File is not!!!");
            e.printStackTrace();
        }
        meMenu = gson.fromJson(reader, Goods[].class);
    }

    MenuGoods()  {
        URL file = getClass().getResource("/menu.json");
        Gson gson = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(file.getFile())));
        } catch (FileNotFoundException e) {
            System.out.println("File is not!!!");
            e.printStackTrace();
        }
        meMenu = gson.fromJson(reader, Goods[].class);
    }

    /**
     * Получение строкового представления объекта меню
     * @return меню в строковом представлении
     */
    @Override
    public String toString(){
        String strOut = "";
        for (int i = 0; i < meMenu.length; i++){
            strOut += (i + 1) + ". " + meMenu[i].toString() + "\n";
        }
        return strOut;
    }

    /**
     * Получение количества наименований продуктов которое содержит меню
     * @return количество наименований продуктов которое содержит меню
     */
    int getSizeMenu(){
        return meMenu.length;
    }

    /**
     * Получение стоимости продукта имеющего переданное id
     * @param id id прдукта
     * @return стоимость продукта
     */
    public int getPrice(int id){
        if(id <= meMenu.length) {
            return meMenu[id - 1].getPrice();
        } else {
            return 0;
        }
    }

    /**
     * Получение наименования(названия) продукта
     * @param id id продукта наименование(название) которого нужно получить
     * @return название/наименование продукта
     */
    public String getName(int id){
        if(id <= meMenu.length) {
            return meMenu[id - 1].getName();
        } else{
            return null;
        }
    }

    /**
     * Получение времени приготовления/подготовки продукта
     * @param id id продукта время приготовления/подготовки требуется получить
     * @return время приготовления/подготовки продукта
     */
    public int getTimeCook(int id){
        if(id <= meMenu.length) {
            return meMenu[id - 1].getTimeCook();
        } else {
            return 0;
        }
    }
}