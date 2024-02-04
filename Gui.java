import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Gui extends JFrame {
    JPanel panel_1;
    JPanel panel_2;
    JPanel panel_3;
    JComboBox<String> box;
    JLabel labaLabel;
    JButton button;
    JTable table;
    DefaultTableModel tableModel;
    WestminsterShoppingManager manager;
    private JTextArea dTextArea;
    JButton Add_to_shopping_cart_button;
    private ArrayList<String> shoppingCartDetails;
    private int count=1;
    DefaultTableModel tableModel2;
    private ArrayList<Object> productIDS;
     JLabel totalLabel;
    static String tcost;

    public Gui(WestminsterShoppingManager manager) {

        super("Westminster Shopping Centre");
       // System.out.println(box.getSelectedItem());
        this.manager=manager;
        panel_1 = new JPanel();
        panel_2 = new JPanel();
        panel_3 = new JPanel();
        dTextArea=new JTextArea();
        dTextArea.setEditable(false);
        Add_to_shopping_cart_button=new JButton("Add To Shopping Cart");
        shoppingCartDetails=new ArrayList<String>();
        String[] item = {"All", "Electronics", "Cloth"};
        box = new JComboBox<>(item);
        box.setSelectedItem("All");
        labaLabel = new JLabel("Select Product Category");
        button = new JButton("Shopping Cart");
         box.setSelectedIndex(0);
        String Item_type=box.getSelectedItem().toString();
        System.out.println(Item_type);
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) box.getSelectedItem();
                System.out.println("Selected: " + selectedOption);
                updatetable(selectedOption);
            }

        });
        Add_to_shopping_cart_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Object productID = tableModel.getValueAt(selectedRow, 0);
                    Object quantity = 1; // Assuming you always start with a quantity of 1
                    double price = Double.parseDouble(tableModel.getValueAt(selectedRow, 3).toString());
            
                    // Check if the productID already exists in the second table
                    boolean isAdded = false;
                    for (int i = 0; i < tableModel2.getRowCount(); i++) {
                        if (productID.equals(tableModel2.getValueAt(i, 0))) {
                            isAdded = true;
                            int currentQuantity = Integer.parseInt(tableModel2.getValueAt(i, 1).toString());
                            tableModel2.setValueAt(currentQuantity + 1, i, 1);
                            tcost=calculateTotal();
                           System.out.println(calculateTotal()+"line 88");
                            break;
                        }
                    }
            
                    if (!isAdded) {
                        Object[] row = {productID, quantity, price};
                        tableModel2.addRow(row);
                         tcost=calculateTotal();
                        System.out.println(calculateTotal()+"line98");;
                    }
                }
            }
        });
        
        
            
        // updatetable(initialItemType);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER,70,7));
        panel_1.add(labaLabel);
        panel_1.add(box);
        panel_1.add(button);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER,70,7));
        panel_3.add(Add_to_shopping_cart_button);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Info");
        table = new JTable();
       
        table.setModel(tableModel);
        table.setRowHeight(30);
        JScrollPane scrollPane=new JScrollPane(table);
        updatetable(Item_type);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                if (!e.getValueIsAdjusting()) {
                    int selectedrow=table.getSelectedRow();
                    if (selectedrow!=-1) {
                        //displayRowDetails(selectedrow);
                        display(selectedrow,dTextArea,panel_3);
                    }
                }
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                openShoppingcartWindow();
            }
        });

        panel_2.add(scrollPane);
        panel_1.setBackground(Color.GRAY);
        panel_2.setBackground(Color.white);
        panel_3.setBackground(Color.yellow);

        add(panel_1, BorderLayout.NORTH);
        add(panel_2, BorderLayout.CENTER);
        add(panel_3,BorderLayout.SOUTH);
    
       
          //add(panel_3, BorderLayout.SOUTH);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
     
        public void display(int rows,JTextArea area,JPanel panel){
           Object productId=tableModel.getValueAt(rows, 0);
            Object name=tableModel.getValueAt(rows, 1);
            Object category=tableModel.getValueAt(rows,2);
            Object details1=tableModel.getValueAt(rows, 4); 
            StringBuilder stringBuilder=new StringBuilder(); //string builder use for added userk selectde item details
            stringBuilder.append("Selected Product - Details").append("\n");
            stringBuilder.append("Product Id: ").append(productId).append("\n");
            stringBuilder.append("Category: ").append(category).append("\n");
            stringBuilder.append("Name: ").append(name).append("\n");
             if ("Cloth".equalsIgnoreCase(category.toString())) {
                if (details1 instanceof String) {
                    String detailsString = (String) details1;
                    String[] parts = detailsString.split(",");
                    if (parts.length==2) {
                        stringBuilder.append("Size: ").append(parts[0]).append("\n");
                        stringBuilder.append("Color: ").append(parts[1]).append("\n");
                    }else{
                        stringBuilder.append("details: ").append(detailsString).append("\n");
                    }
                }
             }
             else if ("Electronics".equalsIgnoreCase(category.toString())) {
                if (details1 instanceof String) {
                     String detailsString = (String) details1;
                    String[] parts = detailsString.split(",");
                      if (parts.length==2) {
                        stringBuilder.append("Brand: ").append(parts[0]).append("\n");;
                        stringBuilder.append("Warannty: ").append(parts[1]).append("\n");;
                      }else{
                        stringBuilder.append("Details").append(detailsString).append("\n");
                      }

                }
                
             }
            area.setText(stringBuilder.toString());
            panel.add(area);
        }
        
        
        
        private void updatetable(String itemtype){
            tableModel.setRowCount(0);
            for(Product product:manager.GUiproductList){
            Object type="unknown";
            if (product instanceof Electronics) {
                type="Electronics";
            }
            if (product instanceof Clothing) {
                type="Cloth";
            }
            if (itemtype.equals("All") ||
            (itemtype.equals("Electronics") && product instanceof Electronics) ||
            (itemtype.equals("Cloth") && product instanceof Clothing)) {

            Object[] row = {product.getProductID(), product.getProductName(), type, product.getPrice(), product.toString()};
            tableModel.addRow(row);
        }
              else if (itemtype.equals("Electronics" ) &&product instanceof Electronics) {
                if (product instanceof Electronics) {
                     Object [] row={product.getProductID(),product.getProductName(),type,product.getPrice(),product.toString()};
                      tableModel.addRow(row);
                }
                
              }
              else if (itemtype.equals("Cloth")&& product instanceof Clothing) {
                if (product instanceof Electronics) {
                     Object [] row={product.getProductID(),product.getProductName(),type,product.getPrice(),product.toString()};
                      tableModel.addRow(row);
                }
               
                }
            

        }
            

        }
        // for Dispaly Shopping cart window
        public void openShoppingcartWindow(){
        
           
            
            JFrame cartWindow= new JFrame("Shopping Cart");
            JPanel panel1=new JPanel();// for tabale
            JPanel pJPanel2= new JPanel(); // for cost details
            cartWindow.setSize(800,800);
            cartWindow.setLocationRelativeTo(null);
            cartWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cartWindow.setVisible(true);

           tableModel2=new DefaultTableModel();
            tableModel2.addColumn("Product");
            tableModel2.addColumn("Quntity");
            tableModel2.addColumn("Price");
            
        
           
            JTable carTable=new JTable(tableModel2);
            JScrollPane scrollPane=new JScrollPane(carTable);
            panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
            panel1.setBackground(Color.lightGray);
            panel1.add(scrollPane);
            totalLabel=new JLabel("total cost: " +tcost);
            pJPanel2.add(totalLabel);
        
            cartWindow.add(panel1,BorderLayout.NORTH);
            cartWindow.add(pJPanel2,BorderLayout.SOUTH);
            cartWindow.setVisible(true);
        }
        public String calculateTotal() {
            double totalCost = 0.0;
        
            for (int i = 0; i < tableModel2.getRowCount(); i++) {
                Object itemCostObject = tableModel2.getValueAt(i, 2);
                Object itemQuantityObject = tableModel2.getValueAt(i, 1);
        
                double itemCost = 0.0;
                int itemQuantity = 0;
        
                // Check  the objects are of the expected types
                if (itemCostObject instanceof Double) {
                    itemCost = (Double) itemCostObject;
                }
        
                if (itemQuantityObject instanceof Integer) {
                    itemQuantity = (Integer) itemQuantityObject;
                }
        
                // the total cost
                totalCost += itemCost * itemQuantity;
            }
                totalLabel.setText("Your Total Cost: "+tcost);
            // Format total cost with "$" sign
            return String.format("$%.2f", totalCost);
        }
        
        
        

}
