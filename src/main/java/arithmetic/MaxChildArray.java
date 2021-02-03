package arithmetic;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class MaxChildArray {

    public static void main(String[] args) {

        //最大子数组
        int[] all = new int[]{1,-2,3,-5,6,7};

        int max = Integer.MIN_VALUE;
        int begin = 0;
        int end = 0;

        for (int i=0; i < all.length; i++) {
            int sum = 0;
            for (int j=i; j<all.length; j++) {
                sum += all[j];
                if (sum > max) {
                    begin = i;
                    end = j;
                    max = sum;
                }
            }

        }
        System.out.println(max);

        System.out.println(JSON.toJSONString(Arrays.copyOfRange(all, begin, end + 1)));

    }



}
