package encapsulatecompositewithbuilder;

// BEFORE REFACTORING
// Problem: Complex composite structures are tedious and error-prone to build

import java.util.*;

public class EncapsulateCompositeWithBuilderBefore {
    public static void main(String[] args) {
        // Building a simple product listing is tedious
        TagNode root = new TagNode("div");
        root.addAttribute("class", "product-list");

        TagNode product1 = new TagNode("div");
        product1.addAttribute("class", "product");

        TagNode title1 = new TagNode("h2");
        title1.addChild(new TextNode("Laptop"));
        product1.addChild(title1);

        TagNode price1 = new TagNode("p");
        price1.addAttribute("class", "price");
        price1.addChild(new TextNode("$999.99"));
        product1.addChild(price1);

        TagNode desc1 = new TagNode("p");
        desc1.addAttribute("class", "description");
        desc1.addChild(new TextNode("High-performance laptop"));
        product1.addChild(desc1);

        root.addChild(product1);

        TagNode product2 = new TagNode("div");
        product2.addAttribute("class", "product");

        TagNode title2 = new TagNode("h2");
        title2.addChild(new TextNode("Mouse"));
        product2.addChild(title2);

        TagNode price2 = new TagNode("p");
        price2.addAttribute("class", "price");
        price2.addChild(new TextNode("$29.99"));
        product2.addChild(price2);

        TagNode desc2 = new TagNode("p");
        desc2.addAttribute("class", "description");
        desc2.addChild(new TextNode("Wireless mouse"));
        product2.addChild(desc2);

        root.addChild(product2);

        System.out.println(root.toHTML());
    }
}
