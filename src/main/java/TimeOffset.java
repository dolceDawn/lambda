import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 5/23/2020 17:21
 */
public class TimeOffset {

    public static final Long PRECALCU_SPACE_TIME = 3600 * 1000 * 24 * 60L;


    public static void main(String[] args) throws Exception {

        String sdate = "2020-03-23 14:05:05";

        Long stamp = getTimeStamp(sdate);

        System.out.println(new Date(stamp + PRECALCU_SPACE_TIME));

    }

    public static Long getTimeStamp(String sdate) throws Exception {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.parse(sdate).getTime();

    }

}
