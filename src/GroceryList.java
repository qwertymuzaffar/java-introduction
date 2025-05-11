import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroceryList {

    // Task 1: String array of items
    static String[] items = {
            "Milk",
            "Eggs",
            "Bread",
            "Butter",
            "Cheddar Cheese",
            "Apples",
            "Bananas",
            "Tomatoes",
            "Potatoes",
            "Onions",
            "Chicken Breast",
            "Rice"
    };

    // Task 2: float array of unit price
    static float[] unitPrices = {
            2.49f,  // Milk
            1.99f,  // Eggs
            2.49f,  // Bread
            3.49f,  // Butter
            4.99f,  // Cheddar Cheese
            0.99f,  // Apples
            0.59f,  // Bananas
            1.29f,  // Tomatoes
            0.79f,  // Potatoes
            0.69f,  // Onions
            5.99f,  // Chicken Breast
            2.49f   // Rice
    };

    static int[] inventory = {
            10,  // Milk
            12,  // Eggs
            15,  // Bread
            20,  // Butter
            5,   // Cheddar Cheese
            30,  // Apples
            25,  // Bananas
            20,  // Tomatoes
            50,  // Potatoes
            40,  // Onions
            10,  // Chicken Breast
            20   // Rice
    };

    public static void main(String[] args) {

        // Task 3: Import and create a Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Grocery Shop!");

        // Task 4: Create infinite loop
        while (true) {
            System.out.println("\nType 'Exit' to quit or press Enter to shop:");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for shopping with us. Goodbye!");
                break;
            }

            float totalBill = 0.0f;

            // Task 5: Create for inner infinite loop for purchases
            while (true) {

                // Task 6: Get user input for item name
                System.out.println("\nEnter item name (or 'Complete' to finish):");
                String entry = scanner.nextLine();
                if (entry.equalsIgnoreCase("complete")) {
                    System.out.println("Order complete.");
                    break;
                }

                // Task 10: Implement Item Search Functionality
                int selectedIndex = searchItem(entry);

                if (selectedIndex < 0) {
                    System.out.println("Sorry, we don’t carry \"" + entry + "\". Please try again.");
                    continue;
                }

                if (inventory[selectedIndex] <= 0) {
                    System.out.println("Sorry, " + items[selectedIndex] + " is out of stock.");
                    continue;
                }

                // Task 7: Get the item price based on the index position
                String selectedItem = items[selectedIndex];
                float unitPrice = unitPrices[selectedIndex];

                String priceStr = String.format("%.2f", unitPrices[selectedIndex]);
                System.out.println("Unit price for " + items[selectedIndex] + ": $" + priceStr);
                System.out.println("Available stock: " + inventory[selectedIndex]);

                // Task 8: Get the item quantity and find item price
                System.out.print("Enter quantity for " + selectedItem + ": ");
                int quantity;

                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
                if (quantity < 1) {
                    System.out.println("Quantity must be at least 1.");
                    continue;
                }
                if (quantity > inventory[selectedIndex]) {
                    System.out.println("Only " + inventory[selectedIndex] + " items available.");
                    continue;
                }

                // compute this item’s total
                float itemTotal = unitPrice * quantity;

                // add to grand total
                totalBill += itemTotal;

                // Task 9: Print the total price
                System.out.println(
                        quantity
                                + " × " + selectedItem
                                + " @ $" + String.format("%.2f", unitPrice)
                                + " each = $" + String.format("%.2f", itemTotal)
                );

                // Task 13: Total the Bill with Discounts
                float discountedTotal = applyDiscount(totalBill);

                System.out.println("\nYour total bill: $" + String.format("%.2f", totalBill));
                if (discountedTotal < totalBill) {
                    System.out.println(
                            "Discount applied! New total: $"
                                    + String.format("%.2f", discountedTotal)
                    );
                }

                // Task 11: Calculate Average Price
                System.out.println(
                        "Average unit price: $"
                                + String.format("%.2f", calculateAveragePrice())
                );

                // Task 12: Filter Items Below a Certain Price
                System.out.print(
                        "Enter a price threshold to list items below that price"
                                + " (or press Enter to skip): "
                );
                String thresh = scanner.nextLine();
                if (!thresh.isEmpty()) {
                    try {
                        float threshold = Float.parseFloat(thresh);
                        List<String> below = filterItemsBelowPrice(threshold);
                        System.out.println(
                                "Items priced below $" + String.format("%.2f", threshold)
                                        + ": " + below
                        );
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid threshold; skipping filter.");
                    }
                }

                // Task 14: Inventory Management
                System.out.println("\nCurrent inventory:");
                for (int i = 0; i < items.length; i++) {
                    System.out.println(items[i] + ": " + inventory[i]);
                }
            }
        }

        scanner.close();
    }

    // Task 10: Implement Item Search Functionality
    public static int searchItem(String name) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Task 11: Calculate Average Price
    public static float calculateAveragePrice() {
        float sum = 0.0f;
        for (float p : unitPrices) {
            sum += p;
        }
        return sum / unitPrices.length;
    }

    // Task 12: Filter Items Below a Certain Price
    public static List<String> filterItemsBelowPrice(float price) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            if (unitPrices[i] < price) {
                String entry = items[i]
                        + " ($" + String.format("%.2f", unitPrices[i]) + ")";
                result.add(entry);
            }
        }
        return result;
    }

    // Task 13: Total the Bill with Discounts
    public static float applyDiscount(float total) {
        float rate = 0.0f;
        if (total > 200.0f) {
            rate = 0.15f;      // 15% off over $200
        } else if (total > 100.0f) {
            rate = 0.10f;      // 10% off over $100
        } else if (total > 50.0f) {
            rate = 0.05f;      // 5% off over $50
        }
        return total * (1 - rate);
    }
}
