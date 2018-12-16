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
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

        String postage = ("\"fruits\": " + mapper.writeValueAsString(fruitsTmp));

        doPrintWriter(postage, file);

        tmpIndex = fruits.size();
    }

    void save(String pathToJsonFile) throws JsonProcessingException {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String allPostages = mapper.writeValueAsString(fruits);
//        "\"all information\": " +

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
        System.out.println(fruits);
    }

    private void doPrintWriter(String postage, File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(postage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
