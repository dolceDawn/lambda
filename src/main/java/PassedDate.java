import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PassedDate {

    public static void main(String[] args) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //1.过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, - 6);
        Date d = c.getTime();
        String day = format.format(d)+" 00:00:00";
        System.out.println("过去七天："+day);

        //2.过去6月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -5);
        int firstDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, firstDay);
        Date m = c.getTime();
        String mon = format.format(m)+" 00:00:00";
        System.out.println("过去一个月："+mon);

        //4.过去一年
        c.setTime(new Date());
        c.add(Calendar.MONTH, -11);
        c.set(Calendar.DAY_OF_MONTH, firstDay);
        Date y = c.getTime();
        String year = format.format(y)+" 00:00:00";
        System.out.println("过去一年："+year);

        //自定义时间
        c.setTime(format.parse("2020-04-16 00:00:00"));
        c.add(Calendar.MONTH, 1);
        String auto = format.format(y)+" 00:00:00";

        System.out.println(auto);

    }

}
