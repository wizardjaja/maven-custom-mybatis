package com.wizard.mybatis.session.impl;



import com.wizard.mybatis.cfg.Configuration;
import com.wizard.mybatis.cfg.Mapper;
import com.wizard.mybatis.proxy.MapperProxy;
import com.wizard.mybatis.session.SqlSession;
import com.wizard.mybatis.util.DataSourceUtils;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {
    private Configuration config;
    private Map<String, Mapper> mappers;
    private Connection conn;
    public DefaultSqlSession(Configuration config) {
        this.config = config;
        conn = DataSourceUtils.getConnection(config);
    }


    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        T t = (T)Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, new MapperProxy(config.getMappers(), conn));
        return t;
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
