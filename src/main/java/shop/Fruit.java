package shop;

public class Fruit {
    private TypeFruit type;
    private int shelfLife;
    private String  data;
    private double price;

    public Fruit(TypeFruit type, int shelfLife, String data, double price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
                ", data='" + data + '\'' +
                ", price=" + price +
                '}';
    }
}
