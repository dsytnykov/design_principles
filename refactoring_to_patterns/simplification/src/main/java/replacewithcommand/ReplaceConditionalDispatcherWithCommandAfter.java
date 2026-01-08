package replacewithcommand;

// AFTER REFACTORING
// Solution: Use Command pattern to encapsulate each action

import java.util.*;

public class ReplaceConditionalDispatcherWithCommandAfter {
    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();
        Document doc = editor.getDocument();

        editor.executeCommand(new AppendCommand(doc, "Hello "));
        editor.showDocument();

        editor.executeCommand(new AppendCommand(doc, "World"));
        editor.showDocument();

        editor.executeCommand(new UppercaseCommand(doc));
        editor.showDocument();

        editor.executeCommand(new UndoCommand(doc));
        editor.showDocument();

        editor.executeCommand(new ReplaceCommand(doc, "World", "Java"));
        editor.showDocument();

        editor.executeCommand(new DeleteCommand(doc, 5));
        editor.showDocument();

        List<EditorCommand> commandHistory = new ArrayList<>();
        EditorCommand cmd = new AppendCommand(doc, "!");
        commandHistory.add(cmd);
        editor.executeCommand(cmd);
        editor.showDocument();

        System.out.println("\nCommand history:");
        for (EditorCommand command : commandHistory) {
            System.out.println("- " + command.getDescription());
        }
    }
}

// Command interface
interface EditorCommand {
    void execute();
    String getDescription();
}

// Concrete command for appending text
class AppendCommand implements EditorCommand {
    private final Document document;
    private final String text;

    public AppendCommand(Document document, String text) {
        this.document = document;
        this.text = text;
    }

    @Override
    public void execute() {
        document.saveToHistory();
        document.setContent(document.getContent() + text);
        System.out.println("Appended: " + text);
    }

    @Override
    public String getDescription() {
        return "Append '" + text + "'";
    }
}

// Concrete command for deleting characters
class DeleteCommand implements EditorCommand {
    private final Document document;
    private final int count;

    public DeleteCommand(Document document, int count) {
        this.document = document;
        this.count = count;
    }

    @Override
    public void execute() {
        document.saveToHistory();
        String content = document.getContent();
        if (content.length() >= count) {
            document.setContent(content.substring(0, content.length() - count));
            System.out.println("Deleted " + count + " characters");
        }
    }

    @Override
    public String getDescription() {
        return "Delete " + count + " characters";
    }
}

// Concrete command for replacing text
class ReplaceCommand implements EditorCommand {
    private final Document document;
    private final String oldText;
    private final String newText;

    public ReplaceCommand(Document document, String oldText, String newText) {
        this.document = document;
        this.oldText = oldText;
        this.newText = newText;
    }

    @Override
    public void execute() {
        document.saveToHistory();
        String content = document.getContent();
        document.setContent(content.replace(oldText, newText));
        System.out.println("Replaced '" + oldText + "' with '" + newText + "'");
    }

    @Override
    public String getDescription() {
        return "Replace '" + oldText + "' with '" + newText + "'";
    }
}

// Concrete command for uppercase conversion
class UppercaseCommand implements EditorCommand {
    private final Document document;

    public UppercaseCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.saveToHistory();
        document.setContent(document.getContent().toUpperCase());
        System.out.println("Converted to uppercase");
    }

    @Override
    public String getDescription() {
        return "Convert to uppercase";
    }
}

// Concrete command for undo
class UndoCommand implements EditorCommand {
    private final Document document;

    public UndoCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() {
        document.restoreFromHistory();
        System.out.println("Undo performed");
    }

    @Override
    public String getDescription() {
        return "Undo";
    }
}

// Editor now just executes commands
class DocumentEditor {
    private final Document document;

    public DocumentEditor() {
        this.document = new Document();
    }

    public void executeCommand(EditorCommand command) {
        command.execute();
    }

    public Document getDocument() {
        return document;
    }

    public void showDocument() {
        System.out.println("Document: \"" + document.getContent() + "\"");
    }
}

