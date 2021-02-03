import java.util.List;

/**
 * 设备树
 *
 * @author zhangliming
 * @date 2020/07/13
 */
public class ScreenTree implements Cloneable {

    private String id;

    private String name;

    private String parentid;

    private String typecode;

    private String referid;

    private List<ScreenTree> childs;

    private boolean hasPermission = true;

    private boolean alarmViewPermission = false;

    private Long totalNum;

    private Long currentNum;

    private Integer speciality;

    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getReferid() {
        return referid;
    }

    public void setReferid(String referid) {
        this.referid = referid;
    }

    public List<ScreenTree> getChilds() {
        return childs;
    }

    public void setChilds(List<ScreenTree> childs) {
        this.childs = childs;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public boolean isAlarmViewPermission() {
        return alarmViewPermission;
    }

    public void setAlarmViewPermission(boolean alarmViewPermission) {
        this.alarmViewPermission = alarmViewPermission;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Long currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Integer speciality) {
        this.speciality = speciality;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ScreenTree(String id, String name, String parentid, String typecode) {
        this.id = id;
        this.name = name;
        this.parentid = parentid;
        this.typecode = typecode;
    }

    @Override
    public Object clone() {
        ScreenTree tree = null;
        try {
            tree = (ScreenTree) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return tree;
    }

    public ScreenTree(){}
}
