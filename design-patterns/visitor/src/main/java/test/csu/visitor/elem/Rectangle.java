package test.csu.visitor.elem;

import test.csu.visitor.visitor.Visitor;

public class Rectangle implements Element {

    public final double width;
    public final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
