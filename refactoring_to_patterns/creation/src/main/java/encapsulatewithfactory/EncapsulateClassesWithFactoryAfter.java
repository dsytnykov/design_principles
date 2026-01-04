package encapsulatewithfactory;

// AFTER REFACTORING
// Solution: Factory encapsulates creation logic, making it easier to maintain and extend
public class EncapsulateClassesWithFactoryAfter {

    public static void main(String[] args) {
        ClientCode client = new ClientCode();

        client.processApplication("GOLD", 720);
        client.processApplication("PLATINUM", 780);
        client.processApplication("STANDARD", 650);

        // Can also use specific factory methods
        CreditCard premiumCard = CreditCardFactory.createPlatinumCard(800);
        System.out.println("Premium card created with limit: $" + premiumCard.getCreditLimit());
    }
}

class CreditCardFactory {
    private static final double GOLD_LIMIT_HIGH = 5000.0;
    private static final double GOLD_LIMIT_LOW = 2500.0;
    private static final double PLATINUM_LIMIT = 10000.0;
    private static final double STANDARD_LIMIT = 1000.0;

    private static final double GOLD_RATE_HIGH = 0.12;
    private static final double GOLD_RATE_LOW = 0.15;
    private static final double PLATINUM_RATE = 0.10;
    private static final double STANDARD_RATE = 0.18;

    private static final int GOLD_MIN_SCORE = 700;
    private static final int PLATINUM_MIN_SCORE = 750;

    public static CreditCard createGoldCard(int creditScore) {
        if (creditScore >= GOLD_MIN_SCORE) {
            return new CreditCard("GOLD", GOLD_LIMIT_HIGH, GOLD_RATE_HIGH);
        } else {
            return new CreditCard("GOLD", GOLD_LIMIT_LOW, GOLD_RATE_LOW);
        }
    }

    public static CreditCard createPlatinumCard(int creditScore) {
        if (creditScore < PLATINUM_MIN_SCORE) {
            throw new IllegalArgumentException(
                    "Credit score must be at least " + PLATINUM_MIN_SCORE + " for Platinum card");
        }
        return new CreditCard("PLATINUM", PLATINUM_LIMIT, PLATINUM_RATE);
    }

    public static CreditCard createStandardCard() {
        return new CreditCard("STANDARD", STANDARD_LIMIT, STANDARD_RATE);
    }

    public static CreditCard createCard(String cardType, int creditScore) {
        return switch (cardType.toUpperCase()) {
            case "GOLD" -> createGoldCard(creditScore);
            case "PLATINUM" -> createPlatinumCard(creditScore);
            case "STANDARD" -> createStandardCard();
            default -> throw new IllegalArgumentException("Unknown card type: " + cardType);
        };
    }
}

class ClientCode {
    public void processApplication(String cardType, int creditScore) {
        CreditCard card = CreditCardFactory.createCard(cardType, creditScore);

        System.out.println("Created " + card.getType() + " card with limit: $"
                + card.getCreditLimit() + " and rate: " + (card.getInterestRate() * 100) + "%");
    }
}
