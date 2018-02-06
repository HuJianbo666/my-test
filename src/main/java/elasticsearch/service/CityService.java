package elasticsearch.service;

import elasticsearch.domain.City;

/**
 * Created by hujianbo on 2018/2/6.
 */
public interface CityService {
    Long saveCity(City city);
}
