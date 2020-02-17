package com.wizard.mybatis.util;

import com.wizard.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultType();
            Class domainClass = Class.forName(resultType);
            pstm = conn.prepareStatement(queryString);
            rs = pstm.executeQuery();
            List<E> list = new ArrayList<E>();
            while (rs.next()) {
                E obj = (E) domainClass.newInstance();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);
                    System.out.println(columnValue.getClass());
                    if (columnName != null && !"".equals(columnName.trim())) {
                        Field[] declaredFields = domainClass.getDeclaredFields();
                        for (Field f:declaredFields
                             ) {
                            if (columnName.equalsIgnoreCase(f.getName())){
                                columnName = f.getName();
                            }
                        }
                    }
                    PropertyDescriptor pd = new PropertyDescriptor(columnName, domainClass);

                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(obj, columnValue);
                }
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(pstm, rs);
        }
        return null;
    }

    private void release(PreparedStatement pstm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
