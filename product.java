import java.util.ArrayList;
import java.util.List;

// Custom exception for duplicate product IDs
class DuplicateProductIDException extends Exception {
    public DuplicateProductIDException(String message) {
        super(message);
    }
}

// Custom exception for product not found
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

// Product class
class Product {
    protected String productName;
    protected int productId;
    protected double price;
    protected int quantityInStock;

    public Product(String productName, int productId, double price, int quantityInStock) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public void displayDetails() {
        System.out.println("Product Name: " + productName);
        System.out.println("Product ID: " + productId);
        System.out.println("Price: $" + price);
        System.out.println("Quantity in Stock: " + quantityInStock);
    }
}

// Electronics class
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
        System.out.println("Warranty Period: " + warrantyPeriod + " months");
    }
}

// Clothing class
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

// Shopping cart class
class ShoppingCart {
    private List<Product> cartItems = new ArrayList<>();

    public void addProduct(Product product) throws DuplicateProductIDException {
        for (Product p : cartItems) {
            if (p.productId == product.productId) {
                throw new DuplicateProductIDException("Product with ID " + product.productId + " already exists in the cart.");
            }
        }
        cartItems.add(product);
    }

    public void deleteProduct(int productId) throws ProductNotFoundException {
        boolean found = false;
        for (Product p : cartItems) {
            if (p.productId == productId) {
                cartItems.remove(p);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found in the cart.");
        }
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Product p : cartItems) {
                p.displayDetails();
                System.out.println("------------------------------");
            }
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product p : cartItems) {
            total += p.price;
        }
        return total;
    }
}

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        try {
            // Adding electronics to the cart
            Electronics phone = new Electronics("Smartphone", 1, 799.99, 10, "Apple", 12);
            cart.addProduct(phone);

            // Adding clothing to the cart
            Clothing shirt = new Clothing("T-shirt", 2, 19.99, 20, "M", "Cotton");
            cart.addProduct(shirt);

            // Adding duplicate product (should throw an exception)
            Electronics duplicatePhone = new Electronics("Smartphone", 1, 799.99, 5, "Samsung", 12);
            cart.addProduct(duplicatePhone); // This should throw DuplicateProductIDException
        } catch (DuplicateProductIDException | ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Displaying the cart contents
        cart.displayCart();

        // Calculating and displaying the total price
        System.out.println("Total Price: $" + cart.getTotalPrice());

        // Deleting a product from the cart
        try {
            cart.deleteProduct(2);
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Displaying the updated cart contents
        cart.displayCart();
    }
}

