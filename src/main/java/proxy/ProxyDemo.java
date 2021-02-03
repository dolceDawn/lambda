package proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        ProxyHandler handler = new ProxyHandler(realSubject);

        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);

        proxySubject.request();
    }

}
