package string;

import org.apache.commons.lang3.StringUtils;

public class SubStringTest {

    public static void main(String[] args) {
        String reason = "组报文时找不到非空字段 [PLAT_TXN_DATE]P1PECR";
        reason = StringUtils.length(reason) <= 32 ? reason : reason.substring(0, 32);
        System.out.println(reason);
    }
}
