package arithmetic;

import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    /*
     * Write the regular expression in the blank space below
     */
    final static String regularExpression = "^[a-z]{1,6}+([_\\.]?[0-9]{0,4})@(hackerrank.com)$";


    public static void main(String[] args) {

        List<Integer> arrs = new ArrayList<>();

        arrs.add(6);
        arrs.add(6);
        arrs.add(3);
        arrs.add(9);
        arrs.add(3);
        arrs.add(5);
        arrs.add(1);

        Integer target = 12;

        System.out.println(stockPairs(arrs, target));

    }


    public static int stockPairs(List<Integer> stocksProfit, long target) {

        HashSet<Integer> exists = new HashSet<>(100);

        int count = 0;

        for (int i = 0; i <= stocksProfit.size() -1; i++) {

            Integer cur = stocksProfit.get(i);

            if (exists.contains(cur)) {
                continue;
            }

            for (int j = i + 1; j <= stocksProfit.size() -1; j++) {

                Integer next = stocksProfit.get(j);

                long sum = Long.valueOf(cur + next).longValue();

                if (target == sum) {
                    exists.add(cur);
                    exists.add(next);
                    count++;
                }
            }

        }
        return count;
    }


}