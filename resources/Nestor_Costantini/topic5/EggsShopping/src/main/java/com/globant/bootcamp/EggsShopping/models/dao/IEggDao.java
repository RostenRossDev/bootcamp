package com.globant.bootcamp.EggsShopping.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.globant.bootcamp.EggsShopping.models.animals.Egg;

public interface IEggDao extends CrudRepository<Egg, Long>{

}
