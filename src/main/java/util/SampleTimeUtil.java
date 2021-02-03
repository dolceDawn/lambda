package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author allan
 * @version 1.0
 * @date Created in 10:07 AM 8/7/2019
 * @Description ${description}
 */
public class SampleTimeUtil {

    private static final Long BEGIN = 0L;

    private static final Long END = 1L;

    private static final Long POINT = 2L;

    private SampleTimeUtil() {
    }

    /**
     * 单例
     *
     * @return
     */
    public static SampleTimeUtil getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    /**
     * 单例枚举
     */
    private enum SingletonEnum {
        /**
         * 单例
         */
        INSTANCE;
        private SampleTimeUtil instance;

        SingletonEnum() {
            instance = new SampleTimeUtil();
        }

        public SampleTimeUtil getInstance() {
            return instance;
        }
    }


    /**
     * 时间树
     */
    public static class TimeTree {
        private Long value;
        private Long type;
        private TimeTree left;
        private TimeTree right;
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }


        public TimeTree getLeft() {
            return left;
        }

        public void setLeft(TimeTree left) {
            this.left = left;
        }

        public TimeTree getRight() {
            return right;
        }

        public void setRight(TimeTree right) {
            this.right = right;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        public Long getType() {
            return type;
        }

        public void setType(Long type) {
            this.type = type;
        }
    }

    /**
     * 片段类型
     */
    public List<Long> periodType = Arrays.asList(BEGIN, END);

    /**
     * 添加节点
     *
     * @param tree
     * @param time
     * @param type
     * @return
     */
    public TimeTree putTime(TimeTree tree, Long time, Long type) {
        boolean flag = tree == null ? false : tree.value.compareTo(time) == 0
                && (!tree.type.equals(type) && periodType.contains(tree.type) && periodType.contains(type));
        if (tree == null) {
            tree = new TimeTree();
            tree.setValue(time);
            tree.setType(type);
            tree.setNum(1);
        } else if (tree.value.compareTo(time) > 0) {
            tree.left = putTime(tree.left, time, type);
        } else if (tree.value.compareTo(time) < 0) {
            tree.right = putTime(tree.right, time, type);
        } else if (flag) {
            --tree.num;
            if (tree.num == 0) {
                tree.setType(POINT);
            }

        } else if (tree.value.compareTo(time) == 0
                && tree.type.equals(type)) {
            tree.setNum(1 + tree.getNum());
        } else if (tree.value.compareTo(time) == 0
                && POINT.equals(tree.type)) {

        }
        return tree;
    }


    /**
     * 二叉树中序排序
     *
     * @param node
     * @param btList
     */
    public void orderTree(TimeTree node, List<Long[]> btList) {
        if (node.getLeft() != null) {
            orderTree(node.getLeft(), btList);
        }
        for (int i = 0; i < node.num; ++i) {
            Long[] nodes = new Long[]{node.getValue(), node.getType()};
            btList.add(nodes);
        }
        if (node.getRight() != null) {
            orderTree(node.getRight(), btList);
        }
    }

    /**
     * 片段
     */
    public static class Periods {
        private Long begin;
        private Long end;

        public Long getBegin() {
            return begin;
        }

        public void setBegin(Long begin) {
            this.begin = begin;
        }

        public Long getEnd() {
            return end;
        }

        public void setEnd(Long end) {
            this.end = end;
        }
    }

    /**
     * 游标提取
     *
     * @param orderList
     * @return
     */
    public List<Periods> transList(List<Long[]> orderList) {
        List<Periods> periodList = new ArrayList<>();
        int beginNum = 0;
        int endNum = 0;
        Long beginValue = 0L;
        boolean flag = false;


        for (Long[] order : orderList) {
            if (!flag) {
                if (BEGIN.equals(order[1])) {
                    beginValue = order[0];
                    ++beginNum;
                    flag = true;
                } else if (POINT.equals(order[1])) {
                    Periods periods = new Periods();
                    periods.setBegin(order[0]);
                    periods.setEnd(order[0]);
                    periodList.add(periods);
                    flag = true;
                }
            } else if (BEGIN.equals(order[1])) {
                ++beginNum;
            } else if (END.equals(order[1]) && beginNum != endNum) {
                ++endNum;
            }

            if (END.equals(order[1]) && beginNum == endNum) {
                Periods periods = new Periods();
                periods.setBegin(beginValue);
                periods.setEnd(order[0]);
                periodList.add(periods);

                //重设
                beginNum = 0;
                endNum = 0;
                beginValue = 0L;
                flag = false;
            }
        }

        return periodList;
    }

}
