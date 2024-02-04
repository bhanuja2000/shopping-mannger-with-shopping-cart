public class Clothing  extends Product{

    // Private instance variables to store size and color information
    private String size;
    private String color;

    // Constructor for creating a Clothing object with specified attribute
    public Clothing(String productID, String productName, int availableItem, int price, String size, String color) {
        super(productID, productName, availableItem, price);
        this.size = size;
        this.color = color;
    }
    // Set the size and color specific to Clothing
    public String getSize() {
        return size;
    }
    // Getter method to retrieve the size of the clothin
    public void setSize(String size) {
        this.size = size;
    }
    // Setter method to update the size of the clothing
    public String getColor() {
        return color;
    }
    // Getter method to retrieve the color of the clothing
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return  size + "," +color;
    }
    

}
