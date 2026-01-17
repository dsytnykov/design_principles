package replacewithstate;


// BEFORE REFACTORING
// Problem: Complex state transitions with lots of conditionals

public class ReplaceStateAlteringConditionsWithStateBefore {
    public static void main(String[] args) {
        DocumentEditor doc = new DocumentEditor("John Doe");

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

class DocumentEditor {
    private static final int DRAFT = 0;
    private static final int MODERATION = 1;
    private static final int PUBLISHED = 2;
    private static final int ARCHIVED = 3;

    private int state;
    private String content;
    private String author;

    public DocumentEditor(String author) {
        this.state = DRAFT;
        this.author = author;
        this.content = "";
    }

    public void publish() {
        if (state == DRAFT) {
            System.out.println("Cannot publish directly from DRAFT. Submit for moderation first.");
        } else if (state == MODERATION) {
            state = PUBLISHED;
            System.out.println("Document published successfully!");
        } else if (state == PUBLISHED) {
            System.out.println("Document is already published.");
        } else if (state == ARCHIVED) {
            System.out.println("Cannot publish archived document. Create new version.");
        }
    }

    public void edit(String newContent) {
        if (state == DRAFT) {
            content = newContent;
            System.out.println("Content updated in draft.");
        } else if (state == MODERATION) {
            state = DRAFT;
            content = newContent;
            System.out.println("Document moved back to DRAFT. Content updated.");
        } else if (state == PUBLISHED) {
            System.out.println("Cannot edit published document. Create new version.");
        } else if (state == ARCHIVED) {
            System.out.println("Cannot edit archived document.");
        }
    }


    public void submitForModeration() {
        if (state == DRAFT) {
            if (content.isEmpty()) {
                System.out.println("Cannot submit empty document.");
            } else {
                state = MODERATION;
                System.out.println("Document submitted for moderation.");
            }
        } else if (state == MODERATION) {
            System.out.println("Document already in moderation.");
        } else if (state == PUBLISHED) {
            System.out.println("Document is published. No moderation needed.");
        } else if (state == ARCHIVED) {
            System.out.println("Cannot submit archived document.");
        }
    }

    public void archive() {
        if (state == DRAFT) {
            System.out.println("Cannot archive draft. Delete instead.");
        } else if (state == MODERATION) {
            System.out.println("Cannot archive document in moderation. Reject first.");
        } else if (state == PUBLISHED) {
            state = ARCHIVED;
            System.out.println("Document archived.");
        } else if (state == ARCHIVED) {
            System.out.println("Document is already archived.");
        }
    }

    public String getStateName() {
        return switch (state) {
            case DRAFT -> "DRAFT";
            case MODERATION -> "MODERATION";
            case PUBLISHED -> "PUBLISHED";
            case ARCHIVED -> "ARCHIVED";
            default -> "UNKNOWN";
        };
    }
}
