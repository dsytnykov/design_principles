package encapsulateclasseswithfactory;

// AFTER REFACTORING
// Solution: Factory encapsulates parser creation logic

public class EncapsulateClassesWithFactoryAfter {
    public void processFile(String filename, String content) {
        // Simple delegation to factory
        Parser parser = ParserFactory.createParser(filename, content);
        parser.parse(content);
    }

    public static void main(String[] args) {
        //DataProcessor
        EncapsulateClassesWithFactoryAfter processor = new EncapsulateClassesWithFactoryAfter();

        // Client code is clean and focused on business logic
        processor.processFile("data.xml",
                "<?xml version=\"1.0\" xmlns=\"http://example.com\"?><root>data</root>");

        processor.processFile("data.json",
                "{\"name\": \"John\", \"age\": 30}");

        processor.processFile("data.csv",
                "Name,Age,City\nJohn,30,NYC\nJane,25,LA");

        // Can also use specific factory methods when needed
        System.out.println("\n--- Using specific factory methods ---");
        Parser strictParser = ParserFactory.createStrictJSONParser();
        strictParser.parse("{\"valid\": true}");

        Parser customCSV = ParserFactory.createCustomCSVParser(';', false);
        customCSV.parse("John;30;NYC");
    }
}

// Common interface for all parsers
interface Parser {
    void parse(String content);
}

// Parser implementations (now package-private, only factory can create them)
class XMLParserAfter implements Parser {
    private boolean validateSchema;

    XMLParserAfter(boolean validateSchema) {
        this.validateSchema = validateSchema;
    }

    @Override
    public void parse(String content) {
        System.out.println("Parsing XML" + (validateSchema ? " with schema validation" : ""));
        System.out.println("Content: " + content.substring(0, Math.min(50, content.length())));
    }
}

class JSONParserAfter implements Parser {
    private boolean strictMode;

    JSONParserAfter(boolean strictMode) {
        this.strictMode = strictMode;
    }

    @Override
    public void parse(String content) {
        System.out.println("Parsing JSON" + (strictMode ? " in strict mode" : ""));
        System.out.println("Content: " + content.substring(0, Math.min(50, content.length())));
    }
}

class CSVParserAfter implements Parser {
    private char delimiter;
    private boolean hasHeader;

    CSVParserAfter(char delimiter, boolean hasHeader) {
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
    }

    @Override
    public void parse(String content) {
        System.out.println("Parsing CSV with delimiter '" + delimiter +
                "', header: " + hasHeader);
        System.out.println("Content: " + content.substring(0, Math.min(50, content.length())));
    }
}

// Factory encapsulates all parser creation logic
class ParserFactory {

    public static Parser createParser(String filename, String content) {
        if (filename.endsWith(".xml")) {
            return createXMLParser(content);
        } else if (filename.endsWith(".json")) {
            return createJSONParser(content);
        } else if (filename.endsWith(".csv")) {
            return createCSVParser(content);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filename);
        }
    }

    private static Parser createXMLParser(String content) {
        boolean validate = content.contains("<?xml") && content.contains("xmlns");
        return new XMLParserAfter(validate);
    }

    private static Parser createJSONParser(String content) {
        boolean strict = !content.contains("//") && !content.contains("/*");
        return new JSONParserAfter(strict);
    }

    private static Parser createCSVParser(String content) {
        char delimiter = content.contains("\t") ? '\t' : ',';
        boolean hasHeader = content.lines().count() > 1;
        return new CSVParserAfter(delimiter, hasHeader);
    }

    // Alternative: Explicit creation methods for specific cases
    public static Parser createStrictJSONParser() {
        return new JSONParserAfter(true);
    }

    public static Parser createValidatingXMLParser() {
        return new XMLParserAfter(true);
    }

    public static Parser createCustomCSVParser(char delimiter, boolean hasHeader) {
        return new CSVParserAfter(delimiter, hasHeader);
    }
}

