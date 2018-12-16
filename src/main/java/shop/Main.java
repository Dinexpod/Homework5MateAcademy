package shop;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static int countOfPosts;

    public static void main(String[] args) throws JsonProcessingException {
        TradeShop tradeShop = new TradeShop();

        Fruit banana = new Fruit(TypeFruit.BANANA, 1, "2018.10.10", 187);
        Fruit apple = new Fruit(TypeFruit.APPLE, 2, "2018.10.10", 117);
        Fruit avocado = new Fruit(TypeFruit.AVOCADO, 3, "2018.10.10", 19);
        Fruit grape = new Fruit(TypeFruit.GRAPE, 10, "2018.10.10", 57);

        tradeShop.add(banana);
        tradeShop.add(apple);
        tradeShop.add(avocado);
        tradeShop.add(grape);

        tradeShop.addFruit(createPath());

        Fruit avocado1 = new Fruit(TypeFruit.BLACKBERRY, 12, setDate(), 9);
        Fruit grape1 = new Fruit(TypeFruit.DURIAN, 10, setDate(), 7);

        tradeShop.add(avocado1);
        tradeShop.add(grape1);

        tradeShop.addFruit(createPath());

        tradeShop.save("src/main/java/shop/allInformation.json");

        tradeShop.load("src/main/java/shop/allInformation.json");

        System.out.println("Spoiled fruits: " + tradeShop.getSpoiledFruits(parseDate("2018.10.12")));
        System.out.println("Avaliable fruits: " + tradeShop.getAvailableFruits(parseDate("2018.10.12")));

        System.out.println("Spoiled fruits (SOME TYPE): " + tradeShop.getSpoiledFruits(parseDate("2018.10.12"), TypeFruit.BANANA));
        System.out.println("Avaliable fruits (SOME TYPE): " + tradeShop.getAvailableFruits(parseDate("2018.10.12"), TypeFruit.AVOCADO));

        System.out.println("EXIST: " + tradeShop.getAddedFruits(parseDate("2018.10.10")));
        System.out.println("EXIST (SOME TYPE): " + tradeShop.getAddedFruits(parseDate("2018.10.12"), TypeFruit.GRAPE));
    }

    private static Date parseDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date dateTmp = null;

        try {
            dateTmp = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTmp;
    }

    public static String createPath() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        return ("src/main/java/shop/" + "(" + (countOfPosts++) + ")" + formatForDateNow.format(date) + ".json");
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
