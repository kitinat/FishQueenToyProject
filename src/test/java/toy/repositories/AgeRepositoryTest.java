package toy.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import toy.models.Age;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class AgeRepositoryTest {

    @Autowired
    private AgeRepository ageRepository;

    @Test
    public void listShouldBe5(){
        List<Age> ageList = ageRepository.getAgeList();
        assertEquals(ageList.size(),5);
    }
}
