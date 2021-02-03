package proxy_mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class ProxyHandler implements InvocationHandler {

    private Connector connector;

    public ProxyHandler(Connector connector){
        this.connector = connector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return connector.selectOne();
    }
}
