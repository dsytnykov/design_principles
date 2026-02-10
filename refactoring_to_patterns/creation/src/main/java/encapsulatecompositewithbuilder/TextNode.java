package encapsulatecompositewithbuilder;

class TextNode implements HTMLElement {
    private final String text;

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public String toHTML() {
        return text;
    }
}