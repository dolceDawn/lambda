/**
 * @author zhangliming
 * Created by zhangliming on 2019/12/09.
 */
public enum DumpStatusEnum {
    /**
     * HAS_DUMPED
     */
    HAS_DUMPED {

        @Override
        public Integer getType() {
            return 1;
        }
    },
    /**
     * NO_DUMP
     */
    NO_DUMP {

        @Override
        public Integer getType() {
            return 0;
        }
    };


    public abstract Integer getType();
}
