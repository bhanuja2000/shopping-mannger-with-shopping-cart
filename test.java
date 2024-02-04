import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class test {

    public static void main(String[] args) {
        // Initialize necessary objects and variable
          Map<String, String> hashMap;
               boolean exite=true;
                WestminsterShoppingManager mn=new WestminsterShoppingManager();
                mn.loadfile(); // befor the display funtion load file deatails main arraylist

                 // Main program loop
               while (exite) {
                System.out.println("****WestminsterShoppingManager****");
                System.out.println("Press 1 For Add A new Product");
                System.out.println("press 2 for Remove A product");
                System.out.println("Press 3 for Read a Product List");
                System.out.println("Press 4 for Save File");
                System.out.println("Press 5 for Exite");
                 System.out.println("Press 6 for Open Shooping Menu");
                 // Handling user decisions
                Scanner scanner=new Scanner(System.in);
                String dission=scanner.nextLine();
                if (dission.equals("1")) {
                    System.out.println("Press  1 For Add a Electronic Product");
                    System.out.println("Press 2 for Clothes");
                     String type=scanner.nextLine();
                      if (type.equals("1")){
                        System.out.println("Entred productID");
                         String productid=scanner.nextLine();
                         System.out.println("Entred Product Name");
                         String productname=scanner.nextLine();
                         
                         int avalablitem;
                         while (true) {
                            System.out.println("Entred Product Quntity"); //hadalind valide input
                            if (scanner.hasNextInt()) {
                                avalablitem=scanner.nextInt();
                                break;
                            }
                            else{
                                System.out.println("you Entred Invalid Input");
                                scanner.next();
                            }
                         }
                         scanner.nextLine();
                         System.out.println("Entred Product price");
                         int price=scanner.nextInt();
                         scanner.nextLine();
                         System.out.println("entred brand");
                         String brand=scanner.nextLine();
                         System.out.println("enter warantty");
                         String warantty=scanner.nextLine();
                         Electronics electronics=new Electronics(productid, productname, avalablitem, price, brand, warantty);
                         mn.addANewProduct(electronics);
                         
                      }
                      else if(type.equals("2")){
                           System.out.println("Entred productID");
                           String productid=scanner.nextLine();
                           System.out.println("Entred Product Name");
                          String productname=scanner.nextLine();
                          int avalablitem;
                          while (true) {
                            System.out.println("Entred Product Quntity");
                            if (scanner.hasNextInt()) {                     //valide input
                                avalablitem=scanner.nextInt();
                                break;
                            }
                            else{
                                System.out.println("Invalid Input");
                                scanner.next();
                            }
                          }
                         
                          System.out.println("Entred Product price");
                          int price=scanner.nextInt();
                           scanner.nextLine();
                            System.out.println("entre size");
                            String size=scanner.nextLine();
                           System.out.println("entre color");
                            String color=scanner.nextLine();
                           Clothing clothing=new Clothing(productid, productname, avalablitem, price, size, color);
                            mn.addANewProduct(clothing);

                      }
                      else{
                        System.out.println("You Entred Wrong Number");
                      }
                  }
                  else if (dission.equals("2")) {
                       System.out.println("entre the product ID");
                       String id=scanner.nextLine();
                       mn.filestetus=false;
                       mn.deleteProduct(id);
                       mn.saveFile("/Users/bhanujarathsara/Desktop/uow_Coursework/OOP/hh.txt");
                  }
                  else if (dission.equals("3")) {
                   mn.printProduct();
                  }
                  else if (dission.equals("4")) {
                   mn.saveFile("/Users/bhanujarathsara/Desktop/uow_Coursework/OOP/hh.txt");
                  }
                  else if (dission.equals("5")) {
                   exite=false;
                   System.out.println("System had Termanated");
                  }
                   else if (dission.equals("6")) {
                    System.err.println("Are you new user (yes/no)");
                        String UserDission=scanner.nextLine();
                        if (UserDission.equals("yes")) {
                          System.err.println("enter user name");
                          String username=scanner.nextLine();
                          System.out.println("entre password");
                          String password=scanner.nextLine();
                          User user=new User(username, password);
                          hashMap=new HashMap<>();
                          hashMap.put(user.getUsername(),user.getPassword());
                          savehashmap(hashMap, "/Users/bhanujarathsara/Desktop/uow_Coursework/OOP/hasmapdata.txt");
                           Gui gh=new Gui(mn);
                        }
                        else if (UserDission.equals("no")) {
                          Map<String, String> userData = loadUserDataFromFile("/Users/bhanujarathsara/Desktop/uow_Coursework/OOP/hasmapdata.txt");
                          System.out.print("Enter username: ");
                          String username = scanner.nextLine();
                          System.out.print("Enter password: ");
                          String password = scanner.nextLine();

                          if (validateCredentials(userData, username, password)) {
                            System.out.println("Authentication successful. User data: " + userData.get(username));
                            Gui gh=new Gui(mn);
                        } else {
                            System.out.println("Authentication failed. Invalid username or password.");
                        }
                          
                        }
                       
                   }
                  else{
                    System.out.println("you entred Wrong One Please Input Correct Number");
                  }
                  
           }


   }
          //user detais save to file
   public static void savehashmap(Map<String, String> hashMap, String filename){

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
      for (Map.Entry<String, String> entry : hashMap.entrySet()) {
          writer.write(entry.getKey() + ":" + entry.getValue());
          writer.newLine();
      }
      System.out.println("Data saved to " + filename);
  } catch (IOException e) {
      e.printStackTrace();
  }

   }
   // read rhe user details from file
   private static Map<String, String> loadUserDataFromFile(String filename) {
    Map<String, String> userData = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String username = parts[0];
                String password = parts[1];
                userData.put(username, password);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return userData;
}
private static boolean validateCredentials(Map<String, String> userData, String username, String password) {
  // Check if the username exists and the password matches
  return userData.containsKey(username) && userData.get(username).equals(password);
}

}
