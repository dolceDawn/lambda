package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import static sun.misc.VM.getState;

public class CustomAqs {

    public static void main(String[] args) {

    }


    private static class Syn extends AbstractQueuedSynchronizer {

        boolean acq() {
            return tryAcquire(1);
        }

        boolean rel() {
            return tryRelease(1);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            Thread thread = Thread.currentThread();
            Thread owner = getExclusiveOwnerThread();
            if (thread != owner) {
                return false;
            } else {
                int state = getState();
                if (state - arg == 0) {
                    setState(0);
                    return true;
                }
            }
            return false;
        }
    }

}
