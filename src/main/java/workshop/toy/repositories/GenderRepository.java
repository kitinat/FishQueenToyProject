package workshop.toy.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import workshop.toy.models.Gender;

import java.util.List;

public interface GenderRepository extends CrudRepository<Gender, Integer> {

    @Query("SELECT * FROM GENDER ORDER BY ID")
    List<Gender> getGenderList();

}
