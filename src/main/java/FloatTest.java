import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class FloatTest {

    public static final Long constant = 3600L;

    public static void main(String[] args) {

        Long start = 3600L;

        Long end = 72000L;

        float interval = end - start;

        float livedMonths =interval / constant;

        Map<Integer, Integer> timeAlertInfo = new HashMap<>(6);

        System.out.println(livedMonths);

        Integer oneMonthNum = 0;
        Integer twoMonthNum = 0;
        Integer threeMonthNum = 0;
        Integer fourMonthNum = 0;
        Integer fiveMonthNum = 0;
        Integer sixMonthNum = 0;

        if (livedMonths >= 0 && livedMonths <= AlertHandleTimeEnum.ONE_MONTH.getLivedTime()) {
            oneMonthNum++;
        } else if (livedMonths > AlertHandleTimeEnum.ONE_MONTH.getLivedTime()
                && livedMonths <= AlertHandleTimeEnum.TWO_MONTH.getLivedTime()) {
            twoMonthNum++;
        } else if (livedMonths > AlertHandleTimeEnum.TWO_MONTH.getLivedTime()
                && livedMonths <= AlertHandleTimeEnum.THREE_MONTH.getLivedTime()) {
            threeMonthNum++;
        } else if (livedMonths > AlertHandleTimeEnum.THREE_MONTH.getLivedTime()
                && livedMonths <= AlertHandleTimeEnum.FOUR_MONTH.getLivedTime()) {
            fourMonthNum++;
        } else if (livedMonths > AlertHandleTimeEnum.FOUR_MONTH.getLivedTime()
                && livedMonths <= AlertHandleTimeEnum.FIVE_MONTH.getLivedTime()) {
            fiveMonthNum++;
        } else {
            sixMonthNum++;
        }

        timeAlertInfo.put((int)AlertHandleTimeEnum.ONE_MONTH.getLivedTime(), oneMonthNum);
        timeAlertInfo.put((int)AlertHandleTimeEnum.TWO_MONTH.getLivedTime(), twoMonthNum);
        timeAlertInfo.put((int)AlertHandleTimeEnum.THREE_MONTH.getLivedTime(), threeMonthNum);
        timeAlertInfo.put((int)AlertHandleTimeEnum.FOUR_MONTH.getLivedTime(), fourMonthNum);
        timeAlertInfo.put((int)AlertHandleTimeEnum.FIVE_MONTH.getLivedTime(), fiveMonthNum);
        timeAlertInfo.put((int)AlertHandleTimeEnum.SIX_MONTH.getLivedTime(), sixMonthNum);

        System.out.println(JSON.toJSONString(timeAlertInfo));


    }

}
