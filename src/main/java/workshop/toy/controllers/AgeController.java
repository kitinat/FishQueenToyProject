package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.models.Age;
import workshop.toy.repositories.AgeRepository;

import java.util.List;

@RestController
public class AgeController {

    @Autowired
    private AgeRepository ageRepository;

    @GetMapping("/age")
    public List<Age> list() {
        return ageRepository.getAgeList();
    }

}
