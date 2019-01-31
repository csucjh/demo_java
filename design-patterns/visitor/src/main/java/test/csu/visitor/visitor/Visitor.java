package test.csu.visitor.visitor;

import test.csu.visitor.elem.Circle;
import test.csu.visitor.elem.Rectangle;
import test.csu.visitor.elem.Square;

public interface Visitor<T> {
    T visit(Square element);

    T visit(Circle element);

    T visit(Rectangle element);
}
