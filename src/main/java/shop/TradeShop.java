package shop;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TradeShop {
    private List<Fruit> fruits = new ArrayList<>();
    private static int tmpIndex;

    public void add(Fruit fruit) {
        fruits.add(fruit);
    }

    public void addFruit(String pathToJsonFile) throws JsonProcessingException {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Fruit> fruitsTmp = new ArrayList<>();

        for (int i = tmpIndex; i < this.fruits.size(); i++) {
            fruitsTmp.add(fruits.get(i));
        }

        String postage = mapper.writeValueAsString(fruitsTmp);

        doPrintWriter(postage, file);

        tmpIndex = fruits.size();
    }

    void save(String pathToJsonFile) throws JsonProcessingException {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String allPostages = mapper.writeValueAsString(fruits);

        doPrintWriter(allPostages, file);
    }

    void load(String pathToJsonFile) {
        fruits = null;
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();

        try {
            fruits = Arrays.asList(mapper.readValue(file, Fruit[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doPrintWriter(String postage, File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(postage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Fruit> getSpoiledFruits(Date date) {
        return fruits.stream().filter((Fruit fruit) ->
             (parseData(fruit)).getTime() < date.getTime())
                .collect(Collectors.toList());
    }

    public List<Fruit> getAvaliableFruits(Date date) {
        return fruits.stream().filter((Fruit fruit) ->
                (parseData(fruit)).getTime() >= date.getTime())
                .collect(Collectors.toList());
    }

    private Date parseData(Fruit fruit) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date dateTmp = null;
        try {
            dateTmp = format.parse(fruit.getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTmp;
    }
}
