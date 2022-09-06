package web.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.spring_boot.model.User;
import web.spring_boot.service.UserService;

@Controller
public class UserController {
    private UserService userservice;

    @Autowired
    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping("/")
    public String home() {

        return "redirect:users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {

        model.addAttribute("users", userservice.listUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping("/input")
    public String inputUser(@ModelAttribute("user") User user) {

        userservice.addUser(user);
        return "redirect:users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("editable_user", userservice.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("editable_user") User user, @PathVariable("id") Long id) {

        userservice.editUser(id, user);
        return "redirect:users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {

        userservice.deleteUser(id);
        return "redirect:users";
    }
}

