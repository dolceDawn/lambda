import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sdformat {

    private final static Long MILLI_OF_ONE_DAY =  1000 * 3600 * 24 * 1L;

    public static void main(String[] args) throws Exception {

        int interval = -0;

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH, interval);

        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime())+" 00:00:00";

        System.out.println(firstDayOfMonth);


        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date time = sd.parse(firstDayOfMonth);

        long dateTime = time.getTime() / 1000;

        System.out.println(dateTime);

        //getEndDay();

        getCurrentDay();

    }

    public static void getCurrentDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(c.getTime())+" 00:00:00";

        System.out.println(firstDayOfMonth);

    }


    public static void getEndDay() throws Exception {

        int interval = -1;

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH, interval);

        int endDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, endDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime())+" 00:00:00";

        System.out.println(firstDayOfMonth);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date time = sd.parse(firstDayOfMonth);

        long dateTime = time.getTime() / 1000;

        System.out.println(dateTime);


    }

}
