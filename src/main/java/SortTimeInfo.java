import dto.TimeInfos;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/29/2020 10:48
 */
public class SortTimeInfo {

    public static void main(String[] args) throws Exception {

        List<TimeInfos> times = new ArrayList<>();

        TimeInfos t1 = new TimeInfos (1588467600000L, 1588470000000L, 3);
        TimeInfos t2 = new TimeInfos (1588727400000L, 1588728600000L, 1);
        TimeInfos t3 = new TimeInfos (1588779600000L, 1588780380000L, 3);
        TimeInfos t4 = new TimeInfos (1588812000000L, 1588812900000L, 1);
        TimeInfos t5 = new TimeInfos (1588813800000L, 1588815600000L, 3);
        TimeInfos t6 = new TimeInfos (1589073000000L, 1589074800000L, 1);
        TimeInfos t7 = new TimeInfos (1588812900000L, 1588813800000L, 1);
        TimeInfos t8 = new TimeInfos (1588410000000L, 1588467600000L, 1);
        TimeInfos t9 = new TimeInfos (1588470000000L, 1588727400000L, 3);
        TimeInfos t10 = new TimeInfos(1588728600000L, 1588779600000L, 1);
        TimeInfos t11 = new TimeInfos(1588780380000L, 1588812000000L, 3);
        TimeInfos t12 = new TimeInfos(1588815600000L, 1589073000000L, 1);
        TimeInfos t13 = new TimeInfos(1589074800000L, 1590893233000L, 1);


        times.add(t1);
        times.add(t2);
        times.add(t3);
        times.add(t4);
        times.add(t5);
        times.add(t6);
        times.add(t7);
        times.add(t8);
        times.add(t9);
        times.add(t10);
        times.add(t11);
        times.add(t12);
        times.add(t13);

        times.stream().forEach(a->a.setGroupIndex(null));

        Integer groupIndex = 0;

        //合并连续已计算段
        if (times.size() > 1) {

            for (int i = 0; i < times.size() -1; i++ ) {

                if (times.get(i).getType().equals(times.get(i + 1).getType())) {
                    times.get(i).setGroupIndex(groupIndex);
                    times.get(i + 1).setGroupIndex(groupIndex);
                } else {
                    groupIndex = groupIndex + 1;
                }
            }

        }

        Map<Integer, List<TimeInfos>> groupInfo = times.stream().filter(a->a.getGroupIndex() != null).collect(Collectors.groupingBy(a->a.getGroupIndex()));

        System.out.println(groupInfo);

    }

}
