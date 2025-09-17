package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import app.model.User;
import app.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {  // Показать всех пользователей
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @GetMapping("/users/add")
    public String addUserForm(Model model) {   //форма для добавления нового пользователя
        model.addAttribute("user", new User());
        return "addUser";
    }


    @PostMapping("/users/add")
    public String addUser(@RequestParam String name, @RequestParam String email) {     //  добавления пользователя
        User user = new User(name, email);
        userService.saveUser(user);
        return "redirect:/users";
    }


    @GetMapping("/users/edit")
    public String editUserForm(@RequestParam Long id, Model model) {   //редактирования пользователя
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUsers";
    }

    // Обработка обновления пользователя
    @PostMapping("/users/edit")
    public String updateUser(@RequestParam Long id,        // Обработка обновления
                             @RequestParam String name,
                             @RequestParam String email) {
        User user = new User(name, email);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }


    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) { // delete
        userService.deleteUser(id);
        return "redirect:/users";
    }
}