package templatemethod;


// AFTER REFACTORING
// Solution: Template Method pattern - algorithm structure in superclass,
// specific steps overridden in subclasses

class FormTemplateMethodAfter {
    public static void main(String[] args) {
        String reportData = "Sales for Q4: $1,250,000";

        Report pdfReport = new PDFReportAfter(reportData);
        pdfReport.generate();

        Report htmlReport = new HTMLReportAfter(reportData);
        htmlReport.generate();

        Report excelReport = new ExcelReportAfter(reportData);
        excelReport.generate();

        processReport(new PDFReportAfter("Another report"));
    }

    private static void processReport(Report report) {
        System.out.println("Processing report...");
        report.generate();
    }
}

abstract class Report {
    protected String data;

    public Report(String data) {
        this.data = data;
    }

    // Template method - defines the algorithm structure
    // This is final to prevent subclasses from changing the algorithm flow
    public final void generate() {
        openConnection();
        writeHeader();
        writeContent();
        writeFooter();
        closeConnection();
        save();
    }

    protected void openConnection() {
        System.out.println("Opening " + getFormatName() + " generator...");
    }

    protected void closeConnection() {
        System.out.println("Closing " + getFormatName() + " generator...");
    }

    protected void save() {
        System.out.println(getFormatName() + " saved to disk.\n");
    }

    protected abstract void writeHeader();
    protected abstract void writeContent();
    protected abstract void writeFooter();
    protected abstract String getFormatName();
}

class PDFReportAfter extends Report {
    public PDFReportAfter(String data) {
        super(data);
    }

    @Override
    protected void writeHeader() {
        System.out.println("=== PDF REPORT ===");
        System.out.println("Format: Portable Document Format");
    }

    @Override
    protected void writeContent() {
        System.out.println("Content: " + data);
    }

    @Override
    protected void writeFooter() {
        System.out.println("Page 1 of 1");
        System.out.println("Generated: " + java.time.LocalDateTime.now());
    }

    @Override
    protected String getFormatName() {
        return "PDF";
    }
}

class HTMLReportAfter extends Report {
    public HTMLReportAfter(String data) {
        super(data);
    }

    @Override
    protected void writeHeader() {
        System.out.println("<html><head><title>HTML Report</title></head><body>");
        System.out.println("<h1>HTML REPORT</h1>");
    }

    @Override
    protected void writeContent() {
        System.out.println("<p>" + data + "</p>");
    }

    @Override
    protected void writeFooter() {
        System.out.println("<footer>Generated: " + java.time.LocalDateTime.now() + "</footer>");
        System.out.println("</body></html>");
    }

    @Override
    protected String getFormatName() {
        return "HTML";
    }
}

class ExcelReportAfter extends Report {
    public ExcelReportAfter(String data) {
        super(data);
    }

    @Override
    protected void writeHeader() {
        System.out.println("--- EXCEL REPORT ---");
        System.out.println("[A1] Report Title");
    }

    @Override
    protected void writeContent() {
        System.out.println("[A2] " + data);
    }

    @Override
    protected void writeFooter() {
        System.out.println("[A10] Generated: " + java.time.LocalDateTime.now());
    }

    @Override
    protected String getFormatName() {
        return "Excel";
    }
}
