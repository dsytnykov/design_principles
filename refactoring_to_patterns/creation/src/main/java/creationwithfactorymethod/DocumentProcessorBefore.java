package creationwithfactorymethod;

// BEFORE REFACTORING
// Problem: Document processing logic mixed with type-specific creation logic

public class DocumentProcessorBefore {
    public static void main(String[] args) {
        Document doc = new Document("My Report", "This is the report content.");

        DocumentExporterBefore htmlExporter = new DocumentExporterBefore("HTML");
        System.out.println("HTML Export:");
        System.out.println(htmlExporter.export(doc));

        System.out.println("\n" + "=".repeat(50) + "\n");

        DocumentExporterBefore xmlExporter = new DocumentExporterBefore("XML");
        System.out.println("XML Export:");
        System.out.println(xmlExporter.export(doc));

        System.out.println("\n" + "=".repeat(50) + "\n");

        DocumentExporterBefore pdfExporter = new DocumentExporterBefore("PDF");
        System.out.println("PDF Export:");
        System.out.println(pdfExporter.export(doc));
    }
}

class DocumentExporterBefore {
    private final String documentType;

    public DocumentExporterBefore(String documentType) {
        this.documentType = documentType;
    }

    // Template-like method with embedded creation logic
    public String export(Document doc) {
        StringBuilder result = new StringBuilder();

        result.append(createHeader(doc));

        result.append(createContent(doc));

        result.append(createFooter(doc));

        return result.toString();
    }

    // Complex conditional logic based on type
    private String createHeader(Document doc) {
        return switch (documentType) {
            case "HTML" -> "<html>\n<head><title>" + doc.title() + "</title></head>\n<body>\n";
            case "XML" -> "<?xml version=\"1.0\"?>\n<document>\n<title>" + doc.title() + "</title>\n";
            case "PDF" -> "%PDF-1.4\n%%Title: " + doc.title() + "\n%%BeginContent\n";
            default -> "=== " + doc.title() + " ===\n";
        };
    }

    private String createContent(Document doc) {
        return switch (documentType) {
            case "HTML" -> "<div class=\"content\">\n" + doc.content() + "\n</div>\n";
            case "XML" -> "<content>" + doc.content() + "</content>\n";
            case "PDF" -> "BT\n" + doc.content() + "\nET\n";
            default -> doc.content() + "\n";
        };
    }

    private String createFooter(Document doc) {
        return switch (documentType) {
            case "HTML" -> "</body>\n</html>";
            case "XML" -> "</document>";
            case "PDF" -> "%%EOF";
            default -> "--- End ---";
        };
    }
}

