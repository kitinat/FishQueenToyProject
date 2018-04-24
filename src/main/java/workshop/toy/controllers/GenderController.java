package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.models.Gender;
import workshop.toy.repositories.GenderRepository;

import java.util.List;

@RestController
public class GenderController {

    @Autowired
    private GenderRepository genderRepository;

    @GetMapping("/gender")
    public List<Gender> list() {
        return genderRepository.getGenderList();
    }

}
