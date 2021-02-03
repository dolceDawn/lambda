package dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelSegment implements Comparable<ModelSegment> {

    private static final ThreadLocal<DateFormat> FORMATS = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Date start;

    private final Date end;

    private List<Integer> alarmIds;

    public ModelSegment(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("start is not null or end is not null");
        }
        if (start.after(end)) {
            throw new IllegalArgumentException("time bucket is invalid start :" + start.toString() + "  end : " + end.toString());
        }
        this.start = (Date) start.clone();

        this.end = (Date) end.clone();
    }

    public ModelSegment(String start, String end) throws ParseException {
        this(parse(start), parse(end));
    }

    public ModelSegment(long startTime, long endTime) {
        this(new Date(startTime), new Date(endTime));
    }


    public Date getStart() {
        if (start == null) {
            return null;
        }
        return (Date) start.clone();
    }

    public Date getEnd() {
        if (end == null) {
            return null;
        }
        return (Date) end.clone();
    }

    public long getStartTime() {
        return start.getTime();
    }

    public long getEndTime() {
        return end.getTime();
    }

    private static Date parse(String str) throws ParseException {
        return FORMATS.get().parse(str);
    }

    private static String format(Date str) {
        return FORMATS.get().format(str);
    }

    @Override
    public String toString() {
        return "startTime=" + format(start) + ", endTime=" + format(end);
    }

    @Override
    public int compareTo(ModelSegment o) {
        if (o.getStartTime() > start.getTime()) {
            return -1;
        } else if (o.getStartTime() < start.getTime()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModelSegment that = (ModelSegment) o;

        if (compareTo(that) != 0) {
            return false;
        }

        if (!start.equals(that.start)) {
            return false;
        }
        return end.equals(that.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    public List<Integer> getAlarmIds() {
        return alarmIds;
    }

    public void setAlarmIds(List<Integer> alarmIds) {
        this.alarmIds = alarmIds;
    }
}
