package com.itheima;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test
    //查询操作
    public void findAll() throws IOException {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlsession对象 openSession()不带参数就是默认需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);
      /*  SqlSession sqlSession1 = sqlSessionFactory.openSession();
        System.out.println("sqlSession1 = " + sqlSession1);*/
        //执行sql语句
        List<Object> selectList = sqlSession.selectList("userMapper.findAll");
        System.out.println(selectList);
        sqlSession.close();
        //sqlSession1.close();
    }

    @Test
    public void  save() throws IOException {
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("userMapper.save",user);
        //增删改操作需要提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void delete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //openSession(true) 方法参数传入true表示默认自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        sqlSession.delete("userMapper.delete",1);
        sqlSession.close();


    }
    @Test
    public void test() throws IOException {
        UserDao userDao = new UserDaoImpl();
        List<User> all = userDao.findAll();
        System.out.println(all);
    }
    @Test
    public void test1() {
        System.out.println("hello git");
    }
}
