package proxy_mapper;

import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {

        Connector connector = new Connector();

        Subject subject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),new Class[]{Subject.class},
                new ProxyHandler(connector));

        System.out.println(subject.request());
    }

}
