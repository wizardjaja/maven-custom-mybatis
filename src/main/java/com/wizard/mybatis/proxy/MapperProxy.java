package com.wizard.mybatis.proxy;

import com.wizard.mybatis.cfg.Mapper;
import com.wizard.mybatis.util.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        String classname = method.getDeclaringClass().getName();
        Mapper mapper = mappers.get(classname + "." + name);
        if (mapper == null){
            return null;
        }
        return new Executor().selectList(mapper, conn);
    }
}
