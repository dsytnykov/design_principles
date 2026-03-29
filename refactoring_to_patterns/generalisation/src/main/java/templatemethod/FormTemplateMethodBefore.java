package templatemethod;

// BEFORE REFACTORING
// Problem: Similar algorithms duplicated across multiple classes

class FormTemplateMethodBefore {
    public static void main(String[] args) {
        String reportData = "Sales for Q4: $1,250,000";

        PDFReport pdfReport = new PDFReport(reportData);
        pdfReport.generate();

        HTMLReport htmlReport = new HTMLReport(reportData);
        htmlReport.generate();

        ExcelReport excelReport = new ExcelReport(reportData);
        excelReport.generate();
    }
}


class PDFReport {
    private final String data;

    public PDFReport(String data) {
        this.data = data;
    }

    public void generate() {
        // Step 1: Open connection
        System.out.println("Opening PDF generator...");

        // Step 2: Prepare header
        System.out.println("=== PDF REPORT ===");
        System.out.println("Format: Portable Document Format");

        // Step 3: Write content
        System.out.println("Content: " + data);

        // Step 4: Prepare footer
        System.out.println("Page 1 of 1");
        System.out.println("Generated: " + java.time.LocalDateTime.now());

        // Step 5: Close connection
        System.out.println("Closing PDF generator...");
        System.out.println("PDF saved to disk.\n");
    }
}

class HTMLReport {
    private final String data;

    public HTMLReport(String data) {
        this.data = data;
    }

    public void generate() {
        // Step 1: Open connection (similar to PDF)
        System.out.println("Opening HTML generator...");

        // Step 2: Prepare header (different format)
        System.out.println("<html><head><title>HTML Report</title></head><body>");
        System.out.println("<h1>HTML REPORT</h1>");

        // Step 3: Write content (different format)
        System.out.println("<p>" + data + "</p>");

        // Step 4: Prepare footer (different format)
        System.out.println("<footer>Generated: " + java.time.LocalDateTime.now() + "</footer>");

        // Step 5: Close connection (similar to PDF)
        System.out.println("</body></html>");
        System.out.println("Closing HTML generator...");
        System.out.println("HTML saved to disk.\n");
    }
}

class ExcelReport {
    private final String data;

    public ExcelReport(String data) {
        this.data = data;
    }

    public void generate() {
        // Step 1: Open connection (similar to others)
        System.out.println("Opening Excel generator...");

        // Step 2: Prepare header (different format)
        System.out.println("--- EXCEL REPORT ---");
        System.out.println("[A1] Report Title");

        // Step 3: Write content (different format)
        System.out.println("[A2] " + data);

        // Step 4: Prepare footer (different format)
        System.out.println("[A10] Generated: " + java.time.LocalDateTime.now());

        // Step 5: Close connection (similar to others)
        System.out.println("Closing Excel generator...");
        System.out.println("Excel saved to disk.\n");
    }
}
