import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 7/21/2020 3:24
 */
public class GroupSplit {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        numbers.add(10);
        numbers.add(10);
        numbers.add(14);
        numbers.add(9);
        numbers.add(8);
        numbers.add(15);

        boolean beforeIsTen = false;

        List<Integer> temp = new ArrayList<>();

        result.add(temp);

        for (int i = 0; i < numbers.size() - 1; i++) {

            Integer current = numbers.get(i);

            Integer next = numbers.get(i + 1);

            //转折点
            if ((current > 10 && next < 10) || current < 10 && next > 10) {

                temp.add(current);

                temp.add(10);

                temp = new ArrayList<>();

                result.add(temp);

                temp.add(10);

                beforeIsTen = false;

                if (i == numbers.size() - 2) {
                    temp.add(next);
                }

            } else if (current == 10) {

                if (!beforeIsTen && i > 0) {
                    temp.add(current);
                }

                boolean allTens = true;
                for (int j = 0; j <= temp.size() - 1; j++) {
                    if (temp.get(j) != 10) {
                        allTens = false;
                        break;
                    }
                }

                if (temp.size() > 0 && allTens) {
                    temp.add(current);
                } else {
                    if (next == 10) {
                        temp = new ArrayList<>();
                        result.add(temp);
                        temp.add(current);
                    }

                    if (i == numbers.size() - 2) {
                        temp.add(next);
                    }
                }

                beforeIsTen = true;

            } else {

                if (beforeIsTen) {
                    temp = new ArrayList<>();
                    result.add(temp);
                    temp.add(10);
                }

                temp.add(current);

                if (i == numbers.size() - 2) {
                    temp.add(next);
                }

                beforeIsTen = false;
            }
        }

        System.out.println(result);

    }

}
