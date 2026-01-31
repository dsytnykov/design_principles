package replacewithcreationmethods;

// AFTER REFACTORING
// Solution: Named creation methods that clearly express intent

public class ReplaceConstructorsWithCreationMethodsAfter {
    public static void main(String[] args) {
        // Intent is crystal clear from the method names
        LoanAfter standardLoan = LoanAfter.createStandardLoan(10000.0, 0.05, 60);

        // No confusing boolean - method name tells you it's promotional
        LoanAfter promoLoan = LoanAfter.createPromotionalLoan(5000.0, 24);

        // Parameter name makes it clear: 0.2 means 20% discount
        LoanAfter advisorLoan = LoanAfter.createAdvisorApprovedLoan(15000.0, 0.045, 48, 0.2);

        // No string flag needed - method name is self-documenting
        LoanAfter refinanceLoan = LoanAfter.createRefinanceLoan(20000.0, 0.04, 36);

        // Can even create from existing loan
        LoanAfter newRefinance = LoanAfter.createRefinanceFromExisting(standardLoan, 0.035, 48);

        System.out.println("Standard Loan - Type: " + standardLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", standardLoan.calculateMonthlyPayment()) +
                ", Fee: $" + String.format("%.2f", standardLoan.getOriginationFee()));

        System.out.println("Promo Loan - Type: " + promoLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", promoLoan.calculateMonthlyPayment()) +
                ", Fee: $" + String.format("%.2f", promoLoan.getOriginationFee()));

        System.out.println("Advisor Loan - Type: " + advisorLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", advisorLoan.calculateMonthlyPayment()) +
                ", Fee: $" + String.format("%.2f", advisorLoan.getOriginationFee()));

        System.out.println("Refinance Loan - Type: " + refinanceLoan.getLoanType() +
                ", Monthly: $" + String.format("%.2f", refinanceLoan.calculateMonthlyPayment()) +
                ", Fee: $" + String.format("%.2f", refinanceLoan.getOriginationFee()));

        System.out.println("New Refinance - Type: " + newRefinance.getLoanType() +
                ", Monthly: $" + String.format("%.2f", newRefinance.calculateMonthlyPayment()) +
                ", Fee: $" + String.format("%.2f", newRefinance.getOriginationFee()));
    }
}

class LoanAfter {
    private final double principal;
    private final double interestRate;
    private final int termInMonths;
    private final  String loanType;
    private final double originationFee;

    // Private constructor - forces use of creation methods
    private LoanAfter(double principal, double interestRate, int termInMonths,
                 String loanType, double originationFee) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanType = loanType;
        this.originationFee = originationFee;
    }

    // Clear, named creation methods
    public static LoanAfter createStandardLoan(double principal, double interestRate, int termInMonths) {
        double fee = principal * 0.01; // 1% origination fee
        return new LoanAfter(principal, interestRate, termInMonths, "STANDARD", fee);
    }

    public static LoanAfter createPromotionalLoan(double principal, int termInMonths) {
        // Promotional loans have 0% interest and no fees
        return new LoanAfter(principal, 0.0, termInMonths, "PROMOTIONAL", 0.0);
    }

    public static LoanAfter createAdvisorApprovedLoan(double principal, double interestRate,
                                                 int termInMonths, double feeDiscountPercent) {
        if (feeDiscountPercent < 0.0 || feeDiscountPercent > 1.0) {
            throw new IllegalArgumentException("Fee discount must be between 0 and 1");
        }
        double baseFee = principal * 0.01;
        double discountedFee = baseFee * (1.0 - feeDiscountPercent);
        return new LoanAfter(principal, interestRate, termInMonths, "ADVISOR_APPROVED", discountedFee);
    }

    public static LoanAfter createRefinanceLoan(double principal, double interestRate, int termInMonths) {
        double fee = principal * 0.005; // 0.5% fee for refinance (50% discount)
        return new LoanAfter(principal, interestRate, termInMonths, "REFINANCE", fee);
    }

    // Alternative: create from existing loan (useful for refinancing)
    public static LoanAfter createRefinanceFromExisting(LoanAfter existingLoan, double newInterestRate,
                                                   int newTermInMonths) {
        return createRefinanceLoan(existingLoan.principal, newInterestRate, newTermInMonths);
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

    public double getPrincipal() {
        return principal;
    }

    public double getOriginationFee() {
        return originationFee;
    }
}

