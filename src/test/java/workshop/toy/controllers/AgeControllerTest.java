package workshop.toy.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.models.Age;
import workshop.toy.repositories.AgeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgeControllerTest {

    @MockBean
    private AgeRepository ageRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success() throws Exception {
        Age a1 = new Age(1, "Test1");
        Age a2 = new Age(2, "Test2");
        List<Age> ages = new ArrayList<>();
        ages.add(a1);
        ages.add(a2);
        given(ageRepository.getAgeList())
                .willReturn(ages);

        ResponseEntity<List> response
                = restTemplate.getForEntity("/rest/age", List.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }
}
