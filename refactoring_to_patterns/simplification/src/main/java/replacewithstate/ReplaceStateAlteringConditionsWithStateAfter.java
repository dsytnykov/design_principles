package replacewithstate;

// AFTER REFACTORING
// Solution: State pattern encapsulates state-specific behavior

public class ReplaceStateAlteringConditionsWithStateAfter {
    public static void main(String[] args) {
        DocumentEditorAfter doc = new DocumentEditorAfter("John Doe");

        System.out.println("Current state: " + doc.getStateName());
        doc.publish(); // Should fail

        doc.edit("This is my article content.");
        doc.submitForModeration();

        System.out.println("\nCurrent state: " + doc.getStateName());
        doc.edit("Trying to edit"); // Should move back to draft

        System.out.println("\nCurrent state: " + doc.getStateName());
        doc.submitForModeration();
        doc.publish();

        System.out.println("\nCurrent state: " + doc.getStateName());
        doc.archive();

        System.out.println("\nFinal state: " + doc.getStateName());
    }
}

interface DocumentState {
    void publish(DocumentEditorAfter doc);
    void edit(DocumentEditorAfter doc, String newContent);
    void submitForModeration(DocumentEditorAfter doc);
    void archive(DocumentEditorAfter doc);
    String getStateName();
}

// Concrete state: Draft
class DraftState implements DocumentState {
    @Override
    public void publish(DocumentEditorAfter doc) {
        System.out.println("Cannot publish directly from DRAFT. Submit for moderation first.");
    }

    @Override
    public void edit(DocumentEditorAfter doc, String newContent) {
        doc.setContent(newContent);
        System.out.println("Content updated in draft.");
    }

    @Override
    public void submitForModeration(DocumentEditorAfter doc) {
        if (doc.getContent().isEmpty()) {
            System.out.println("Cannot submit empty document.");
        } else {
            doc.setState(new ModerationState());
            System.out.println("Document submitted for moderation.");
        }
    }

    @Override
    public void archive(DocumentEditorAfter doc) {
        System.out.println("Cannot archive draft. Delete instead.");
    }

    @Override
    public String getStateName() {
        return "DRAFT";
    }
}

// Concrete state: Moderation
class ModerationState implements DocumentState {
    @Override
    public void publish(DocumentEditorAfter doc) {
        doc.setState(new PublishedState());
        System.out.println("Document published successfully!");
    }

    @Override
    public void edit(DocumentEditorAfter doc, String newContent) {
        doc.setState(new DraftState());
        doc.setContent(newContent);
        System.out.println("Document moved back to DRAFT. Content updated.");
    }

    @Override
    public void submitForModeration(DocumentEditorAfter doc) {
        System.out.println("Document already in moderation.");
    }

    @Override
    public void archive(DocumentEditorAfter doc) {
        System.out.println("Cannot archive document in moderation. Reject first.");
    }

    @Override
    public String getStateName() {
        return "MODERATION";
    }
}

// Concrete state: Published
class PublishedState implements DocumentState {
    @Override
    public void publish(DocumentEditorAfter doc) {
        System.out.println("Document is already published.");
    }

    @Override
    public void edit(DocumentEditorAfter doc, String newContent) {
        System.out.println("Cannot edit published document. Create new version.");
    }

    @Override
    public void submitForModeration(DocumentEditorAfter doc) {
        System.out.println("Document is published. No moderation needed.");
    }

    @Override
    public void archive(DocumentEditorAfter doc) {
        doc.setState(new ArchivedState());
        System.out.println("Document archived.");
    }

    @Override
    public String getStateName() {
        return "PUBLISHED";
    }
}

// Concrete state: Archived
class ArchivedState implements DocumentState {
    @Override
    public void publish(DocumentEditorAfter doc) {
        System.out.println("Cannot publish archived document. Create new version.");
    }

    @Override
    public void edit(DocumentEditorAfter doc, String newContent) {
        System.out.println("Cannot edit archived document.");
    }

    @Override
    public void submitForModeration(DocumentEditorAfter doc) {
        System.out.println("Cannot submit archived document.");
    }

    @Override
    public void archive(DocumentEditorAfter doc) {
        System.out.println("Document is already archived.");
    }

    @Override
    public String getStateName() {
        return "ARCHIVED";
    }
}

class DocumentEditorAfter {
    private DocumentState state;
    private String content;
    private String author;

    public DocumentEditorAfter(String author) {
        this.state = new DraftState();
        this.author = author;
        this.content = "";
    }

    // Delegate to current state - no conditionals!
    public void publish() {
        state.publish(this);
    }

    public void edit(String newContent) {
        state.edit(this, newContent);
    }

    public void submitForModeration() {
        state.submitForModeration(this);
    }

    public void archive() {
        state.archive(this);
    }

    public String getStateName() {
        return state.getStateName();
    }

    void setState(DocumentState newState) {
        this.state = newState;
    }

    void setContent(String content) {
        this.content = content;
    }

    String getContent() {
        return content;
    }
}
