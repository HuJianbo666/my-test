package elasticsearch.dao;

import elasticsearch.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Created by hujianbo on 2018/2/6.
 */
public interface CityRepository extends ElasticsearchCrudRepository<City, Long> {


}
