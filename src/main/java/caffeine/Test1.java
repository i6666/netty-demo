package caffeine;

import java.math.BigDecimal;

/**
 * @author zhuang.ma
 * @date 2021/12/27
 */
public class Test1 {

    public static void main(String[] args) {
        Double v = Double.parseDouble("0.00");
        System.out.println("v="+v);
        double t = 0.00d;
        System.out.println("t="+t);

        BigDecimal bigDecimal = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("b="+bigDecimal);
    }
}
