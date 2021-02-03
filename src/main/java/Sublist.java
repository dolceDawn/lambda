/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/16/2020 10:48
 */
public class Sublist {

    public static void main(String[] args) {

        Apple a1 = new Apple("1234");

        test(a1);

        System.out.println(a1);


    }


    public static void test(Apple a1) {

        Apple a2 = new Apple("dddd");

        a1.setName("aaaa");

        a1 = a2;

    }



}
