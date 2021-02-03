package future;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class DBservice {


    public static void main(String[] args) {

        List<String> lists = new ArrayList<>();

        Callable<List<String>> task = new BatchReadHistoricalDataTask(lists);

        FutureTask<List<String>> futureTask = new FutureTask<>(task);

        // 注意，这边entry.getKey()和connectorId可能不同，后面获取线程返回数据的时候需要处理
        TimeSeriesDbServiceExecutor.execute(futureTask);

        try {
            List<String> results = futureTask.get();
            System.out.println(JSON.toJSONString(results));
        } catch (Exception e) {
            System.out.println("catched : " + e.getMessage());
        }

    }




    private static class BatchReadHistoricalDataTask implements Callable<List<String>> {
        final List<String> tagNameList;

        BatchReadHistoricalDataTask(List<String> tagNameList) {
            this.tagNameList = tagNameList;
        }

        @Override
        public List<String> call() throws Exception {
            // 日志特定字段bizTraceId进行装饰
            Exception exception = null;

            for (int i = 0; i <= 3; i++) {
                try{
                    if (i == 3) {
                        throw new Exception("error happened !");
                    }
                    tagNameList.add(i + "");
                } catch (Exception e) {
                    exception = e;
                } finally {
                    System.out.println("finally");
                }
            }
            throw exception;
        }

    }

}
