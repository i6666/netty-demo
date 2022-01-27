package redis;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author zhuang.ma
 * @date 2021/12/22
 */
public class test1 {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(83);
        String format2 = df.format(0);
        System.out.println(format);
        System.out.println(format2);

        BigDecimal bigDecimal = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal.toString());

    }
}
