package com.egova.oauth.repository;

import com.egova.oauth.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hbche on 2017/7/21.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
