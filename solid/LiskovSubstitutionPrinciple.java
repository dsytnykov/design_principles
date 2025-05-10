
public class LiskovSubstitutionPrinciple {
}

//WRONG
/*
public class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

public class Square extends Rectangle {
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(5);
        rectangle.setHeight(10);
        System.out.println("Rectangle Area: " + rectangle.getArea());

        Square square = new Square();
        square.setWidth(5);
        System.out.println("Square Area: " + square.getArea());
    }
}
 */

//CORRECT
/*
public interface Shape {
    int getArea();
}

// Rectangle.java
public class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

// Square.java
public class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public int getArea() {
        return side * side;
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 10);
        Shape square = new Square(5);

        System.out.println("Rectangle Area: " + rectangle.getArea());
        System.out.println("Square Area: " + square.getArea());
    }
}
 */

