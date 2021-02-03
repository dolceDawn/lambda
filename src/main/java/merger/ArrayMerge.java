package merger;

import com.alibaba.fastjson.JSON;

public class ArrayMerge {

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,3,4,0,0,0,0};
        int[] num2 = new int[]{4,5,7,9};

        merge2(num1,4, num2, 4);

        System.out.println(JSON.toJSONString(num1));


    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }

        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }


    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m+n-1;
        while(j>=0){
            nums1[k--] = i >= 0 && nums1[i]>nums2[j] ? nums1[i--] : nums2[j--];
        }
    }


}
