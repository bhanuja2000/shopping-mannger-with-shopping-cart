public class Electronics extends Product {
    
private String brand;
private String warantty;
public Electronics(String productID, String productName, int availableItem, int price,String brand,String warantty) {
   super(productID, productName, availableItem, price);
    this.brand = brand;
    this.warantty = warantty;

}
public String getBrand() {
    return brand;
}
public void setBrand(String brand) {
    this.brand = brand;
}
public String getWarantty() {
    return warantty;
}
public void setWarantty(String warantty) {
    this.warantty = warantty;
}
@Override
public String toString() {
    return  brand + "," + warantty ;
}


}
