package shop;

public class Fruit {
    private TypeFruit type;
    private int shelfLife;
    private String postDate;
    private double price;

    public Fruit(TypeFruit type, int shelfLife, String postDate, double price) {
        this.type = type;
        this.shelfLife = shelfLife;
        this.postDate = postDate;
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
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
                ", postDate='" + postDate + '\'' +
                ", price=" + price +
                '}';
    }
}
