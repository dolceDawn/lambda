package thread;

import treeflatten.TreeNode;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class Call {

    private final FutureTask<TreeNode> future = new FutureTask<>(new Callable<TreeNode>() {
        @Override
        public TreeNode call() throws Exception {
            return null;
        }
    });

    Semaphore sm = new Semaphore(2);



}
