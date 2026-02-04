package creationwithfactorymethod;


// AFTER REFACTORING
// Solution: Factory Method pattern combined with Template Method pattern

public class DocumentProcessorAfter {
    public static void main(String[] args) {
        Document doc = new Document("My Report", "This is the report content.");

        // Can create exporters directly
        DocumentExporterAfter htmlExporter = new HtmlExporter();
        System.out.println("HTML Export:");
        System.out.println(htmlExporter.export(doc));

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Or use factory for dynamic creation
        DocumentExporterAfter xmlExporter = ExporterFactory.createExporter("XML");
        System.out.println("XML Export:");
        System.out.println(xmlExporter.export(doc));

        System.out.println("\n" + "=".repeat(50) + "\n");

        DocumentExporterAfter pdfExporter = ExporterFactory.createExporter("PDF");
        System.out.println("PDF Export:");
        System.out.println(pdfExporter.export(doc));

        // Easy to add new formats by creating new subclasses
        // without modifying existing code (Open/Closed Principle)
    }
}

// Abstract class defines Template Method and Factory Methods
abstract class DocumentExporterAfter {

    // TEMPLATE METHOD - defines the algorithm skeleton
    public final String export(Document doc) {
        StringBuilder result = new StringBuilder();

        result.append(createHeader(doc));

        result.append(createContent(doc));

        result.append(createFooter(doc));

        return result.toString();
    }

    protected abstract String createHeader(Document doc);
    protected abstract String createContent(Document doc);
    protected abstract String createFooter(Document doc);
}

class HtmlExporter extends DocumentExporterAfter {

    @Override
    protected String createHeader(Document doc) {
        return "<html>\n<head><title>" + doc.title() + "</title></head>\n<body>\n";
    }

    @Override
    protected String createContent(Document doc) {
        return "<div class=\"content\">\n" + doc.content() + "\n</div>\n";
    }

    @Override
    protected String createFooter(Document doc) {
        return "</body>\n</html>";
    }
}

class XmlExporter extends DocumentExporterAfter {

    @Override
    protected String createHeader(Document doc) {
        return "<?xml version=\"1.0\"?>\n<document>\n<title>" + doc.title() + "</title>\n";
    }

    @Override
    protected String createContent(Document doc) {
        return "<content>" + doc.content() + "</content>\n";
    }

    @Override
    protected String createFooter(Document doc) {
        return "</document>";
    }
}

class PdfExporter extends DocumentExporterAfter {

    @Override
    protected String createHeader(Document doc) {
        return "%PDF-1.4\n%%Title: " + doc.title() + "\n%%BeginContent\n";
    }

    @Override
    protected String createContent(Document doc) {
        return "BT\n" + doc.content() + "\nET\n";
    }

    @Override
    protected String createFooter(Document doc) {
        return "%%EOF";
    }
}

class TextExporter extends DocumentExporterAfter {

    @Override
    protected String createHeader(Document doc) {
        return "=== " + doc.title() + " ===\n";
    }

    @Override
    protected String createContent(Document doc) {
        return doc.content() + "\n";
    }

    @Override
    protected String createFooter(Document doc) {
        return "--- End ---";
    }
}

class ExporterFactory {
    public static DocumentExporterAfter createExporter(String type) {
        return switch (type.toUpperCase()) {
            case "HTML" -> new HtmlExporter();
            case "XML" -> new XmlExporter();
            case "PDF" -> new PdfExporter();
            case "TEXT" -> new TextExporter();
            default -> throw new IllegalArgumentException("Unknown exporter type: " + type);
        };
    }
}