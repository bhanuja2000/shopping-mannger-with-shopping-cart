/**
 * Product
 */
public class Product {

     //  instance variables to store product information
private String productID;
private String  productName;
private int availableItem;
private int price;
// Constructor to initialize a Product object with specified attributes
public Product(String productID, String productName, int availableItem, int price) {
    this.productID = productID;
    this.productName = productName;
    this.availableItem = availableItem;
    this.price = price;
}
// Getter,setter and to string methods
public String getProductID() {
    return productID;
}
public void setProductID(String productID) {
    this.productID = productID;
}
public String getProductName() {
    return productName;
}
public void setProductName(String productName) {
    this.productName = productName;
}
public int getAvailableItem() {
    return availableItem;
}
public void setAvailableItem(int availableItem) {
    this.availableItem = availableItem;
}
public int getPrice() {
    return price;
}
public void setPrice(int price) {
    this.price = price;
}
@Override
public String toString() {
    return "Product [productID=" + productID + ", productName=" + productName + ", availableItem=" + availableItem
            + ", price=" + price + "]";
}

    
}