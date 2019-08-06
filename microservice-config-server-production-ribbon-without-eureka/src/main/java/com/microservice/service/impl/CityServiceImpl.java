package com.microservice.service.impl;

import com.microservice.dao.CityMapper;
import com.microservice.domain.City;
import com.microservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author songabao
 * @date 2019/6/18 15:52
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public City getCity(Long id) {
        return cityMapper.selectByPrimaryKey(id);
    }
}
