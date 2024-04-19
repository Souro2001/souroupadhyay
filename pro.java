import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Product {
    private String productName;
    private int productId;
    private double price;
    private int quantityInStock;

    public Product(String productName, int productId, double price, int quantityInStock) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void displayDetails() {
        System.out.println("Product Name: " + productName);
        System.out.println("Product ID: " + productId);
        System.out.println("Price: $" + price);
        System.out.println("Quantity in Stock: " + quantityInStock);
    }
}

class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productName, int productId, double price, int quantityInStock, String brand, int warrantyPeriod) {
        super(productName, productId, price, quantityInStock);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Brand: " + brand);
        System.out.println("Warranty Period: " + warrantyPeriod + " years");
    }
}

class Clothing extends Product {
    private String size;
    private String material;

    public Clothing(String productName, int productId, double price, int quantityInStock, String size, String material) {
        super(productName, productId, price, quantityInStock);
        this.size = size;
        this.material = material;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Size: " + size);
        System.out.println("Material: " + material);
    }
}

class ShoppingCart {
    private ArrayList<Product> cartItems = new ArrayList<>();

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Product product : cartItems) {
                product.displayDetails();
                System.out.println();
            }
        }
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}

class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Electronics to Cart");
            System.out.println("2. Add Clothing to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        System.out.println("Enter Electronics details:");
                        System.out.print("Product Name: ");
                        String electronicsName = scanner.nextLine();
                        System.out.print("Product ID: ");
                        int electronicsId = scanner.nextInt();
                        System.out.print("Price: Rs");
                        double electronicsPrice = scanner.nextDouble();
                        System.out.print("Quantity in Stock: ");
                        int electronicsQuantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Brand: ");
                        String electronicsBrand = scanner.nextLine();
                        System.out.print("Warranty Period (in years): ");
                        int warrantyPeriod = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Electronics electronics = new Electronics(electronicsName, electronicsId, electronicsPrice, electronicsQuantity, electronicsBrand, warrantyPeriod);
                        cart.addToCart(electronics);
                        break;
                    case 2:
                        System.out.println("Enter Clothing details:");
                        System.out.print("Product Name: ");
                        String clothingName = scanner.nextLine();
                        System.out.print("Product ID: ");
                        int clothingId = scanner.nextInt();
                        System.out.print("Price: $");
                        double clothingPrice = scanner.nextDouble();
                        System.out.print("Quantity in Stock: ");
                        int clothingQuantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Size: ");
                        String clothingSize = scanner.nextLine();
                        System.out.print("Material: ");
                        String clothingMaterial = scanner.nextLine();
                        Clothing clothing = new Clothing(clothingName, clothingId, clothingPrice, clothingQuantity, clothingSize, clothingMaterial);
                        cart.addToCart(clothing);
                        break;
                    case 3:
                        cart.displayCart();
                        System.out.println("Total Price: Rs" + cart.calculateTotalPrice());
                        break;
                    case 4:
                        System.out.println("Thank you for shopping with us!");
                        return;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
