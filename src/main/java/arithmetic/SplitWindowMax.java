package arithmetic;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 */
public class SplitWindowMax {

    public static void main(String[] args) {
        int[] all = new int[]{1,-2,3,-5,6,7};

        System.out.println(JSON.toJSONString(maxSlidingWindow(all, 2)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 非空判断
        if (nums == null || k <= 0) return new int[0];
        // 最终结果数组
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < res.length; i++) {
            // 初始化最大值
            int max = nums[i];
            // 循环 k-1 次找最大值
            for (int j = i + 1; j < (i + k); j++) {
                max = (nums[j] > max) ? nums[j] : max;
            }
            res[i] = max;
        }
        return res;
    }


    public static int[] maxSlidingWindow2(int[] nums, int k) {
        // 非空判断
        if (nums == null || k <= 0) return new int[]{};
        // 最终结果数组
        int[] res = new int[nums.length - k + 1];
        // 优先队列
        PriorityQueue<Integer> queue = new PriorityQueue(res.length, (Comparator<Integer>) (i1, i2) -> {
            // 倒序排列（从大到小，默认是从小到大）
            return i2 - i1;
        });

        // 第一轮元素添加
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        res[0] = queue.peek();
        int last = nums[0]; // 每轮要移除的元素
        for (int i = k; i < nums.length; i++) {
            // 移除滑动窗口之外的元素
            queue.remove(last);
            // 添加新元素
            queue.offer(nums[i]);
            // 存入最大值
            res[i - k + 1] = queue.peek();
            // 记录每轮要移除的元素（滑动窗口最左边的元素）
            last = nums[i - k + 1];
        }
        return res;
    }


}