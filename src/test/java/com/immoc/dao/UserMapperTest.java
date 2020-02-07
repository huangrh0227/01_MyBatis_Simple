package com.immoc.dao;

import com.immoc.domain.Account;
import com.immoc.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class UserMapperTest {
    SqlSession sqlSession;
    UserMapper userMapper;
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(in);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testFindByUserId() {
        User user = userMapper.findByUserId(41);
        System.out.println(user.getUserName());
        for (Account account : user.getAccounts()) {
            System.out.println(account.getId()+"-->"+account.getMoney());
        }
    }

    @Test
    public void testCache() {
        User user = userMapper.findByUserId(41);
        System.out.println(user.getUserName());
        sqlSession.clearCache();
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        user = userMapper.findByUserId(41);
        System.out.println(user.getUserName());
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }
}
