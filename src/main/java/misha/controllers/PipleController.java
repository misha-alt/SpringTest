package misha.controllers;


import misha.dao.PersonDAO;
import misha.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/piple")
public class PipleController {


    private final PersonDAO personDAO;
    @Autowired
    public PipleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index (Model model){
        model.addAttribute("piple",personDAO.index());
        return "piple/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.shpw(id));
        return "piple/show";
    }

    @GetMapping("/new")
    public String newPerson (Model model){
        model.addAttribute("person", new Person());
        return "piple/new";
    }
    @PostMapping()
    public String create (@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/piple";

    }
    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.shpw(id));
        return "piple/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")Person person, @PathVariable("id") int id){
        personDAO.update(id,person);
        return "redirect:/piple";
    }
}
