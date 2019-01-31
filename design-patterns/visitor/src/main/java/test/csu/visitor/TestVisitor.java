package test.csu.visitor;

import test.csu.visitor.elem.Circle;
import test.csu.visitor.elem.Element;
import test.csu.visitor.elem.Rectangle;
import test.csu.visitor.elem.Square;
import test.csu.visitor.lambda.LambdaVisitor;
import test.csu.visitor.visitor.AreaVisitor;
import test.csu.visitor.visitor.PerimeterVisitor;
import test.csu.visitor.visitor.Visitor;

import java.util.Arrays;
import java.util.List;

public class TestVisitor {

    public static void main(String[] args) {
        lambdaVisitor();

        System.out.println("-------------");
        
        visitor();
    }

    public static void visitor() {
        List<Element> figures = Arrays.asList(new Circle(4), new Square(5), new Rectangle(6, 7));

        double totalArea = 0.0;
        Visitor<Double> areaVisitor = new AreaVisitor();
        for (Element figure : figures) {
            totalArea += figure.accept(areaVisitor);
        }
        System.out.println("Total area = " + totalArea);

        double totalPerimeter = 0.0;
        Visitor<Double> perimeterVisitor = new PerimeterVisitor();
        for (Element figure : figures) {
            totalPerimeter += figure.accept(perimeterVisitor);
        }
        System.out.println("Total perimeter = " + totalPerimeter);
    }

    public static void lambdaVisitor() {
        List<Object> figures = Arrays.asList(new Circle(4), new Square(5), new Rectangle(6, 7));

        LambdaVisitor<Double> areaVisitor = LambdaVisitor.<Double>instance()
                .visitable(Square.class).then(x -> x.side * x.side)
                .visitable(Circle.class).then(c -> Math.PI * c.radius * c.radius)
                .visitable(Rectangle.class).then(r -> r.height * r.width);

        double totalArea = figures.stream().map(areaVisitor).reduce(0.0, (v1, v2) -> v1 + v2);
        System.out.println("Total area = " + totalArea);

        LambdaVisitor<Double> perimeterVisitor = LambdaVisitor.<Double>instance()
                .visitable(Square.class).then(s -> 4 * s.side)
                .visitable(Circle.class).then(c -> 2 * Math.PI * c.radius)
                .visitable(Rectangle.class).then(r -> 2 * r.height + 2 * r.width);

        double totalPerimeter = figures.stream().map(perimeterVisitor).reduce(0.0, (v1, v2) -> v1 + v2);
        System.out.println("Total perimeter = " + totalPerimeter);
    }
}
