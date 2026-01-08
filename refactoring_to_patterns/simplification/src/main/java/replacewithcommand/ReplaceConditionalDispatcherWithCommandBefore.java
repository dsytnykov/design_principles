package replacewithcommand;

// BEFORE REFACTORING
// Problem: Conditional logic dispatches to different behaviors

import java.util.*;

public class ReplaceConditionalDispatcherWithCommandBefore {
    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();

        editor.executeAction("APPEND", "Hello ");
        editor.showDocument();

        editor.executeAction("APPEND", "World");
        editor.showDocument();

        editor.executeAction("UPPERCASE", null);
        editor.showDocument();

        editor.executeAction("UNDO", null);
        editor.showDocument();

        editor.executeAction("REPLACE", "World|Java");
        editor.showDocument();

        editor.executeAction("DELETE", "5");
        editor.showDocument();
    }

    private static class DocumentEditor {
        private final Document document;

        public DocumentEditor() {
            this.document = new Document();
        }

        public void executeAction(String action, String parameter) {
            if (action.equals("APPEND")) {
                document.saveToHistory();
                document.setContent(document.getContent() + parameter);
                System.out.println("Appended: " + parameter);

            } else if (action.equals("DELETE")) {
                document.saveToHistory();
                int deleteCount = Integer.parseInt(parameter);
                String content = document.getContent();
                if (content.length() >= deleteCount) {
                    document.setContent(content.substring(0, content.length() - deleteCount));
                    System.out.println("Deleted " + deleteCount + " characters");
                }

            } else if (action.equals("REPLACE")) {
                document.saveToHistory();
                String[] parts = parameter.split("\\|");
                String oldText = parts[0];
                String newText = parts[1];
                String content = document.getContent();
                document.setContent(content.replace(oldText, newText));
                System.out.println("Replaced '" + oldText + "' with '" + newText + "'");

            } else if (action.equals("UPPERCASE")) {
                document.saveToHistory();
                document.setContent(document.getContent().toUpperCase());
                System.out.println("Converted to uppercase");

            } else if (action.equals("UNDO")) {
                document.restoreFromHistory();
                System.out.println("Undo performed");

            } else {
                System.out.println("Unknown action: " + action);
            }
        }

        public void showDocument() {
            System.out.println("Document: \"" + document.getContent() + "\"");
        }
    }
}



