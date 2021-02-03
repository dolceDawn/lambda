import java.util.HashMap;
import java.util.Map;

public class ExceptTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");

        map.remove("bb");
        try {
            //calcu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void calcu() throws Exception {

        int a = 1;
        int b = 0;

        try {
            System.out.println(a/b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Calcu Error!" + e.getMessage(), e);
        }

    }

}
