package functional;

/**
 *
 */
public class TestFunctional {

    public static void main(String[] args) {

        driver(x -> System.out.println(x), "car");

        System.out.println("---------------------");

        driver2(x -> System.out.println(x));
    }


    public static void driver(Vehicle vehicle, String name) {
        vehicle.driver(name);
        vehicle.oil(4);
        vehicle.cleaning(2);
    }

    public static void driver2(Vehicle vehicle) {
        vehicle.driver("test");
        vehicle.oil(1);
        vehicle.cleaning(0);
    }
}
