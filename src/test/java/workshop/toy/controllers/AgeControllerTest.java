package workshop.toy.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.repositories.AgeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgeControllerTest {

    @MockBean
    private AgeRepository ageRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success() throws Exception {

    }
}
