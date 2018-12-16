package shop;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    TradeShop tradeShop = new TradeShop();
    private static int countOfPosts;

    public static void main(String[] args) {
        Main main = new Main();

        Fruit banana = new Fruit(TypeFruit.BANANA, 12, setDate(), 187);
        Fruit apple = new Fruit(TypeFruit.APPLE, 15, setDate(), 117);
        Fruit avocado = new Fruit(TypeFruit.AVOCADO, 102, setDate(), 19);
        Fruit grape = new Fruit(TypeFruit.GRAPE, 10, setDate(), 57);

        main.addToList(banana);
        main.addToList(apple);
        main.addToList(avocado);
        main.addToList(grape);

//        main.addFruit();

        Fruit avocado1 = new Fruit(TypeFruit.AVOCADO, 12, setDate(), 9);
        Fruit grape1 = new Fruit(TypeFruit.GRAPE, 1, setDate(), 7);

        main.addToList(avocado1);
        main.addToList(grape1);

//        main.addFruit();

        main.doSave();

        main.load();


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
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss");
        return ("src/main/java/shop/" + "(" + countOfPosts++ + ")" + formatForDateNow.format(date) + ".json");
    }

    public static String createPath(String date) {
        return ("src/main/java/shop/" + date + ".json");
    }

    public static String setDate() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy_MM_dd");
        return formatForDateNow.format(date);
    }
}
