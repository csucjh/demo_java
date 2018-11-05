package algorithm.rsa;

import java.math.BigDecimal;

public class RSAStudy {

    public static void main(String[] args) {

        // 取一对互质的数比如p, q
        int p = 61, q = 53;

        // 计算n 与 欧拉n
        int n = p * q;
        int ol_n = (p - 1) * (q - 1);

        // 求e；要求 1 < e < 欧拉n 且 e 和 欧拉n 互质；数有很多，比如17
        int e = 17;

        // 求d；要求 e * d % 欧拉n = 1
        int d = 0;
        for (int i = 0; i < ol_n; i++) {
            int temp = (i * ol_n) + 1;
            if (temp % e == 0) {
                d = temp / e;
                if (e * d % ol_n == 1) {
                    System.out.println(i + "..." + d);
                    break;
                }
            }
        }

        // 钥匙A(公钥)为：n和e
        // 钥匙B(私钥)为：n和d

        //用钥匙A加密 123
        int num = 123;
        int a_en = new BigDecimal(num).pow(e).divideAndRemainder(new BigDecimal(n))[1].intValue();
        // 用钥匙B解密
        int a_dn = new BigDecimal(a_en).pow(d).divideAndRemainder(new BigDecimal(n))[1].intValue();

        // 用钥匙B加密 99
        num = 99;
        int b_en = new BigDecimal(num).pow(d).divideAndRemainder(new BigDecimal(n))[1].intValue();
        // 用钥匙A解密 89
        int b_dn = new BigDecimal(b_en).pow(e).divideAndRemainder(new BigDecimal(n))[1].intValue();
    }
}
