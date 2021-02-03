import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AutoIncre {

    public static void main(String[] args) {

        int sameNum = 5;

        int current = 8;

        int caseNum = 9;

        DecimalFormat df = new DecimalFormat("0.0000");

        float result = Float.parseFloat(df.format((float) 5 / 8 * 5 / 9));

        result = (float) Math.sqrt(result);

        System.out.println(result);

        List<String> failureModeNames = new ArrayList<>();
        failureModeNames.add("a");
        failureModeNames.add("b");
        failureModeNames.add("c");

        String modelNameStr = StringUtils.join(failureModeNames.toArray(), "/");

        String[] nameArray = modelNameStr.split("/");


        System.out.println(nameArray);



    }



}
