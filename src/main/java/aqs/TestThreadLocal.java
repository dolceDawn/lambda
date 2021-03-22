package aqs;

public class TestThreadLocal {

    private static ThreadLocal T = new ThreadLocal();
    private static ThreadLocal T1 = new InheritableThreadLocal();

    //然后我们将ThreadLocal换成InheritableThreadLocal

    public static void main(String[] args) {
        T1.set("23abc");
        System.out.println("main - " + T1.get());
        new Thread(() -> System.out.println("sub - " + T1.get())).start();
    }
}
