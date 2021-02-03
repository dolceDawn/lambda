/**
 * AlertHandleTimeEnum
 * @author zhangliming
 * Created by zhangliming on 2020/4/7.
 */
public enum AlertHandleTimeEnum {

    /**
     * ONE_LEVEL
     */
    ONE_MONTH
    {
        @Override
        public float getLivedTime(){
            return 1.0f;
        }
    },
    /**
     * TWO_MONTH
     */
    TWO_MONTH
    {
        @Override
        public float getLivedTime(){
            return 2.0f;
        }
    },
    /**
     * THREE_MONTH
     */
    THREE_MONTH
    {
        @Override
        public float getLivedTime(){
            return 3.0f;
        }
    },
    /**
     * FOUR_MONTH
     */
    FOUR_MONTH
    {
        @Override
        public float getLivedTime(){
            return 4.0f;
        }
    },
    /**
     * FIVE_MONTH
     */
    FIVE_MONTH
    {
        @Override
        public float getLivedTime(){
            return 5.0f;
        }
    },
    /**
     * SIX_MONTH
     */
    SIX_MONTH
    {
        @Override
        public float getLivedTime(){
            return 6.0f;
        }
    }
    ;

    public abstract float getLivedTime();

}
