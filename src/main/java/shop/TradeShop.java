package shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/
public class TradeShop {
    private List<Fruit> fruits = new ArrayList<>();
    private final static long DAY_IN_MLS = 86_400_000;

    void addFruits(List<Fruit> fruits, String pathToJsonFile) throws IOException {
        this.fruits.addAll(fruits);

        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(file, fruits);
    }

    void save(String pathToJsonFile) throws IOException {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(file, fruits);
    }

    void load(String pathToJsonFile) {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();

        try {
            fruits = Arrays.asList(mapper.readValue(file, Fruit[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Fruit> getAddedFruits(Date date) {
        return fruits.stream().filter(fruit ->
                (fruit.getDate().getTime() / DAY_IN_MLS) == (date.getTime() / DAY_IN_MLS))
                .collect(Collectors.toList());
    }

    List<Fruit> getAddedFruits(Date date, TypeFruit type) {
        return fruits.stream().filter(fruit -> (fruit.getType() == type)
                && ((fruit.getDate().getTime() / DAY_IN_MLS) == (date.getTime() / DAY_IN_MLS)))
                .collect(Collectors.toList());
    }

    List<Fruit> getSpoiledFruits(Date date) {
        return fruits.stream().filter(fruit ->
                ((fruit.getDate().getTime() + (fruit.getShelfLife() * DAY_IN_MLS)) / DAY_IN_MLS)
                        <= ((date.getTime()) / DAY_IN_MLS))
                .collect(Collectors.toList());
    }

    List<Fruit> getAvailableFruits(Date date) {
        return fruits.stream().filter(fruit ->
                ((fruit.getDate().getTime() + (fruit.getShelfLife() * DAY_IN_MLS)) / DAY_IN_MLS)
                > (date.getTime()) / DAY_IN_MLS)
                .collect(Collectors.toList());
    }

    List<Fruit> getSpoiledFruits(Date date, TypeFruit type) {
        return fruits.stream().filter(fruit -> (fruit.getType() == type))
                .filter(fruit ->
                        ((fruit.getDate().getTime() + (fruit.getShelfLife() * DAY_IN_MLS)) / DAY_IN_MLS)
                <= (date.getTime()) / DAY_IN_MLS)
                .collect(Collectors.toList());
    }

    List<Fruit> getAvailableFruits(Date date, TypeFruit type) {
        return fruits.stream().filter(fruit -> (fruit.getType() == type))
                .filter((Fruit fruit) ->
                        ((fruit.getDate().getTime() + (fruit.getShelfLife() * DAY_IN_MLS)) / DAY_IN_MLS)
                > (date.getTime()) / DAY_IN_MLS)
                .collect(Collectors.toList());
    }
}
