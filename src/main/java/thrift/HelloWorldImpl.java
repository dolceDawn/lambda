package thrift;

public class HelloWorldImpl implements  HelloWorldService.Iface {
    public  HelloWorldImpl(){
    }
    @Override
    public  String sayHello(String username) throws MyException{
        if (true) {
            MyException me = new MyException();
            me.setMsg("dolce error!");
            throw me;
        }
        return "hi " + username +"welcome to thrift world";
    }
}