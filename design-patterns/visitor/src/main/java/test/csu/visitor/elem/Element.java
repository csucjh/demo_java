package test.csu.visitor.elem;

import test.csu.visitor.visitor.Visitor;

public interface Element {
    <T> T accept(Visitor<T> visitor);
}
