package encapsulatewithfactory;

// BEFORE REFACTORING
// Problem: Client code directly instantiates classes with complex logic


// Client code has to know all the details about creating credit cards
class EncapsulateClassesWithFactoryBefore {

    public static void main(String[] args) {
        ClientCode client = new ClientCode();
        client.processApplication("GOLD", 720);
        client.processApplication("PLATINUM", 780);
        client.processApplication("STANDARD", 650);
    }

    // Client code has to know all the details about creating credit cards
    static class ClientCode {
        public void processApplication(String cardType, int creditScore) {
            CreditCard card;

            // Complex creation logic scattered in client code
            if (cardType.equals("GOLD")) {
                if (creditScore >= 700) {
                    card = new CreditCard("GOLD", 5000.0, 0.12);
                } else {
                    card = new CreditCard("GOLD", 2500.0, 0.15);
                }
            } else if (cardType.equals("PLATINUM")) {
                if (creditScore >= 750) {
                    card = new CreditCard("PLATINUM", 10000.0, 0.10);
                } else {
                    throw new IllegalArgumentException("Insufficient credit score for Platinum");
                }
            } else if (cardType.equals("STANDARD")) {
                card = new CreditCard("STANDARD", 1000.0, 0.18);
            } else {
                throw new IllegalArgumentException("Unknown card type");
            }

            System.out.println("Created " + card.getType() + " card with limit: $"
                    + card.getCreditLimit() + " and rate: " + (card.getInterestRate() * 100) + "%");
        }
    }
}
