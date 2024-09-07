package by.eugene.library.controllers.impl;

import by.eugene.library.controllers.PeopleController;
import by.eugene.library.model.Person;
import by.eugene.library.services.BookService;
import by.eugene.library.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleControllerImpl implements PeopleController {

    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public PeopleControllerImpl(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @Override
    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people/allPeople";
    }

    @Override
    @GetMapping("/{id}")
    public String getOnePerson(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personService.findByID(id));
        model.addAttribute("books", bookService.getBooksByOwnerId(id));
        return "people/onePerson";
    }

    @Override
    @GetMapping("/new")
    public String getNewFormToCreatePerson(@ModelAttribute("person") Person person) {
        return "people/newFormForPerson";
    }

    @Override
    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/newFormForPerson";
        }

        personService.save(person);
        return "redirect:/people";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personService.findByID(id));
        return "people/editPersonForm";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                               @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "people/editPersonForm";
        }
        personService.update(id, person);
        return "redirect:/people";
    }

    @Override
    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") Integer id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
