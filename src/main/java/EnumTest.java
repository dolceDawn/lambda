import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EnumTest {

    public static void main(String[] args) throws Exception {

        float aa = 0.2001f;

        if (aa > 1) {
            System.out.println("success");
        }


        TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");

        //getMonthStart(0, timeZone);

        String id = "DPP_05_4100000";

        System.out.println("DPP_05_4100000" + 1);


        String sensor = "sensorcode01";

        System.out.println("\"" + sensor + "\"" + " >= 0");

    }


    public static String getMonthStart(int interval, TimeZone timeZone) {

        Calendar cal = Calendar.getInstance();

        cal.setTimeZone(timeZone);

        cal.add(Calendar.MONTH, interval);

        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);

        //cal.set(Calendar.DAY_OF_MONTH, 1);

        // 设置日历中月份的最小天数
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(timeZone);
        String firstDayOfMonth = sdf.format(cal.getTime()) + " 00:00:00";

        System.out.println(firstDayOfMonth);

        return firstDayOfMonth;
    }

    public static Date formate(String date) {
        Date target = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
            target = format.parse(date);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return target;
    }

}
