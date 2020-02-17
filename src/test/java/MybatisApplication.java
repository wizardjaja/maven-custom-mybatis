import com.wizard.io.Resources;
import com.wizard.mapper.IUserMapper;
import com.wizard.mybatis.session.SqlSession;
import com.wizard.mybatis.session.SqlSessionFactory;
import com.wizard.mybatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.InputStream;


public class MybatisApplication {
    @Test
    public void userFindById(){
        //设置配置文件名
        String resources = "config/mybatis-config.xml";
        // 读取配合文件到reader对象中
        InputStream in = Resources.getResourceAsStream(resources);
        //创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(in);
        //创建session实例
        SqlSession session=sqlMapper.openSession();
        //传入参数查询，返回结果
        // findById  与 UserInfoMapper 中select标签设置的ID一致，表示执行哪个sql语句
        IUserMapper userMapper = session.getMapper(IUserMapper.class);
        //输出结果
        userMapper.getAllUsers().forEach(System.out::println);
        //关闭session
        session.close();
    }

    @Test
    public void testIsID() throws IntrospectionException {
        PropertyDescriptor pd = new PropertyDescriptor("Id", com.wizard.model.User.class);
    }
}
