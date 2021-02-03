/**
 * 请求日志操作类型
 *
 * @author zhangliming
 * @date 2019/11/26
 */
public enum ChangeRequestLogOperateType {

    /**
     * 删除
     */
    DELETE {
        @Override
        public Integer getOperateType() {
            return 0;
        }
    }
    /**
     * 新增
     */
    , ADDED {
        @Override
        public Integer getOperateType() {
            return 1;
        }
    };

    public abstract Integer getOperateType();

}
