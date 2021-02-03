
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * @author zhangliming
 * @version 1.0
 * @date Created in 1:30 PM 11/21/2019
 * @Description ${description}
 */
public class CalculateDiff {

    public static void main(String[] args) {

        //已引入period合并后的timerange（标准）
        List<TrainingTimeRangeVO> pmRanges = new ArrayList<>();
        TrainingTimeRangeVO range01 = new TrainingTimeRangeVO();
        range01.setStartTime(1000L);
        range01.setEndTime(1020L);
        pmRanges.add(range01);

        TrainingTimeRangeVO range02 = new TrainingTimeRangeVO();
        range02.setStartTime(1040L);
        range02.setEndTime(1060L);
        pmRanges.add(range02);

        TrainingTimeRangeVO range03 = new TrainingTimeRangeVO();
        range03.setStartTime(1080L);
        range03.setEndTime(1100L);
        pmRanges.add(range03);

        TrainingTimeRangeVO range04 = new TrainingTimeRangeVO();
        range04.setStartTime(1120L);
        range04.setEndTime(1140L);
        pmRanges.add(range04);

        TrainingTimeRangeVO range05 = new TrainingTimeRangeVO();
        range05.setStartTime(1160L);
        range05.setEndTime(1180L);
        pmRanges.add(range05);

        //模型已选时间段
        List<Sampletime> trendwarningSampletimes = new ArrayList<>();
        Sampletime samp01 = new Sampletime();
        samp01.setBgtmie(1000L);
        samp01.setEndtime(1020L);
        trendwarningSampletimes.add(samp01);


        Sampletime samp02 = new Sampletime();
        samp02.setBgtmie(1040L);
        samp02.setEndtime(1060L);
        trendwarningSampletimes.add(samp02);


        Sampletime samp03 = new Sampletime();
        samp03.setBgtmie(1150L);
        samp03.setEndtime(1155L);
        trendwarningSampletimes.add(samp03);


        Sampletime samp04 = new Sampletime();
        samp04.setBgtmie(1160L);
        samp04.setEndtime(1180L);
        trendwarningSampletimes.add(samp04);


        //按时间先后排序
        trendwarningSampletimes.sort(comparing(Sampletime::getBgtmie));
        pmRanges.sort(comparing(TrainingTimeRangeVO::getStartTime));

        Long periodVeryBegin = pmRanges.get(0).getStartTime();
        Long periodVeryEnd = pmRanges.get(pmRanges.size() - 1).getEndTime();

        Long spVeryBegin = trendwarningSampletimes.get(0).getBgtmie();
        Long spVeryEnd = trendwarningSampletimes.get(trendwarningSampletimes.size() - 1).getEndtime();

        List<TrendWarningChangeLogVO> logList = new ArrayList<>();

        boolean needCutLeftPart = true;

        //无任何交集
        if (spVeryEnd <= periodVeryBegin || spVeryBegin >= periodVeryEnd) {
            needCutLeftPart = false;
        }


        //有交集
        Iterator<Sampletime> iterator = trendwarningSampletimes.iterator();
        //最后落点信息
        Long lastStopTime =  null;
        int lastStopPoint = 0;
        boolean lastStopGap = false;
        //priod最大时间
        int maxstep = pmRanges.size() - 1;
        //开始遍历sample time
        while (iterator.hasNext()) {
            Sampletime sptime = iterator.next();
            Long spbegin = sptime.getBgtmie();
            int startPoint = 0;
            boolean startGap = false;
            //开始点位置信息
            Map<Integer, Boolean> startInfoMap = locationTimePoint(spbegin, pmRanges);
            for (Map.Entry<Integer, Boolean> entry : startInfoMap.entrySet()) {
                startPoint = entry.getKey().intValue();
                startGap = entry.getValue().booleanValue();
            }
            //结束点位置信息
            Long spend = sptime.getEndtime();
            Map<Integer, Boolean> endInfoMap = locationTimePoint(spend, pmRanges);
            int endPoint = 0;
            boolean endGap = false;
            for (Map.Entry<Integer, Boolean> entry : endInfoMap.entrySet()) {
                endPoint = entry.getKey().intValue();
                endGap = entry.getValue().booleanValue();
            }

            if (lastStopTime == null && !startGap && startPoint >= 0) {
                lastStopTime = periodVeryBegin;
            }

            if (startPoint < 0) {
                if (endPoint < 0) {
                    logList.add(new TrendWarningChangeLogVO(spbegin, spend
                            , ChangeRequestLogOperateType.ADDED.getOperateType()));
                }
                //上岸
                if (endPoint >= 0) {
                    //首段
                    logList.add(new TrendWarningChangeLogVO(spbegin, pmRanges.get(0).getStartTime()
                            , ChangeRequestLogOperateType.ADDED.getOperateType()));
                    //中间段数
                    int moddleNumber = endPoint - 0;
                    if (moddleNumber >= 0) {
                        if (endGap) {
                            for (int i = 0; i <= endPoint; i++) {
                                if (i < endPoint) {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getEndTime(), pmRanges.get(i + 1).getStartTime()
                                            , ChangeRequestLogOperateType.ADDED.getOperateType()));
                                } else {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getEndTime(), spend
                                            , ChangeRequestLogOperateType.ADDED.getOperateType()));
                                }
                            }
                        } else {
                            for (int i = 0; i < endPoint; i++) {
                                logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getEndTime(), pmRanges.get(i + 1).getStartTime()
                                        , ChangeRequestLogOperateType.ADDED.getOperateType()));
                            }
                        }
                    }
                }
            }
            //sampletime的开始点位于periodtime第一个时间段的右侧
            if (startPoint >= 0) {
                //起始段停留在gap中，先算删除的部分
                if (startGap) {
                    //第一次计算diff
                    if (null == lastStopTime) {
                        for (int i = 0; i <= startPoint; i++) {
                            logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getStartTime(), pmRanges.get(i).getEndTime()
                                    , ChangeRequestLogOperateType.DELETE.getOperateType()));
                        }
                    } else {
                        //第二次及以上，计算diff
                        //上一次停留在gap中
                        if (lastStopGap) {
                            int diff = startPoint - lastStopPoint;
                            if (diff > 0) {
                                for (int i = 1; i <= diff; i++) {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(lastStopPoint + i).getStartTime(), pmRanges.get(lastStopPoint + i).getEndTime()
                                            , ChangeRequestLogOperateType.DELETE.getOperateType()));
                                }
                            }
                        } else {
                            //上次停留不在gap中
                            int diff = startPoint - lastStopPoint;
                            logList.add(new TrendWarningChangeLogVO(lastStopTime, pmRanges.get(lastStopPoint).getEndTime()
                                    , ChangeRequestLogOperateType.DELETE.getOperateType()));
                            if (diff > 0) {
                                for (int i = 1; i <= diff; i++) {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(lastStopPoint + i).getStartTime(), pmRanges.get(lastStopPoint + i).getEndTime()
                                            , ChangeRequestLogOperateType.DELETE.getOperateType()));
                                }
                            }
                        }
                    }
                    //新增的部分
                    //首段
                    int moddleNumber = endPoint - startPoint;
                    if (endGap) {
                        if (moddleNumber == 0) {
                            logList.add(new TrendWarningChangeLogVO(spbegin, spend
                                    , ChangeRequestLogOperateType.ADDED.getOperateType()));
                        }
                        if (moddleNumber > 0) {
                            //前
                            logList.add(new TrendWarningChangeLogVO(spbegin, pmRanges.get(startPoint + 1).getStartTime()
                                    , ChangeRequestLogOperateType.ADDED.getOperateType()));

                            //后
                            logList.add(new TrendWarningChangeLogVO(pmRanges.get(endPoint).getEndTime(), spend
                                    , ChangeRequestLogOperateType.ADDED.getOperateType()));

                        }
                    } else {
                        logList.add(new TrendWarningChangeLogVO(spbegin, pmRanges.get(startPoint + 1).getStartTime()
                                , ChangeRequestLogOperateType.ADDED.getOperateType()));
                    }

                    for (int i = 1; i < moddleNumber; i++) {
                        logList.add(new TrendWarningChangeLogVO(pmRanges.get(startPoint + i).getEndTime(), pmRanges.get(startPoint + i + 1).getStartTime()
                                , ChangeRequestLogOperateType.ADDED.getOperateType()));
                    }

                } else {
                    //起始时间不在空隙中
                    //先算删除部分
                    int diff = startPoint - lastStopPoint;
                    if (lastStopGap) {
                        //停止时间在间隙
                        if (diff <= 1) {
                            logList.add(new TrendWarningChangeLogVO(pmRanges.get(lastStopPoint + 1).getStartTime()
                                    , spbegin, ChangeRequestLogOperateType.DELETE.getOperateType()));
                        }
                        if (diff > 1) {
                            for (int i = lastStopPoint + 1; i <= startPoint; i++) {
                                if (i < startPoint) {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getStartTime()
                                            , pmRanges.get(i).getEndTime(), ChangeRequestLogOperateType.DELETE.getOperateType()));
                                } else {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getStartTime()
                                            , spbegin, ChangeRequestLogOperateType.DELETE.getOperateType()));
                                }
                            }
                        }
                    } else {
                        //lastStopPoint不在间隙
                        if (diff == 0) {
                            logList.add(new TrendWarningChangeLogVO(lastStopTime, spbegin, ChangeRequestLogOperateType.DELETE.getOperateType()
                                    , null, null));
                        } else {
                            logList.add(new TrendWarningChangeLogVO(lastStopTime, pmRanges.get(lastStopPoint).getEndTime()
                                    , ChangeRequestLogOperateType.DELETE.getOperateType()));
                            if (diff > 1) {
                                for (int i = lastStopPoint + 1; i < startPoint; i++) {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getStartTime()
                                            , pmRanges.get(i).getEndTime(), ChangeRequestLogOperateType.DELETE.getOperateType()));
                                }
                            }
                            logList.add(new TrendWarningChangeLogVO(pmRanges.get(startPoint).getStartTime(), spbegin
                                    , ChangeRequestLogOperateType.DELETE.getOperateType()));
                        }
                    }
                    //算新增部分（起始时间不在gap）
                    if (endGap) {

                        int addDiff = endPoint - startPoint;

                        if (addDiff == 0) {
                            logList.add(new TrendWarningChangeLogVO(pmRanges.get(startPoint).getEndTime()
                                    , spend, ChangeRequestLogOperateType.ADDED.getOperateType()));
                        }

                        if (addDiff >= 1) {
                            for (int i = startPoint; i <= endPoint; i++) {
                                if (i != endPoint) {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getEndTime()
                                            , pmRanges.get(i + 1).getStartTime(), ChangeRequestLogOperateType.ADDED.getOperateType()));
                                } else {
                                    logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getEndTime()
                                            , spend, ChangeRequestLogOperateType.ADDED.getOperateType()));
                                }
                            }
                        }
                    } else {
                        for (int i = startPoint; i < endPoint; i++ ) {
                            logList.add(new TrendWarningChangeLogVO(pmRanges.get(i).getEndTime()
                                    , pmRanges.get(i + 1).getStartTime(), ChangeRequestLogOperateType.ADDED.getOperateType()));
                        }
                    }
                }
            }
            //更新sampletime endtime 的停留位置
            lastStopTime = spend;
            lastStopPoint = endPoint;
            lastStopGap = endGap;
        }
        //判断最后一个sampletime endtime 落点，来确定是否有需要删除项
        if (needCutLeftPart && lastStopPoint <= maxstep) {
            if (lastStopGap) {
                for (int i = lastStopPoint; i < maxstep; i++) {
                    Long bgTime = pmRanges.get(i + 1).getStartTime();
                    Long endTime = pmRanges.get(i + 1).getEndTime();
                    logList.add(new TrendWarningChangeLogVO(bgTime, endTime, ChangeRequestLogOperateType.DELETE.getOperateType()
                            , pmRanges.get(i + 1).getPeriodName(), pmRanges.get(i + 1).getTrainingPeriodId()));
                }
            } else {
                logList.add(new TrendWarningChangeLogVO(lastStopTime, pmRanges.get(lastStopPoint).getEndTime()
                        , ChangeRequestLogOperateType.DELETE.getOperateType()));

                int diffstep = maxstep - lastStopPoint;
                if (diffstep >= 1) {
                    for (int i = lastStopPoint; i < maxstep; i++) {
                        logList.add(new TrendWarningChangeLogVO(pmRanges.get(i + 1).getStartTime()
                                , pmRanges.get(i + 1).getEndTime(), ChangeRequestLogOperateType.DELETE.getOperateType()
                               ));
                    }
                }
            }
        }
        //删除首尾时间衔接的点
        List<TrendWarningChangeLogVO>  result = logList.stream().filter(a->!a.getBgTime().equals(a.getEndTime())).collect(Collectors.toList());
        System.out.println(result);
    }


    public static Map<Integer, Boolean> locationTimePoint(Long pointTime, List<TrainingTimeRangeVO> periodMergedRanges) {
        Map<Integer, Boolean> position = new HashMap<>(1);
        for (int i = 0; i <= periodMergedRanges.size() - 1; i++) {
            TrainingTimeRangeVO cur = periodMergedRanges.get(i);
            Long curStart = cur.getStartTime();
            Long curEnd = cur.getEndTime();
            if (pointTime < curStart) {
                position.put(i - 1, true);
                return position;
            }
            if (pointTime >= curStart && pointTime <= curEnd) {
                position.put(i, false);
                return position;
            }

            if (pointTime > curEnd) {
                if (i < periodMergedRanges.size() - 1) {
                    continue;
                } else {
                    position.put(i, true);
                    return position;
                }
            }
        }
        return position;
    }


}
