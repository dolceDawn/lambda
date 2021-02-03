public class Regix {

    public static void main(String[] args) {

        //String express = "\"DPP_09_40000396\" >= -3.022 AND (\"zj_@JT_XNFD_660_02_0307016_DCS2_20LCH12AA101ZZ\" >= 10.2 OR (\"DPP_05_40001996.zj_JT_XNFD_660_02_0307016_DCS2_20LCH12AA101ZZ.RES\" >= 10.2 AND \"DPP_05_40001996.zj_JT_XNFD_660_02_0307017_2C_dT_1gjsdc.RES\" >= 10.2))";
        String express = "\"sting_XNDF-666666-2-SENSOR7\" >= 0";

        boolean result = expertExpress(express);

        System.out.println(result);

        String res = express.replaceAll("\\s+", " ");

        res = res.replaceAll("\\s+and\\s+", " AND ").replaceAll("\\s+AND\\s+", " AND ")
                .replaceAll("\\s+or\\s+", " OR ").replaceAll("\\s+OR\\s+", " OR ");

        res = res.replaceAll("\\s+([)])", ")");
        res = res.replaceAll("\\s+([(])", " (");
        res = res.replaceAll("([(])\\s+", "(");
        res = res.replaceAll("([)])\\s+", ") ");


        System.out.println(res);

        //number
//        String numner = "-3.022";
//        System.out.println(isContainSpecialChart(numner));

    }

    public static boolean isContainSpecialChart(String str) {
        String regex = "^((\\-)?([1-9]\\d*)|0)(\\.\\d{1,4})?$";
        return str.matches(regex);
    }

    public static boolean expertExpress(String str) {

        //String regex = "^(\\s*)([(]?)(\\s*)(\")[a-zA-Z0-9_.]+[\"](\\s*)([)]?)(\\s*)(>=|<=|>|<|==|!=)(\\s*)((\\-|\\+)?\\d+(\\.\\d+)?)(\\s*)";
        //( "asdfasd.adf.REG")  >= 10.12
        String regex = "^(\\s*)([(]*)(\\s*)(\")[a-zA-Z0-9_.@#-]+[\"](\\s*)(\\s+)(>=|<=|>|<|==|!=)(\\s+)((\\-)?(([1-9]\\d*)|0)(\\.\\d{1,4})?)(\\s*)([)]*)" +
                "((\\s+(AND|OR|and|or)\\s+)(([(]*)(\\s*)(\")[a-zA-Z0-9_.@#-]+[\"](\\s*)(\\s+)(>=|<=|>|<|==|!=)(\\s+)((\\-)?(([1-9]\\d*)|0)(\\.\\d{1,4})?)(\\s*)([)]*)))*$";
        return str.matches(regex);
    }

    public static boolean numberExpress (String number) {

        String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?";

        return number.matches(regex);
    }

}
