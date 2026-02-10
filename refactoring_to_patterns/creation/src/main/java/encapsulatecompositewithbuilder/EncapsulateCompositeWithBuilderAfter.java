package encapsulatecompositewithbuilder;

// AFTER REFACTORING
// Solution: Builder encapsulates the complex construction logic

import java.util.*;

public class EncapsulateCompositeWithBuilderAfter {
    public static void main(String[] args) {
        // Example 1: Using generic HTMLBuilder
        System.out.println("=== Using HTMLBuilder ===");
        TagNode html1 = new HTMLBuilder("div")
                .attribute("class", "product-list")
                .beginTag("div")
                .attribute("class", "product")
                .beginTag("h2")
                .text("Laptop")
                .endTag()
                .beginTag("p")
                .attribute("class", "price")
                .text("$999.99")
                .endTag()
                .beginTag("p")
                .attribute("class", "description")
                .text("High-performance laptop")
                .endTag()
                .endTag()
                .beginTag("div")
                .attribute("class", "product")
                .beginTag("h2")
                .text("Mouse")
                .endTag()
                .beginTag("p")
                .attribute("class", "price")
                .text("$29.99")
                .endTag()
                .beginTag("p")
                .attribute("class", "description")
                .text("Wireless mouse")
                .endTag()
                .endTag()
                .build();

        System.out.println(html1.toHTML());

        // Example 2: Using domain-specific ProductListBuilder
        System.out.println("\n=== Using ProductListBuilder ===");
        TagNode html2 = new ProductListBuilder()
                .addProduct("Laptop", "$999.99", "High-performance laptop")
                .addProduct("Mouse", "$29.99", "Wireless mouse")
                .addProduct("Keyboard", "$79.99", "Mechanical keyboard")
                .build();

        System.out.println(html2.toHTML());
    }
}

// Builder class encapsulates construction logic
class HTMLBuilder {
    private final TagNode root;
    private final Stack<TagNode> nodeStack;

    public HTMLBuilder(String rootTag) {
        this.root = new TagNode(rootTag);
        this.nodeStack = new Stack<>();
        this.nodeStack.push(root);
    }

    public HTMLBuilder attribute(String name, String value) {
        nodeStack.peek().addAttribute(name, value);
        return this;
    }

    public HTMLBuilder beginTag(String tagName) {
        TagNode newTag = new TagNode(tagName);
        nodeStack.peek().addChild(newTag);
        nodeStack.push(newTag);
        return this;
    }

    public HTMLBuilder endTag() {
        if (nodeStack.size() > 1) {
            nodeStack.pop();
        }
        return this;
    }

    public HTMLBuilder text(String text) {
        nodeStack.peek().addChild(new TextNode(text));
        return this;
    }

    public TagNode build() {
        return root;
    }
}

// Even better: Domain-specific builder for products
class ProductListBuilder {
    private final HTMLBuilder htmlBuilder;

    public ProductListBuilder() {
        this.htmlBuilder = new HTMLBuilder("div");
        this.htmlBuilder.attribute("class", "product-list");
    }

    public ProductListBuilder addProduct(String name, String price, String description) {
        htmlBuilder.beginTag("div")
                .attribute("class", "product")
                .beginTag("h2")
                .text(name)
                .endTag()
                .beginTag("p")
                .attribute("class", "price")
                .text(price)
                .endTag()
                .beginTag("p")
                .attribute("class", "description")
                .text(description)
                .endTag()
                .endTag();
        return this;
    }

    public TagNode build() {
        return htmlBuilder.build();
    }
}

