package com.wizard.mybatis.session;

import com.wizard.mybatis.session.impl.SqlSessionFactoryImpl;
import com.wizard.mybatis.util.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) {

        return new SqlSessionFactoryImpl(XMLConfigBuilder.loadConfiguration(in));
    }
}
