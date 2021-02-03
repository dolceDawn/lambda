/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 8/6/2020 6:37
 */
public class Replace {

    private final static String URI_CONFIG_COMPUTOR_SENSOR = "/configuration/computeSensor/plant/";
    private final static String URI_CONFIG_MODEL = "/configuration/model/plant/";
    private final static String URI_CONFIG_RULE = "/configuration/rule/plant/";

    public static void main(String[] args) {

        String url = "calculationsensor/selectCaculationSensors/DPP_01_40001179";

        String[] array = url.split("/");

        String data = "94D7KNSTN685CT53MRE1PDSFD100001GSB10LAC10CY104LAC1";

        System.out.println(data.length());

    }

}
