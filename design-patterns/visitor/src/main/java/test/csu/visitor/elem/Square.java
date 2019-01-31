package test.csu.visitor.elem;

import test.csu.visitor.visitor.Visitor;

public class Square implements Element {

    public final double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
