package aqs;

public class TestThreadLocal {

    private static ThreadLocal T = new ThreadLocal();
    private static ThreadLocal T1 = new InheritableThreadLocal();

    //然后我们将ThreadLocal换成InheritableThreadLocal

    public static void main(String[] args) {
        T.set("23abc");
        System.out.println("main - " + T.get());
        new Thread(() -> System.out.println("sub - " + T.get())).start();
    }
}
