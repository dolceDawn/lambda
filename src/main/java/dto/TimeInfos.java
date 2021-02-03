package dto;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/29/2020 10:46
 */
public class TimeInfos {

    public TimeInfos() {
    }

    public TimeInfos(Long startDate, Long endDate, Integer type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }


    private Long startDate;

    private Long endDate;

    private Integer type;
    private Integer groupIndex;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }
}
