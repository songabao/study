package com.microservice.dao;

import com.microservice.domain.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(City record);

    City selectByPrimaryKey(Long id);

    List<City> selectAll();

    int updateByPrimaryKey(City record);
}