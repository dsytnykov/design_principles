package encapsulatecompositewithbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TagNode implements HTMLElement {
    private final String tagName;
    private final Map<String, String> attributes;
    private final List<HTMLElement> children;

    public TagNode(String tagName) {
        this.tagName = tagName;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public void addChild(HTMLElement child) {
        children.add(child);
    }

    @Override
    public String toHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<").append(tagName);

        for (Map.Entry<String, String> attr : attributes.entrySet()) {
            html.append(" ").append(attr.getKey())
                    .append("=\"").append(attr.getValue()).append("\"");
        }

        if (children.isEmpty()) {
            html.append(" />");
        } else {
            html.append(">");
            for (HTMLElement child : children) {
                html.append(child.toHTML());
            }
            html.append("</").append(tagName).append(">");
        }

        return html.toString();
    }
}
