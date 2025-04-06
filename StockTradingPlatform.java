import java.util.*;

public class StockTradingPlatform {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Double> marketData = new HashMap<>();
    static Map<String, Integer> portfolio = new HashMap<>();
    static double balance = 10000.0;  // Initial virtual balance

    public static void main(String[] args) {
        initMarketData();

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 : 
                            viewMarketData();
                            break;
                case 2 :  
                            viewMarketData();
                            
                            buyStock();
                            break;
                case 3 : 
                           sellStock();
                           break;
                case 4 :  
                           viewPortfolio();
                           break;
                case 5 : {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default : System.out.println("Invalid option.");
            }
        }
    }

    static void initMarketData() {
        marketData.put("AAPL", 175.50);
        marketData.put("GOOGL", 2850.25);
        marketData.put("AMZN", 3200.10);
        marketData.put("TSLA", 720.75);
        marketData.put("MSFT", 299.99);
    }

    static void viewMarketData() {
        System.out.println("\nCurrent Market Data:");
        for (String stock : marketData.keySet()) {
            System.out.printf("%s: $%.2f\n", stock, marketData.get(stock));
        }
    }

    static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.next().toUpperCase();

        if (!marketData.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int quantity = scanner.nextInt();
        double price = marketData.get(symbol);
        double totalCost = quantity * price;

        if (totalCost > balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance -= totalCost;
        portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + symbol);
    }

    static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.next().toUpperCase();

        if (!portfolio.containsKey(symbol)) {
            System.out.println("You do not own this stock.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        int owned = portfolio.get(symbol);

        if (quantity > owned) {
            System.out.println("You don't have that many shares.");
            return;
        }

        double price = marketData.get(symbol);
        double totalGain = quantity * price;

        balance += totalGain;
        if (quantity == owned) {
            portfolio.remove(symbol);
        } else {
            portfolio.put(symbol, owned - quantity);
        }

        System.out.println("Sold " + quantity + " shares of " + symbol);
    }

    static void viewPortfolio() {
        System.out.println("\n--- Portfolio ---");
        if (portfolio.isEmpty()) {
            System.out.println("No holdings.");
        } else {
            double totalValue = 0;
            for (String stock : portfolio.keySet()) {
                int qty = portfolio.get(stock);
                double price = marketData.get(stock);
                double value = qty * price;
                totalValue += value;
                System.out.printf("%s: %d shares @ $%.2f = $%.2f\n", stock, qty, price, value);
            }
            System.out.printf("Total portfolio value: $%.2f\n", totalValue);
        }
        System.out.printf("Available balance: $%.2f\n", balance);
    }
}