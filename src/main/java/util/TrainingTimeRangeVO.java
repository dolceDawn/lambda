package util;

import java.util.Objects;

/**
 * @author allan
 * @version 1.0
 * @date Created in 1:28 PM 11/22/2019
 * @Description ${description}
 */
public class TrainingTimeRangeVO implements Comparable<TrainingTimeRangeVO> {
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TrainingTimeRangeVO{" +
                " startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public int compareTo(TrainingTimeRangeVO o) {
        return (int) (startTime - o.startTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof TrainingTimeRangeVO)) {return false;}
        TrainingTimeRangeVO that = (TrainingTimeRangeVO) o;
        return getStartTime().equals(that.getStartTime()) &&
                getEndTime().equals(that.getEndTime());
    }
}
