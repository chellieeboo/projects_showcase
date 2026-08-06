public class PolymorphismDemo {
    
    public static void main(String[] args) {
        System.out.println("🔄 POLYMORPHISM WITH INTERFACES");
        System.out.println("=".repeat(40));
        
        // Create objects
        Payment creditCard = new CreditCardPayment("Maria Santos", "1234-5678-9012-3456");
        Payment paypal = new PayPalPayment("maria@gmail.com");
        Payment gcash = new GCashPayment("09171234567");
        
        // Process payments (all through the same interface)
        System.out.println("\n💳 CREDIT CARD:");
        creditCard.processPayment(1500.50);
        
        System.out.println("\n📧 PAYPAL:");
        paypal.processPayment(750.00);
        
        System.out.println("\n📱 GCASH:");
        gcash.processPayment(250.75);
        
        // Show list of all payments
        System.out.println("\n📋 PAYMENT SUMMARY:");
        Payment[] payments = {creditCard, paypal, gcash};
        for (Payment p : payments) {
            p.showDetails();
        }
        
        // Show total amount
        System.out.println("\n💵 TOTAL AMOUNT PROCESSED:");
        double total = 0;
        for (Payment p : payments) {
            total += p.getLastAmount();
        }
        System.out.printf("Total: ₱%.2f\n", total);
    }
}

/**
 * INTERFACE: Payment
 * Defines what all payment methods must implement
 */
interface Payment {
    // Abstract methods (must be implemented by all classes)
    void processPayment(double amount);
    void showDetails();
    double getLastAmount(); // Returns last processed amount
}

/**
 * CLASS: CreditCardPayment
 * Implements Payment interface
 */
class CreditCardPayment implements Payment {
    private String cardHolderName;
    private String cardNumber;
    private double lastAmount;
    
    public CreditCardPayment(String cardHolderName, String cardNumber) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("💰 Processing Credit Card Payment...");
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: " + maskCardNumber(cardNumber));
        System.out.printf("Amount: ₱%.2f\n", amount);
        System.out.println("✅ Payment Successful!");
        this.lastAmount = amount;
    }
    
    @Override
    public void showDetails() {
        System.out.println("Credit Card: " + maskCardNumber(cardNumber));
        System.out.println("Amount: ₱" + String.format("%.2f", lastAmount));
    }
    
    @Override
    public double getLastAmount() {
        return lastAmount;
    }
    
    // Helper method to hide card number
    private String maskCardNumber(String number) {
        if (number.length() >= 4) {
            return "****-****-****-" + number.substring(number.length() - 4);
        }
        return "****";
    }
}

/**
 * CLASS: PayPalPayment
 * Implements Payment interface
 */
class PayPalPayment implements Payment {
    private String email;
    private double lastAmount;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("💰 Processing PayPal Payment...");
        System.out.println("Email: " + email);
        System.out.printf("Amount: ₱%.2f\n", amount);
        System.out.println("✅ Payment Successful!");
        this.lastAmount = amount;
    }
    
    @Override
    public void showDetails() {
        System.out.println("PayPal: " + email);
        System.out.println("Amount: ₱" + String.format("%.2f", lastAmount));
    }
    
    @Override
    public double getLastAmount() {
        return lastAmount;
    }
}

/**
 * CLASS: GCashPayment
 * Implements Payment interface
 */
class GCashPayment implements Payment {
    private String phoneNumber;
    private double lastAmount;
    
    public GCashPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("💰 Processing GCash Payment...");
        System.out.println("Mobile Number: " + phoneNumber);
        System.out.printf("Amount: ₱%.2f\n", amount);
        System.out.println("✅ Payment Successful!");
        this.lastAmount = amount;
    }
    
    @Override
    public void showDetails() {
        System.out.println("GCash: " + phoneNumber);
        System.out.println("Amount: ₱" + String.format("%.2f", lastAmount));
    }
    
    @Override
    public double getLastAmount() {
        return lastAmount;
    }
}
