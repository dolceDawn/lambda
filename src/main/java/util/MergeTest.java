package util;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

public class MergeTest {

    public static void main(String[] args) {

        List<TrainingTimeRangeVO> hasMergedList = new ArrayList<>();

        List<TrainingTimeRangeVO> allRanges = new ArrayList<>();

        TrainingTimeRangeVO t1 = new TrainingTimeRangeVO();
        t1.setStartTime(1L);
        t1.setEndTime(3L);

        TrainingTimeRangeVO t2 = new TrainingTimeRangeVO();
        t2.setStartTime(3L);
        t2.setEndTime(4L);

        allRanges.add(t1);
        allRanges.add(t2);

        allRanges = allRanges.stream().sorted(Comparator.comparing(TrainingTimeRangeVO::getStartTime)).collect(Collectors.toList());

        SampleTimeUtil tree = SampleTimeUtil.getInstance();
        SampleTimeUtil.TimeTree node = null;
        for (TrainingTimeRangeVO timeRange : allRanges) {
            node = tree.putTime(node, timeRange.getStartTime(), 0L);
            node = tree.putTime(node, timeRange.getEndTime(), 1L);
        }
        List<Long[]> btList = new ArrayList<>();
        tree.orderTree(node, btList);
        List<SampleTimeUtil.Periods> periodList = tree.transList(btList);


        System.out.println(JSON.toJSONString(periodList));

    }

}
