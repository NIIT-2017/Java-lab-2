package Automata;

class Goods {
    private String name;
    private int price;
    private int quantity;
    private String measure;
    private int timeCook;

    /**
     * Конструктор класса
     * @param name наименование продукта
     * @param price стоимость в рублях
     * @param quantity количество
     * @param measure единицы измерения количества
     * @param timeCook время приготовления
     */
    Goods(String name, int price, int quantity, String measure, int timeCook) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.measure = measure;
        this.timeCook = timeCook;
    }

    /**
     * Преобразование объекта в строку
     * @return объект в строковом представлении
     */
    @Override
    public String toString() {
        String strOut;
        if (measure != null) {
            strOut = name + ", " + quantity + " " + measure + ", " + price + " p";

        } else {
            strOut = name + ", " + price + " p";
        }
        return strOut;
    }

    /**
     * Получение названия продукта
     * @return название продукта
     */
    String getName() {
        return name;
    }

    /**
     * Получение стоимости продукта в рублях
     * @return стоимость продукта в рублях
     */
    int getPrice() {
        return price;
    }

    /**
     * Получение количества продукта (литры/штуки/граммы...)
     * @return количество продукта
     */
    int getQuantit() {
        return quantity;
    }

    /**
     * Получение единиц измерения количества продукта
     * @return единица измерения продукта
     */
    String getMeasure() {
        return measure;
    }

    /**
     * Получение времени приготовления продукта в секундах
     * @return время приготовления продукта
     */
    int getTimeCook() {
        return timeCook;
    }
}
