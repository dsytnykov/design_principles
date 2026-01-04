package encapsulatewithfactory;

class CreditCard {
    private String type;
    private double creditLimit;
    private double interestRate;

    public CreditCard(String type, double creditLimit, double interestRate) {
        this.type = type;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public String getType() { return type; }
    public double getCreditLimit() { return creditLimit; }
    public double getInterestRate() { return interestRate; }
}
