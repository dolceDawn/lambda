import java.util.List;

public class Lambda {

    public static void main(String[] args) {
        RunObj robj = new RunObj();
        DaWorkerHeartBeatListenScheduledExecutor.execute(robj::runnableTest, 20);


    }

}
