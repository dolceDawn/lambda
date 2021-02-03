import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/15/2020 17:20
 */
public class SqlJoin {

    public static void main(String[] args) {

        List<Long> appIds = new ArrayList<>();

        appIds.add(1000L);
        appIds.add(1001L);

        String appIdStr = StringUtils.join(appIds, ",");

        String[] appIdStrs = appIdStr.split(",");

        String appIdSqlStr = ServiceUtil.mergeStrForIn(appIdStrs, "app_id");

        System.out.println(appIdSqlStr);

        String str = "10s";

        System.out.println(str.substring(0, str.length() -1));
        

    }

}
