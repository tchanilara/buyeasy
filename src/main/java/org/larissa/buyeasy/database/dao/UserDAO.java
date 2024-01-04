package org.larissa.buyeasy.database.dao;

import jakarta.transaction.Transactional;
import org.larissa.buyeasy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findById(Integer id);
    User findByEmailIgnoreCase(String email);

    @Modifying
    @Transactional
    int deleteByEmailIgnoreCase(String email);
}
