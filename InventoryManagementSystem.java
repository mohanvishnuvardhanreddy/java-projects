import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagementSystem {
    static ArrayList<String> productNames = new ArrayList<>();
    static ArrayList<Integer> productQuantities = new ArrayList<>();
    static ArrayList<Float> productPrices = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static boolean syncFromCSV(String name) {
        File file = new File("inventory.csv");
        if (!file.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[0].equalsIgnoreCase(name)) {
                    productNames.add(data[0]);
                    productQuantities.add(Integer.parseInt(data[1]));
                    productPrices.add(Float.parseFloat(data[2]));
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        int index = -1;
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1 && syncFromCSV(name)) {
            index = productNames.size() - 1;
        }

        if (index != -1) {
            productQuantities.set(index, productQuantities.get(index) + quantity);
            System.out.println("Product updated!\n");
        } else {
            System.out.print("Enter price per unit: ");
            float price = Float.parseFloat(scanner.nextLine());
            productNames.add(name);
            productQuantities.add(quantity);
            productPrices.add(price);
            System.out.println("Product added!\n");
        }
    }

    public static void viewInventory() {
        if (productNames.isEmpty()) {
            System.out.println("Inventory is empty.\n");
            return;
        }
        System.out.println("Name\tQty\tPrice");
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println(productNames.get(i) + "\t" + productQuantities.get(i) + "\t$" + productPrices.get(i));
        }
    }

    public static void updateProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();
        int index = -1;

        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1 && syncFromCSV(name)) {
            index = productNames.size() - 1;
        }

        if (index != -1) {
            System.out.print("Enter new quantity: ");
            int newQty = Integer.parseInt(scanner.nextLine());
            productQuantities.set(index, newQty);
            System.out.println("Stock updated.\n");
        } else {
            System.out.println("Product not found.\n");
        }
    }

    public static void deleteProduct() {
        System.out.print("Enter name to delete: ");
        String name = scanner.nextLine().trim();
        int index = -1;

        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1 && syncFromCSV(name)) {
            index = productNames.size() - 1;
        }

        if (index != -1) {
            productNames.remove(index);
            productQuantities.remove(index);
            productPrices.remove(index);
            System.out.println("Product deleted.\n");
        } else {
            System.out.println("Product not found.\n");
        }
    }

    public static void searchProduct() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        int index = -1;

        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1 && syncFromCSV(name)) {
            index = productNames.size() - 1;
        }

        if (index != -1) {
            System.out.println(productNames.get(index) + ": Qty " + productQuantities.get(index) + ", Price $" + productPrices.get(index) + "\n");
        } else {
            System.out.println("Product not found.\n");
        }
    }

    public static void saveInventory() throws Exception {
        try (FileWriter writer = new FileWriter("inventory.csv")) {
            writer.write("Product Name,Quantity,Price\n");
            for (int i = 0; i < productNames.size(); i++) {
                writer.write(productNames.get(i) + "," + productQuantities.get(i) + "," + productPrices.get(i) + "\n");
            }
            System.out.println("Inventory saved to inventory.csv\n");
        }
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("1. Add/Update 2. View 3. Update Stock 4. Delete 5. Search 6. Save & Exit");
            String choice = scanner.nextLine();
            if (choice.equals("1")) addProduct();
            else if (choice.equals("2")) viewInventory();
            else if (choice.equals("3")) updateProduct();
            else if (choice.equals("4")) deleteProduct();
            else if (choice.equals("5")) searchProduct();
            else if (choice.equals("6")) { saveInventory(); break; }
        }
    }
}