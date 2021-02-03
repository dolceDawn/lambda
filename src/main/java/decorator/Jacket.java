package decorator;

public class Jacket extends DecoratorBase {
    public Jacket(IPerson iPerson) {
        super(iPerson);
    }
    @Override
    public void show() {
        // 执行已有功能
        iPerson.show();
        // 定义新行为
        System.out.println("穿上夹克");
    }
}

