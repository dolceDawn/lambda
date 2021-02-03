import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 5/18/2020 14:06
 */
public class TimeTest {

    public static void main(String[] args) throws Exception {

        Apple a1= new Apple(new String("qazwsxedc"));
        Apple a2= new Apple(new String("qazwsxedc"));


        System.out.println(a1.getName() == a2.getName()); //false


//        Apple a1= new Apple("qazwsxedc");
//        Apple a2= new Apple("qazwsxedc");

        System.out.println(a1.getName() == a2.getName()); //true


        String tag = "W1.UNIT_HUIXIE.#wwwe";


        System.out.println(isContainSpecialChartOp(tag));
    }

    public static boolean isContainSpecialChartOp(String str) {
        String regex = "^[a-zA-Z0-9_][a-zA-Z0-9_]*$";
        return str.matches(regex);
    }


    public static Long getTimeStamp(String sdate) throws Exception {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.parse(sdate).getTime();

    }

}
