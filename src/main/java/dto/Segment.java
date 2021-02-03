package dto;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/16/2020 13:46
 */
public class Segment {

    private Integer start;

    private Integer end;

    private Integer groupIndex;

    public Segment() {
    }

    public Segment(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }
}
