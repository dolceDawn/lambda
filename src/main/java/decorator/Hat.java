package decorator;

public class Hat extends DecoratorBase {
    public Hat(IPerson iPerson) {
        super(iPerson);
    }
    @Override
    public void show() {
        // 执行已有功能
        iPerson.show();
        // 定义新行为
        System.out.println("戴上帽子");
    }
}

