import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SplitTest {
    private static final String QUERY_PARAM_PREFIX = "Data";

    private static final String TAG_SEPARATOR = "#";

    private static final String TABLE_SEPARATOR = "_";

    private static final String MIDDLE_DOT = ".";

    public static void main(String[] args) {

        String demoTag = "LDS_DB_1180#TABLE_1001_1000_1004##10CXM00CT001_XQ01";

        System.out.println(demoTag.contains("#10CXM00CT001_XQ01"));

        HashMap<String, String> triadMap = new HashMap<>();
        triadMap.put("1001","1437");
        triadMap.put("1000","KuoKuang");
        triadMap.put("1004","ST10");

        List<String> tags = new ArrayList<>();
        tags.add(demoTag);

        String result = demoTag.substring(demoTag.split("#")[0].length() + 1 + demoTag.split("#")[1].length() + 1);

        System.out.println(result);


        List<TriadTag> triadTags = tags.stream().map(a->{
            String[] triadArray = a.split(TAG_SEPARATOR)[1].split(TABLE_SEPARATOR);
            //组装三元组信息
            String triad = QUERY_PARAM_PREFIX + MIDDLE_DOT;
            for (int i = 1; i < triadArray.length; i++) {
                triad = triad + triadMap.get(triadArray[i]) + (i == triadArray.length - 1 ? "" : MIDDLE_DOT);
            }
            String tag = a.substring(a.split(TAG_SEPARATOR)[0].length() + 1 + a.split(TAG_SEPARATOR)[1].length() + 1);
            TriadTag triadTag = new TriadTag();
            triadTag.setTriad(triad);
            triadTag.setTag(tag);
            return triadTag;
        }).collect(Collectors.toList());

        System.out.println(triadTags);

        List<Apple> apps = new ArrayList<>();
        Apple app = new Apple();
        app.setName("aaa");
        apps.add(app);

        List<Apple> aaa = apps.subList(0,2);

        Map<String, Apple> map = apps.stream().collect(Collectors.toMap(a->a.getName() + "123", b->b));
        System.out.println(map);
    }
}
