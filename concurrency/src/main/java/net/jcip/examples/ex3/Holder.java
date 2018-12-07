package net.jcip.examples.ex3;

/**
 * Holder
 * <p/>
 * Class at risk of failure if not properly published
 * 没有被正确发布，处于失败的风险中
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n)
            throw new AssertionError("This statement is false.");
    }
}
