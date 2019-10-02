package pecs;

import pecs.fruit.Apple;
import pecs.fruit.Fruit;
import pecs.fruit.Orange;
import pecs.meat.Meat;

/**
 * 什么是PECS（Producer Extends Consumer Super）原则：
 *
 * 频繁往外读取内容的，适合用上界Extends。(作为Producer像程序输出内容)
 * 经常往里插入的，适合用下界Super。(作为Consumer从程序接收内容)
 */
public class PECSTest {

    public static void main(String[] args) {


    }

    /**
     * 上界<? extends T>不能往里存，只能往外取
     *
//     * 一个能放水果以及一切是水果派生类的盘子。再直白点就是：啥水果都能放的盘子。这和我们人类的逻辑就比较接近了。
     * Plate<? extends Fruit>和Plate<Apple>最大的区别就是：Plate<? extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类。
     * 直接的好处就是，我们可以用“苹果盘子”给“水果盘子”赋值了
     */
    public static void upperBoundsWildcards() {
        Plate<? extends Fruit> fruitPlate = new Plate<Fruit>(new Fruit());
        Plate<? extends Fruit> applePlate = new Plate<Apple>(new Apple());
        Plate<? extends Fruit> orangePlate = new Plate<Orange>(new Orange());
//        applePlate.set(new Apple());
//        applePlate.set(new Fruit());
    }

    /**
     * 下界<? super T>不影响往里存，但往外取只能放在Object对象里(get部分失效)
     *
     * 一个能放水果以及一切是水果基类的盘子。Plate<? super Fruit>是Plate<Fruit>的基类，但不是Plate<Apple>的基类.
     * 因为下界规定了元素的最小粒度的下限，实际上是放松了容器元素的类型控制。既然元素是Fruit的基类，那往里存粒度比Fruit小的都可以。
     * 但往外读取元素就费劲了，只有所有类的基类Object对象才能装下。但这样的话，元素的类型信息就全部丢失
     */
    public static void lowerBoundsWildcards() {
        Plate<? super Fruit> p = new Plate<Fruit>(new Fruit());
//        Plate<? super Fruit> p = new Plate<Foods>(new Meat());
//        Plate<? super Foods> p = new Plate<Foods>(new Foods());

        //存入元素正常
//        p.set(new Foods());
//        p.set(new Meat());
        p.set(new Fruit());
        p.set(new Orange());

        //读取出来的东西只能存放在Object类里。
//        Apple newFruit3 = p.get(); //Error
//        Fruit newFruit1 = p.get(); //Error

        Object newFruit2 = p.get();
    }
}
