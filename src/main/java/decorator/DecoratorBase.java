package decorator;

public class DecoratorBase implements IPerson{
    IPerson iPerson;
    public DecoratorBase(IPerson iPerson){
        this.iPerson = iPerson;
    }
    @Override
    public void show() {
        iPerson.show();
    }
}

