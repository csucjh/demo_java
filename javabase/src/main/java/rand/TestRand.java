package rand;

public class TestRand {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println((int) (Math.random() * 1000));
        }
    }
}
