package test.csu.visitor.visitor;

import test.csu.visitor.elem.Circle;
import test.csu.visitor.elem.Rectangle;
import test.csu.visitor.elem.Square;

public class AreaVisitor implements Visitor<Double> {
    @Override
    public Double visit(Square element) {
        return element.side * element.side;
    }

    @Override
    public Double visit(Circle element) {
        return Math.PI * element.radius * element.radius;
    }

    @Override
    public Double visit(Rectangle element) {
        return element.height * element.width;
    }
}
