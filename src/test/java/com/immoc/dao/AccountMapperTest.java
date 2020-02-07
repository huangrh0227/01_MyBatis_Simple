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
import java.util.List;

public class AccountMapperTest {
    SqlSession sqlSession;
    AccountMapper accountMapper;

    @Before
    public void init() throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        sqlSession = sqlSessionFactory.openSession();
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @Test
    public void testFindAccountById() {
        Account account = accountMapper.findAccountById(1);
        System.out.println(account.getMoney());
        // User user = account.getUser();
        // System.out.println(user.getUserName());
    }

    @Test
    public void testFindAccountsByUserId() {
        List<Account> accounts = accountMapper.findAccountsByUserId(41);
        for (Account account : accounts) {
            System.out.println(account.getId() + "-->" + account.getMoney());
        }
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }
}
