import java.util.ArrayList;
import java.io.*;

// WestminsterShoppingManager class implementing the ShoppingManager interface
public class WestminsterShoppingManager implements ShoppingManager{
     // Instance variables
    Product product;
    Clothing cloth;
    Electronics electronics;
    ArrayList<Product>productList;
    BufferedReader reader;
    boolean filestetus=false; //use for file saving mode
    ArrayList<Product>GUiproductList;
    // Constructor initializes
    WestminsterShoppingManager(){
        this.productList=new ArrayList<Product>();
        this.GUiproductList= new ArrayList<Product>();
    }
    // Implementation of the addANewProduct method from the ShoppingManager interface
    public  void addANewProduct(Product product){
        if (productList.size()<=50) {
            
           productList.add(product);
           System.out.println("Product add Successful");
        }
        else{
            System.out.println("cant add not enough spece");
        }
        
        
    }
    // Implementation of the deleteProduct method from the ShoppingManager interface
    public void deleteProduct(String productid){
        boolean found=false;
        for(int i=0;i<productList.size();i++){
            if (productList.get(i).getProductID().equals(productid)) {
                productList.remove(i);
                System.out.println("Product Delete  Success");
                found=true;
                break;
            }
          
            
        }
          if (!found) {
                System.out.println("product not found");
            }
    }
    // Implementation of the printProduct method from the ShoppingManager interface
   public void printProduct(){
    for(int k=0;k<productList.size();k++){
        if (  productList.get(k) instanceof Electronics) {
            Electronics electronics=(Electronics) productList.get(k);
            System.out.println("Electronic Category");
             System.out.println("ProductID= "+productList.get(k).getProductID()+" Product Name="+productList.get(k).getProductName()+" AvalabaleItem="+productList.get(k).getAvailableItem()+" price="+productList.get(k).getPrice()+" Brand="+electronics.getBrand()+" Warantty="+electronics.getWarantty());
        }
        if (productList.get(k) instanceof Clothing) {
            Clothing lClothing=(Clothing) productList.get(k);
            System.out.println("Clothing Category ");
            System.out.println("ProductID= "+productList.get(k).getProductID()+" Product Name="+productList.get(k).getProductName()+" AvalabaleItem="+productList.get(k).getAvailableItem()+" price="+productList.get(k).getPrice()+" Size="+lClothing.getSize()+" Color="+lClothing.getColor());
        }
        
        
    }


    }
   
     // Implementation of the saveFile method 
    public void saveFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,filestetus))) {
            for (Product product : productList) {
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    writer.write(String.format("Electronics,%s,%s,%d,%d,%s,%s", //identifyng electronics items and save with title
                            product.getProductID(), product.getProductName(),
                            product.getAvailableItem(), product.getPrice(),
                            electronics.getBrand(), electronics.getWarantty()));
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    writer.write(String.format("Clothing,%s,%s,%d,%d,%s,%s",    //identifyng cloth items and save with title
                            product.getProductID(),
                            product.getProductName(),
                            product.getAvailableItem(),
                            product.getPrice(),
                            clothing.getSize(),
                            clothing.getColor()));
                }
    
                writer.newLine();
            }
            System.out.println("ProductList successfully saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            e.printStackTrace();
        }
    }
    // Implementation of the loadFile method
    public void loadfile(){
        try {
           reader=new BufferedReader(new FileReader("/Users/bhanujarathsara/Desktop/uow_Coursework/OOP/hh.txt"));
           String line= reader.readLine();
           //String [] filter=line.split(",");
           while (line!=null) {
            String [] filter=line.split(",");
                if (filter[0].equals("Electronics")) {
                    electronics=new Electronics(filter[1],filter[2], Integer.parseInt(filter[3]), Integer.parseInt(filter[4]), filter[5], filter[6]); 
                    productList.add(electronics);
                    GUiproductList.add(electronics);
                    
                }
                else if(filter[0].equals("Clothing")){
                    cloth=new Clothing(filter[1], filter[2], Integer.parseInt(filter[3]),Integer.parseInt(filter[4]), filter[5], filter[6]);
                    productList.add(cloth);
                    GUiproductList.add(cloth);
                }
            
               line=reader.readLine();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

   
    


}
