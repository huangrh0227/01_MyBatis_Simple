package com.immoc.dao;

import com.immoc.domain.Role;

import java.util.List;

public interface RoleMapper {
    int addRole(Role role);
    Role findById(int id);
    int deleteRole(int id);
    int updateRole(Role role);
    List<Role> findByRoleName(String roleName);
    int findTotal();
    List<Role> findByUserIds(List<Integer> ids);
}
