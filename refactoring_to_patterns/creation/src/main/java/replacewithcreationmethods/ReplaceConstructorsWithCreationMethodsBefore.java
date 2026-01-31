package replacewithcreationmethods;

// BEFORE REFACTORING
// Problem: Multiple constructors with unclear intent and limitations

public class ReplaceConstructorsWithCreationMethodsBefore {
    // Loan application
    public static void main(String[] args) {
        // Which constructor should I use? The parameters don't make intent clear
        Loan standardLoan = new Loan(10000.0, 0.05, 60);

        // Boolean parameter is confusing - what does 'true' mean?
        Loan promoLoan = new Loan(5000.0, 24, true);

        // What does 0.2 mean here?
        Loan advisorLoan = new Loan(15000.0, 0.045, 48, 0.2);

        // String parameter as flag is a code smell
        Loan refinanceLoan = new Loan("REFINANCE", 20000.0, 0.04, 36);

        System.out.println("Standard Loan - Type: " + standardLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", standardLoan.calculateMonthlyPayment()));
        System.out.println("Promo Loan - Type: " + promoLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", promoLoan.calculateMonthlyPayment()));
        System.out.println("Advisor Loan - Type: " + advisorLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", advisorLoan.calculateMonthlyPayment()));
        System.out.println("Refinance Loan - Type: " + refinanceLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", refinanceLoan.calculateMonthlyPayment()));
    }
}

class Loan {
    private final double principal;
    private final double interestRate;
    private final int termInMonths;
    private final String loanType;
    private final double originationFee;

    // Constructor for standard loan
    public Loan(double principal, double interestRate, int termInMonths) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanType = "STANDARD";
        this.originationFee = principal * 0.01; // 1% fee
    }

    // Constructor for promotional loan (0% interest)
    public Loan(double principal, int termInMonths, boolean isPromotional) {
        if (!isPromotional) {
            throw new IllegalArgumentException("Use other constructor for non-promotional");
        }
        this.principal = principal;
        this.interestRate = 0.0;
        this.termInMonths = termInMonths;
        this.loanType = "PROMOTIONAL";
        this.originationFee = 0.0;
    }

    // Constructor for advisor-approved loan
    public Loan(double principal, double interestRate, int termInMonths, double feeDiscount) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanType = "ADVISOR_APPROVED";
        this.originationFee = (principal * 0.01) * (1.0 - feeDiscount);
    }

    // Constructor for refinance loan
    public Loan(String refinanceFlag, double principal, double interestRate, int termInMonths) {
        if (!refinanceFlag.equals("REFINANCE")) {
            throw new IllegalArgumentException("Invalid refinance flag");
        }
        this.principal = principal;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanType = "REFINANCE";
        this.originationFee = principal * 0.005; // 0.5% fee for refinance
    }

    public double calculateMonthlyPayment() {
        if (interestRate == 0.0) {
            return principal / termInMonths;
        }
        double monthlyRate = interestRate / 12.0;
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, termInMonths)) /
                (Math.pow(1 + monthlyRate, termInMonths) - 1);
    }

    public double getTotalCost() {
        return (calculateMonthlyPayment() * termInMonths) + originationFee;
    }

    public String getLoanType() {
        return loanType;
    }
}