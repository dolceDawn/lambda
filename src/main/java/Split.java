import java.util.Date;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 5/9/2020 14:56
 */
public class Split {

    public static final Long TWO_DAY_SENCONDS = 3600 * 1000 * 24 * 2L;

    public static final Long ONE_HALF_MONTH_SENCONDS = 3600 * 1000 * 24 * 45L;

    public static final Long TWO_HALF_MONTH_SENCONDS = 3600 * 1000 * 24 * 75L;

    public static final Long ONE_MONTH_SENCONDS = 3600 * 1000 * 24 * 30L;

    public static void main(String[] args) {

        long bgTime02 = System.currentTimeMillis() - TWO_HALF_MONTH_SENCONDS;
        long end02 = System.currentTimeMillis() - ONE_MONTH_SENCONDS * 2;
        int duration2 = Integer.parseInt((end02 - bgTime02) / 1000 + "");
        int sampleNum2 = duration2 / 60 + 1;

        System.out.println(sampleNum2);

        Date now = new Date();

        System.out.println(now.getTime());

        Long start = 1588824000000L;

        Long end = 1588996800001L;

        long timeBatchs = (end - start) / TWO_DAY_SENCONDS;

        //分段重算
        for (int t = 0; t <= timeBatchs; t++) {

            Long bgTime;
            Long endTime;

            if (t == timeBatchs) {
                bgTime = start + TWO_DAY_SENCONDS * (t);
                endTime = end;
                if (bgTime.equals(endTime)) {
                    return;
                }
            } else {
                bgTime = start + TWO_DAY_SENCONDS * t;
                endTime = start + TWO_DAY_SENCONDS * (t + 1);
            }

            System.out.println("bgTime :" + bgTime + " " + "endTime :" + endTime);

        }

    }

}
