package shop;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    TradeShop tradeShop = new TradeShop();
    private static int countOfPosts;

    public static void main(String[] args) {
        Main main = new Main();

        Fruit banana = new Fruit(TypeFruit.BANANA, 12, "2018.10.13", 187);
        Fruit apple = new Fruit(TypeFruit.APPLE, 15, "2018.11.13", 117);
        Fruit avocado = new Fruit(TypeFruit.AVOCADO, 102, "2018.9.13", 19);
        Fruit grape = new Fruit(TypeFruit.GRAPE, 10, "2018.11.9", 57);

        main.addToList(banana);
        main.addToList(apple);
        main.addToList(avocado);
        main.addToList(grape);

        main.addFruit();

        Fruit avocado1 = new Fruit(TypeFruit.AVOCADO, 12, setDate(), 9);
        Fruit grape1 = new Fruit(TypeFruit.GRAPE, 1, setDate(), 7);

        main.addToList(avocado1);
        main.addToList(grape1);

        main.addFruit();

        main.doSave();

        main.load();

        System.out.println("Spoiled fruits: " + main.getSpoiledFruit("2018.11.1"));
        System.out.println("Avaliable fruits: " + main.getAvaliableFruits("2018.11.1"));

    }

    private List<Fruit> getAvaliableFruits(String  date) {
        return tradeShop.getAvaliableFruits(parseDate(date));
    }

    private List<Fruit> getSpoiledFruit(String  date) {
        return tradeShop.getSpoiledFruits(parseDate(date));
    }

    private Date parseDate(String  date) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date dateTmp = null;
        try {
            dateTmp = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTmp;
    }

    private void load() {
        tradeShop.load("src/main/java/shop/allInformation.json");
    }

    private void doSave() {
        try {
            tradeShop.save("src/main/java/shop/allInformation.json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void addToList(Fruit fruit) {
        tradeShop.add(fruit);
    }

    private void addFruit() {
        try {
            tradeShop.addFruit(createPath());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public static String createPath() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        return ("src/main/java/shop/" + "(" + countOfPosts++ + ")" + formatForDateNow.format(date) + ".json");
    }

    public static String createPath(String date) {
        return ("src/main/java/shop/" + date + ".json");
    }

    public static String setDate() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        return formatForDateNow.format(date);
    }
}
