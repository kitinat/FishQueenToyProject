package toy.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import toy.models.Age;

import java.util.List;

public interface AgeRepository extends CrudRepository<Age, Integer> {

    @Query("SELECT * FROM AGE ORDER BY ID")
    List<Age> getAgeList();

}
