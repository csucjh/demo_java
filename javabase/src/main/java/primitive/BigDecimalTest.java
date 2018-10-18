package primitive;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        divide();
    }

    public static void divide() {

        BigDecimal result = divide(10011, 100);
        System.out.println(result.toString());

        // 24.135 * 99 = 2390.335  测试四舍五入   输出24.14
        result = divide(2390.335f, 99);
        System.out.println(result.toString());

        // 24.134 * 99 = 2389.226  测试四舍五入   输出24.13
        result = divide(2389.226f, 99);
        System.out.println(result.toString());
    }

    /**
     * 除法  (被除数 ÷ 除数 = 商)

     * @param dividend 被除数
     * @param divisor  除数
     * @returnf
     */
    public static BigDecimal divide(float dividend, int divisor) {
        // 保留两位小数
        // ROUND_HALF_UP 四舍五入
        return new BigDecimal(dividend).divide(new BigDecimal(divisor), 2, BigDecimal.ROUND_HALF_UP);
    }
}
