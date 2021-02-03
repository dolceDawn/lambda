import java.util.Objects;

/**
 * @author zhangliming
 * @version 1.0
 * @date Created in 10:54 AM 11/22/2019
 * @Description ${description}
 */
public class TrendWarningChangeLogVO {

    private Long id;

    private Long bgTime;

    private String bgDate;

    private Long endTime;

    private String endDate;

    private Long requestId;

    /**
     * 操作类型 0：删除，1：增加
     */
    private Integer operateType;

    /**
     * 状态 0：未处理，1：同意，2:拒绝
     */
    private Integer state;

    private Long periodId;

    /**
     * 全局时间名称
     */
    private String periodName;

    /**
     * 描述
     */
    private String desc;

    public TrendWarningChangeLogVO() {
    }

    public TrendWarningChangeLogVO(Long bgTime, Long endTime, Integer operateType, String periodName, Long periodId) {
        this.bgTime = bgTime;
        this.endTime = endTime;
        this.operateType = operateType;
        this.periodName = periodName;
        this.periodId = periodId;
    }

    public TrendWarningChangeLogVO(Long bgTime, Long endTime, Integer operateType) {
        this.bgTime = bgTime;
        this.endTime = endTime;
        this.operateType = operateType;
    }

    public TrendWarningChangeLogVO(Long id, Long bgTime, Long endTime, Long requestId, Integer operateType, Integer state) {
        this.id = id;
        this.bgTime = bgTime;
        this.endTime = endTime;
        this.requestId = requestId;
        this.operateType = operateType;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBgTime() {
        return bgTime;
    }

    public void setBgTime(Long bgTime) {
        this.bgTime = bgTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBgDate() {
        return bgDate;
    }

    public void setBgDate(String bgDate) {
        this.bgDate = bgDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof TrendWarningChangeLogVO)) {return false;}
        TrendWarningChangeLogVO logVO = (TrendWarningChangeLogVO) o;
        return getBgTime().equals(logVO.getBgTime()) &&
                getEndTime().equals(logVO.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBgTime(), getEndTime());
    }

    @Override
    public String toString() {
        return "TrendWarningChangeLogVO{" +
                "id=" + id +
                ", bgTime=" + bgTime +
                ", bgDate='" + bgDate + '\'' +
                ", endTime=" + endTime +
                ", endDate='" + endDate + '\'' +
                ", requestId=" + requestId +
                ", operateType=" + operateType +
                ", state=" + state +
                ", periodId=" + periodId +
                ", periodName='" + periodName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
