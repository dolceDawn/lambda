import java.util.Objects;

/**
 * @author allan
 * @version 1.0
 * @date Created in 1:28 PM 11/22/2019
 * @Description ${description}
 */
public class TrainingTimeRangeVO implements Comparable<TrainingTimeRangeVO> {
    private Long id;
    /**
     * 训练段id
     */
    private Long trainingPeriodId;

    /**
     * 训练段名称
     */
    private String periodName;

    /**
     * 状态
     *
     */
    private Integer status;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

    private Integer duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainingPeriodId() {
        return trainingPeriodId;
    }

    public void setTrainingPeriodId(Long trainingPeriodId) {
        this.trainingPeriodId = trainingPeriodId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TrainingTimeRangeVO{" +
                "id=" + id +
                ", trainingPeriodId=" + trainingPeriodId +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
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

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
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
