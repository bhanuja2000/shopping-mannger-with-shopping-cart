import java.util.ArrayList;

public class ShoppingCart {
    
   private Product product;
   private ArrayList<Product>addproduct;
   int cost;

public ShoppingCart(Product product, ArrayList<Product> addproduct) {
    this.product = product;
    this.addproduct = addproduct;
}
public void addproduct(String productID, String productName, int availableItem, int price ){
    product=new Product(productID, productName, availableItem, price);
    addproduct.add(product);
}
public void removeProduct(String productID){
    for(int i=0;i<addproduct.size();i++){
        if (addproduct.get(i).getProductID().equals(productID)) {
            addproduct.remove(i);
        }
    }

}
public void totalcost(){
if (addproduct!=null) {
    
    for(int k=0;k<addproduct.size();k++){
        cost=cost+addproduct.get(k).getPrice();
    }
}
else{
    System.err.println("you dont have add any product");
}
}
}
