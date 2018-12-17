package shop;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Fruit {
    private TypeFruit type;
    private int shelfLife;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date date;
    private double price;

    public Fruit(TypeFruit type, int shelfLife, Date date, double price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
    }

    public Fruit() {
    }

    public TypeFruit getType() {
        return type;
    }

    public void setType(TypeFruit type) {
        this.type = type;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "type=" + type +
                ", shelfLife=" + shelfLife +
                ", postDate='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
