package com.hendisantika.usermanagement.repository;

import com.hendisantika.usermanagement.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String role);
}