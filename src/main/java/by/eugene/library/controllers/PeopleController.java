package by.eugene.library.controllers;

import by.eugene.library.model.Person;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public interface PeopleController {
    String getAllPeople(Model model);

    String getOnePerson(Integer id, Model model);
    String getNewFormToCreatePerson(@ModelAttribute("person") Person person);

    String createNewPerson(@ModelAttribute("person") Person person, BindingResult bindingResult);
    String editPerson(Integer id, Model model);

    String updatePerson(@ModelAttribute("person") Person person, BindingResult bindingResult, Integer id);
    String deletePerson(Integer id);
}
