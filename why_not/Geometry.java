public class Square {
    public Point top_left;
    public double side;
}


public class Rectangle {
    public Point top_left;
    public double width;
    public double height;
}


public class Circle {
    public Point center;
    public double radius;
}

public class Geometry {
    public final double PI = 3.14159;
    
    public double area(Object shape) throws NoSuchShapeExcepetion {
        if (shape instanceof Square) {
            Square s = (Square) shape;
            return s.side * s.side;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.width * r.height;
        } else if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return PI * c.radius * c.radius;
        } else {
            throw new NoSuchShapeExcepetion();
        }
    }
}