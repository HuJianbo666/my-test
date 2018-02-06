package elasticsearch.service.impl;

import elasticsearch.dao.CityRepository;
import elasticsearch.domain.City;
import elasticsearch.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hujianbo on 2018/2/6.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }
}
