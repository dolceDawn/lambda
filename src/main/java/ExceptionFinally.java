/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 8/20/2020 9:35
 */
public class ExceptionFinally {

    public static void main(String[] args) {

        boolean flag = true;

        try {

            Integer aa = 0;

            Integer bb = 10;

            int c = bb / aa;

        } catch (Exception e) {
            System.out.println(e.getMessage());

            flag = false;

            throw e;

        } finally {

            System.out.println(flag);

            System.out.println("finally");

        }


    }

}
