package sort;

import java.util.Arrays;


/**

 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,6,9,2,5,3,7,4,8};
        //调用快速排序算法
        quick(arr,0,arr.length-1);
//        //打印排序的后结果，查看是否正确
//        quickSort2(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));


    }

    /**
     * 快速排序算法
     * @param arr 被排序的算法
     * @param start 快速排序的起始位置
     * @param end 快速排序的结束位置
     */
    public static void quick(int[] arr,int start,int end){

        if(start < end){
            int stand = arr[start];

            //开始位置
            int low = start;
            //结束位置
            int high = end;

            while (low < high){

                while (low < high && arr[high] > stand){
                    high--;
                }
                arr[low] = arr[high];

                while (low < high && arr[low] < stand){
                    low++;
                }

                arr[high] = arr[low];
            }

            arr[low] = stand;

            quick(arr,start,low);
            //然后出来高位的数字
            quick(arr,low+1,end);
        }
    }



    //plant B
    static void quickSort2(int s[], int l, int r)
    {
        if (l < r)
        {
            int i = AdjustArray(s, l, r);//先成挖坑填数法调整s[]
            quickSort2(s, l, i - 1); // 递归调用
            quickSort2(s, i + 1, r);
        }
    }



    //快速排序
    static int AdjustArray(int s[], int l, int r) //返回调整后基准数的位置
    {
        int i = l, j = r;
        int x = s[l]; //s[l]即s[i]就是第一个坑
        while (i < j)
        {
            // 从右向左找小于x的数来填s[i]
            while(i < j && s[j] >= x)
                j--;
            if(i < j)
            {
                s[i] = s[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while(i < j && s[i] < x)
                i++;
            if(i < j)
            {
                s[j] = s[i]; //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        s[i] = x;

        return i;
    }

}
