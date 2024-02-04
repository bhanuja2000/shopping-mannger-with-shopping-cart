

 //ShoppingManager interface defines the methods that a shopping manager should implement.
public interface ShoppingManager {
   // Adds a new product to the shopping manager.
    void addANewProduct(Product product);
    // Deletes a product from the shopping manager usaing productid
    void deleteProduct(String productid);
    // Dispaly the products 
    void printProduct();
    //Saves the information about the products to a file.
    void saveFile(String filename);
    
    
} 
