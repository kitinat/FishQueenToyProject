package workshop.toy.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.models.Gender;
import workshop.toy.repositories.GenderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GenderControllerTest {

    @MockBean
    private GenderRepository genderRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success() throws Exception {
        List<Gender> gender = new ArrayList<>();
        gender.add(new Gender(1, "Test1"));
        gender.add(new Gender(2, "Test2"));
        given(genderRepository.getGenderList())
                .willReturn(gender);

        ResponseEntity<List> response
                = restTemplate.getForEntity("/rest/gender", List.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }
}
