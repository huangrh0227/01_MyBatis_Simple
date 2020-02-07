package com.immoc.dao;

import com.immoc.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RoleMapperTest {

    SqlSession sqlSession;
    RoleMapper roleMapper;

    @Before
    public void init() throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        sqlSession = sqlSessionFactory.openSession();
        roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    @Test
    public void testAddRole() {
        Role role = new Role();
        role.setRoleName("学习委员");
        role.setRoleDesc("负责收作业");
        int result = roleMapper.addRole(role);
        System.out.println(role);
    }

    @Test
    public void testFindById() {
        Role role = roleMapper.findById(4);
        System.out.println(role);
    }

    @Test
    public void testDeleteRole() {
        roleMapper.deleteRole(12);
    }

    @Test
    public void testUpdateRole() {
        Role role = new Role();
        role.setId(10);
        role.setRoleName("生活委员");
        role.setRoleDesc("管理卫生的");
        int result = roleMapper.updateRole(role);
        System.out.println(result);
    }

    @Test
    public void testFindByRoleName() {
        List<Role> roles = roleMapper.findByRoleName("%委员%");
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void testFindTotal() {
        int total = roleMapper.findTotal();
        System.out.println(total);
    }

    @Test
    public void testFindByUserIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(3);
        List<Role> roles = roleMapper.findByUserIds(ids);
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @After
    public void destroy() {
        sqlSession.commit();
        sqlSession.close();
    }
}
