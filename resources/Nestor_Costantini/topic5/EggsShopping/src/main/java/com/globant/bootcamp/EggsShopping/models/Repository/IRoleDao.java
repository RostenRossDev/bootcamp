package com.globant.bootcamp.EggsShopping.models.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.bootcamp.EggsShopping.models.entity.Role;

@Repository
public interface IRoleDao extends CrudRepository<Role, Long>{


}
