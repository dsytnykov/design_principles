package encapsulateclasseswithfactory;

// BEFORE REFACTORING
// Problem: Client code must know about multiple concrete classes and complex creation logic

public class EncapsulateClassesWithFactoryBefore {
    public void processFile(String filename, String content) {
        // Complex creation logic scattered in client code
        if (filename.endsWith(".xml")) {
            boolean validate = content.contains("<?xml") && content.contains("xmlns");
            XMLParser parser = new XMLParser(validate);
            parser.parse(content);

        } else if (filename.endsWith(".json")) {
            boolean strict = !content.contains("//") && !content.contains("/*");
            JSONParser parser = new JSONParser(strict);
            parser.parse(content);

        } else if (filename.endsWith(".csv")) {
            char delim = content.contains("\t") ? '\t' : ',';
            boolean header = content.lines().count() > 1;
            CSVParser parser = new CSVParser(delim, header);
            parser.parse(content);

        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filename);
        }
    }

    public static void main(String[] args) {
        //DataProcessor is better name
        EncapsulateClassesWithFactoryBefore processor = new EncapsulateClassesWithFactoryBefore();

        processor.processFile("data.xml",
                "<?xml version=\"1.0\" xmlns=\"http://example.com\"?><root>data</root>");

        processor.processFile("data.json",
                "{\"name\": \"John\", \"age\": 30}");

        processor.processFile("data.csv",
                "Name,Age,City\nJohn,30,NYC\nJane,25,LA");
    }
}

// Different parser implementations
class XMLParser {
    private boolean validateSchema;

    public XMLParser(boolean validateSchema) {
        this.validateSchema = validateSchema;
    }

    public void parse(String content) {
        System.out.println("Parsing XML" + (validateSchema ? " with schema validation" : ""));
        System.out.println("Content: " + content.substring(0, Math.min(50, content.length())));
    }
}

class JSONParser {
    private boolean strictMode;

    public JSONParser(boolean strictMode) {
        this.strictMode = strictMode;
    }

    public void parse(String content) {
        System.out.println("Parsing JSON" + (strictMode ? " in strict mode" : ""));
        System.out.println("Content: " + content.substring(0, Math.min(50, content.length())));
    }
}

class CSVParser {
    private char delimiter;
    private boolean hasHeader;

    public CSVParser(char delimiter, boolean hasHeader) {
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
    }

    public void parse(String content) {
        System.out.println("Parsing CSV with delimiter '" + delimiter +
                "', header: " + hasHeader);
        System.out.println("Content: " + content.substring(0, Math.min(50, content.length())));
    }
}

