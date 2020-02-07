package com.immoc.dao;

import com.immoc.domain.User;

import java.util.List;

public interface UserMapper {
    User findByUserId(Integer userId);
}
