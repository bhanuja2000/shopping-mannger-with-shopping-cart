
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org. junit.*;
public class WestminsterShoppingManagerTest {
    @Test
    void testAddNewElectronicsProduct() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Electronics electronics = new Electronics("E001", "Laptop", 10, 800, "Sony", "1 year");
        manager.addANewProduct(electronics);
        assertTrue(manager.productList.contains(electronics));
    }
    @Test
    void testAddNewClothingProduct() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Clothing clothing = new Clothing("C001", "T-Shirt", 20, 25, "M", "Blue");
        manager.addANewProduct(clothing);
        assertTrue(manager.productList.contains(clothing));
    }
    @Test
    void testDeleteProduct() {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        Electronics electronics = new Electronics("E001", "Laptop", 10, 800, "Sony", "1 year");
        manager.addANewProduct(electronics);
        manager.deleteProduct("E001");
        assertFalse(manager.productList.contains(electronics));
    }

   

    
}
