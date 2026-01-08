package replacewithcommand;

import java.util.Stack;

class Document {
    private String content;
    private final Stack<String> history;

    public Document() {
        this.content = "";
        this.history = new Stack<>();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void saveToHistory() {
        history.push(content);
    }

    public void restoreFromHistory() {
        if (!history.isEmpty()) {
            content = history.pop();
        }
    }
}
