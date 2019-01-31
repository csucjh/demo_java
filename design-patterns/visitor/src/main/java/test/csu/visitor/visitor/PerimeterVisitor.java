package test.csu.visitor.visitor;

import test.csu.visitor.elem.Circle;
import test.csu.visitor.elem.Rectangle;
import test.csu.visitor.elem.Square;

public class PerimeterVisitor implements Visitor<Double> {

    @Override
    public Double visit(Square element) {
        return 4 * element.side;
    }

    @Override
    public Double visit(Circle element) {
        return 2 * Math.PI * element.radius;
    }

    @Override
    public Double visit(Rectangle element) {
        return (2 * element.height + 2 * element.width);
    }
}