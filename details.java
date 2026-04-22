import java.util.*;
import java.io.*;

//Product Class
class Product {
    int id;
    String name;
    String category;
    double price;
    int quantity;

    public Product(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return id + "," + name + "," + category + "," + price + "," + quantity;
    }
}

// 🔹 File Handling
class FileHandler {
    static String fileName = "products.txt";

    public static void writeToFile(Collection<Product> products) {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (Product p : products) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public static List<Product> readFromFile() {
        List<Product> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                Product p = new Product(
                        Integer.parseInt(d[0]),
                        d[1],
                        d[2],
                        Double.parseDouble(d[3]),
                        Integer.parseInt(d[4])
                );

                list.add(p);
            }
        } catch (IOException e) {
            System.out.println("Starting fresh...");
        }

        return list;
    }
}

// 🔹 Inventory Class

class Inventory {

    List<Product> products;

    public Inventory() {
        products = FileHandler.readFromFile(); 
    }

    // Add
    public void add(Product p) {
        for (Product pr : products) {
            if (pr.id == p.id) {
                System.out.println("Duplicate ID!");
                return;
            }
        }

        if (p.price < 0 || p.quantity < 0) {
            System.out.println("Invalid Entry!");
            return;
        }

        products.add(p);
        FileHandler.writeToFile(products);
        System.out.println("Product added!");
    }

    // View
    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products.");
            return;
        }

        for (Product p : products) {
            System.out.println(p.id + " | " + p.name + " | " +
                    p.category + " | " + p.price + " | " + p.quantity);
        }
    }

    // Search
    public void searchProduct(int id) {
        for (Product p : products) {
            if (p.id == id) {
                System.out.println("Found: " + p.name);
                return;
            }
        }
        System.out.println("Not found.");
    }

    // Update
    public void updateProduct(int id, double price, int quantity) {
        for (Product p : products) {
            if (p.id == id) {
                p.price = price;
                p.quantity = quantity;
                FileHandler.writeToFile(products);
                System.out.println("Updated!");
                return;
            }
        }
        System.out.println("ID Not found.");
    }

    // Delete
    public void deleteProduct(int id) {
        Iterator<Product> it = products.iterator();

        while (it.hasNext()) {
            Product p = it.next();
            if (p.id == id) {
                it.remove();
                FileHandler.writeToFile(products);
                System.out.println("Deleted!");
                return;
            }
        }
        System.out.println("Not found.");
    }


    // Low Stock Check
    public List<String> getLowStockProducts() {
    List<String> lowList = new ArrayList<>();

    for (Product p : products) {
        if (p.quantity < 5) {
            lowList.add(p.name);
        }
    }

    return lowList;
}
}

// Thread Class (Silent)
class LowStockMonitor extends Thread {
    private Inventory inv;

    public LowStockMonitor(Inventory inv) {
        this.inv = inv;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10000); // every 10 sec
                inv.getLowStockProducts(); 
            } catch (InterruptedException e) {
                System.out.println("Thread stopped");
            }
        }
    }
}

// 🔹 Main Class
public class details {
    public static void main(String[] args) {

        Inventory inv = new Inventory();

        //  thread
        LowStockMonitor monitor = new LowStockMonitor(inv);
        monitor.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add 2.View 3.Search 4.Update 5.Delete 6.Exit");

            try {
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Category: ");
                        String cat = sc.nextLine();

                        System.out.print("Price: ");
                        double price = sc.nextDouble();

                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();

                        inv.add(new Product(id, name, cat, price, qty));
                        break;

                    case 2:
                        inv.viewProducts();
                        List<String> low = inv.getLowStockProducts();

                if (!low.isEmpty()) {
                    System.out.println("⚠ Low stock items: " + String.join(", ", low));
                }
                        break;

                    case 3:
                        System.out.print("Enter ID: ");
                        inv.searchProduct(sc.nextInt());
                        break;

                    case 4:
                        System.out.print("Enter ID: ");
                        int uid = sc.nextInt();

                        System.out.print("New Price: ");
                        double up = sc.nextDouble();

                        System.out.print("New Quantity: ");
                        int uq = sc.nextInt();

                        inv.updateProduct(uid, up, uq);
                        break;

                    case 5:
                        System.out.print("Enter ID: ");
                        inv.deleteProduct(sc.nextInt());
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }

            

            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
                sc.nextLine();
            }
        }
    }
}