package application.controller;

import application.entity.User;
import application.exception.NoDataFoundException;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/findAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow(() -> new NoDataFoundException("User does not exist")));
        return "users/findById";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/save";
        userService.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/update";
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String saveView(Model model) {
        model.addAttribute(new User());
        return "users/save";
    }

    @GetMapping("/{id}/update")
    public String updateView(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id).orElseThrow(() -> new NoDataFoundException("User does not exist")));
        return "users/update";
    }
}
