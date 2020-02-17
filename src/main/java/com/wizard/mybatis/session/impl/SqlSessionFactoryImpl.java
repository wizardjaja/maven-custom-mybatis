package com.wizard.mybatis.session.impl;

import com.wizard.mybatis.cfg.Configuration;
import com.wizard.mybatis.session.SqlSession;
import com.wizard.mybatis.session.SqlSessionFactory;

import java.io.InputStream;

public class SqlSessionFactoryImpl implements SqlSessionFactory {

    private Configuration config;
    public SqlSessionFactoryImpl(Configuration config) {
        this.config = config;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(config);
    }
}
