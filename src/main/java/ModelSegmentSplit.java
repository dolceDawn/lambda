import dto.ModelSegment;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 7/2/2020 3:18
 */
public class ModelSegmentSplit {

    private final static Long MILLI_OF_FIVE_DAY = 1000 * 3600 * 24 * 5L;

    private final static Long MILLI_OF_EIGHT_DAY = 1000 * 3600 * 24 * 8L;

    private final static Long MILLI_OF_TEN_DAY = 1000 * 3600 * 24 * 10L;

    public static void main(String[] args) {

        Map<String, List<ModelSegment>> toCalcuedMap = new HashMap<>();

        List<ModelSegment> segments01 = new ArrayList<>();
        List<ModelSegment> segments02 = new ArrayList<>();

        //2020-06-01 01:00:00 - 2020-06-05 01:00:00
        ModelSegment seg1 = new ModelSegment(1590973200000L, 1591318800000L);
        //2020-06-05 02:00:00 - 2020-06-25 02:00:00
        ModelSegment seg2 = new ModelSegment(1591322400000L, 1593050400000L);
        //2020-06-25 03:00:00 - 2020-06-26 03:00:00
        ModelSegment seg3 = new ModelSegment(1593054000000L, 1593140400000L);
        //2020-06-27 03:00:00 - 2020-07-13 03:00:00
        ModelSegment seg4 = new ModelSegment(1593226800000L, 1594609200000L);

        //2020-06-02 14:00:00 - 2020-06-07 14:00:00
        //ModelSegment seg4 = new ModelSegment(1591106400000L, 1591538400000L);
        //2020-06-12 18:00:00 - 2020-06-13 18:00:00
        //ModelSegment seg5 = new ModelSegment(1591984800000L, 1592071200000L);

        segments01.add(seg1);
        segments01.add(seg2);
        segments01.add(seg3);
        segments01.add(seg4);



        toCalcuedMap.put("dawn", segments01);
//        toCalcuedMap.put("dolce", segments02);



        //超过十天的数据，切分后剩余的段
        Map<String, ModelSegment> leftSegment = new HashMap<>(10);

        //分批次后最后的结束点，方便下批次开始点
        Map<String, List<Long>> splitEndPoints = new HashMap<>(10);

        while (true) {
            Map<String, List<ModelSegment>> tempSegments = new HashMap<>(16);
                    //分批计算的段
            spliteToCalcuSegment(toCalcuedMap, tempSegments, leftSegment, splitEndPoints);
            if (tempSegments.size() == 0) {
                break;
            }
            tempSegments.clear();
        }

        System.out.println("end");

    }


    private static void spliteToCalcuSegment(Map<String, List<ModelSegment>> toCalcuedMap, Map<String, List<ModelSegment>> tempSegments
            , Map<String, ModelSegment> leftSegment, Map<String, List<Long>> splitEndPoints) {

        for (Map.Entry<String, List<ModelSegment>> entry : toCalcuedMap.entrySet()) {

            //排序
            List<ModelSegment> segments = entry.getValue().stream()
                    .sorted(Comparator.comparing(ModelSegment::getStartTime)).collect(Collectors.toList());

            //剩余段是否大于十天
            if (leftSegment.get(entry.getKey()) != null) {

                ModelSegment leftSeg = leftSegment.get(entry.getKey());

                long leftStart = leftSeg.getStartTime();
                long leftEnd = leftSeg.getEndTime();

                if (leftEnd - leftStart >= MILLI_OF_TEN_DAY) {

                    //取八天数据,重新构造分段
                    ModelSegment rebuild = new ModelSegment(leftStart, leftStart + MILLI_OF_EIGHT_DAY);
                    List<ModelSegment> tempSegs = tempSegments.get(entry.getKey());
                    if (tempSegs == null) {
                        tempSegs = new ArrayList<>();
                        tempSegments.put(entry.getKey(), tempSegs);
                    }
                    tempSegs.add(rebuild);

                    //重新构造剩余的段
                    ModelSegment leftbuild = new ModelSegment(leftStart + MILLI_OF_EIGHT_DAY, leftEnd);
                    leftSegment.put(entry.getKey(), leftbuild);

                    //继续下一个模型
                    break;
                }

                if (leftEnd - leftStart < MILLI_OF_TEN_DAY) {
                    //剩余段小于10天，且splitEndPoints最近一个点大于leftEnd
                    List<Long> lastEnds = splitEndPoints.get(entry.getKey());
                    Long lastestPoint = lastEnds.get(lastEnds.size() - 1);
                    if (leftEnd <= lastestPoint) {
                        List<ModelSegment> tempSegs = tempSegments.get(entry.getKey());
                        if (tempSegs == null) {
                            tempSegs = new ArrayList<>();
                            tempSegments.put(entry.getKey(), tempSegs);
                        }
                        tempSegs.add(leftSeg);
                        leftSegment.clear();
                    }
                }
            }


            for (int i = 0; i <= segments.size() - 1; i++) {
                ModelSegment curSeg = segments.get(i);
                Long curBegin = curSeg.getStartTime();
                Long curEnd = curSeg.getEndTime();

                List<Long> lastEnds = splitEndPoints.get(entry.getKey());
                if (lastEnds == null) {
                    lastEnds = new ArrayList<>();
                    splitEndPoints.put(entry.getKey(), lastEnds);
                }

                if (lastEnds.size() > 0) {
                    //最后一个点
                    Long lastestPoint = lastEnds.get(lastEnds.size() - 1);

                    if (curEnd.longValue() <= lastestPoint.longValue()) {
                        continue;
                    }
                }

                if (curEnd - curBegin >= MILLI_OF_TEN_DAY) {

                    if (tempSegments.get(entry.getKey()) != null) {
                        break;
                    }

                    //取八天数据,重新构造分段
                    ModelSegment rebuild = new ModelSegment(curBegin, curBegin + MILLI_OF_EIGHT_DAY);
                    List<ModelSegment> tempSegs = tempSegments.get(entry.getKey());
                    if (tempSegs == null) {
                        tempSegs = new ArrayList<>();
                        tempSegments.put(entry.getKey(), tempSegs);
                    }
                    tempSegs.add(rebuild);

                    //重新构造剩余的段
                    ModelSegment leftbuild = new ModelSegment(curBegin + MILLI_OF_EIGHT_DAY, curEnd);
                    leftSegment.put(entry.getKey(), leftbuild);

                    //记录结束点
                    List<Long> endPoints = splitEndPoints.get(entry.getKey());
                    endPoints.add(curEnd);
                    //继续下一个模型
                    break;
                } else {
                    List<ModelSegment> tempSegs = tempSegments.get(entry.getKey());
                    if (tempSegs == null) {
                        tempSegs = new ArrayList<>();
                        tempSegments.put(entry.getKey(), tempSegs);
                    }

                    //首先加入因为切分大段剩余的段
                    if (leftSegment.get(entry.getKey()) != null) {
                        tempSegs.add(leftSegment.get(entry.getKey()));

                        leftSegment.clear();
                    }

                    //判断是否已经积累了十天的数据段
                    if (tempSegs.size() > 1) {
                        long firstStart = tempSegs.get(0).getStartTime();
                        if (curEnd - firstStart >= MILLI_OF_TEN_DAY) {
                            //记录最后结束点
                            List<Long> endPoints = splitEndPoints.get(entry.getKey());

                            long lastTempPoint = tempSegs.get(tempSegs.size() - 1).getEndTime();
                            endPoints.add(lastTempPoint);

                            break;
                        }
                    }

                    tempSegs.add(curSeg);

                    List<Long> endPoints = splitEndPoints.get(entry.getKey());
                    endPoints.add(curSeg.getEndTime());
                }
            }
        }
    }

}
