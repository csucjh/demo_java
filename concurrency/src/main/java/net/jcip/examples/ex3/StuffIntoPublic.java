package net.jcip.examples.ex3;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication  不安全发布
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {

    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
