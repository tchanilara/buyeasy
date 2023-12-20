package org.larissa.buyeasy.database.dao;

import org.larissa.buyeasy.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
    public List<UserRole> findByUserId(Integer userId);
}
