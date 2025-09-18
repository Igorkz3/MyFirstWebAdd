package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Редирект с корня на список пользователей
    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    // Отображение списка пользователей
    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    // Форма добавления нового пользователя
    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    // Обработка добавления нового пользователя
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "addUser";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    // Форма редактирования пользователя
    @GetMapping("/users/edit")
    public String editUserForm(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUsers";
    }

    // Обработка обновления пользователя
    @PostMapping("/users/edit")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "updateUsers";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    // Удаление пользователя
    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}