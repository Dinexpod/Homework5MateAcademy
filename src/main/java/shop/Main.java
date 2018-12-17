package shop;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    private static int countOfPosts;

    public static void main(String[] args) throws IOException {
        TradeShop tradeShop = new TradeShop();

        Fruit banana = new Fruit(TypeFruit.BANANA, 1, (new Date()), 187);
        Fruit apple = new Fruit(TypeFruit.APPLE, 1, (new Date()), 117);
        Fruit avocado = new Fruit(TypeFruit.AVOCADO, 3, (new Date()), 19);
        Fruit grape = new Fruit(TypeFruit.GRAPE, 10, (new Date()), 57);

        tradeShop.add(banana);
        tradeShop.add(apple);
        tradeShop.add(avocado);
        tradeShop.add(grape);

        tradeShop.addFruit(createPath());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, (2 - 1), (16 + 1));
        Date date = calendar.getTime();

        Fruit avocado1 = new Fruit(TypeFruit.BLACKBERRY, 12, date, 9);
        Fruit grape1 = new Fruit(TypeFruit.DURIAN, 10, date, 7);

        tradeShop.add(avocado1);
        tradeShop.add(grape1);

        tradeShop.addFruit(createPath());

        tradeShop.save("src/main/java/shop/allInformation.json");

        tradeShop.load("src/main/java/shop/allInformation.json");

        System.out.println("\nSpoiled fruits: " + tradeShop.getSpoiledFruits(date));
        System.out.println("Avaliable fruits: " + tradeShop.getAvailableFruits(date));

        System.out.println("\nSpoiled fruits (SOME TYPE): " + tradeShop.getSpoiledFruits(date, TypeFruit.BANANA));
        System.out.println("Avaliable fruits (SOME TYPE): " + tradeShop.getAvailableFruits(date, TypeFruit.AVOCADO));

        System.out.println("\nEXIST: " + tradeShop.getAddedFruits(date));
        System.out.println("EXIST (SOME TYPE): " + tradeShop.getAddedFruits(date, TypeFruit.BLACKBERRY));
    }

    public static String createPath() {
        countOfPosts++;
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        return ("src/main/java/shop/" + "(" + countOfPosts + ")" + formatForDateNow.format(date) + ".json");
    }
}
