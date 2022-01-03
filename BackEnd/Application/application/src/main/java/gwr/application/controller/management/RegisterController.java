package gwr.application.controller.management;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gwr.application.model.register.RegisterRequest;
import gwr.application.service.admin.user.UsersService;
import gwr.library.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/management/register")
public class RegisterController {

    @Autowired
    private UsersService userService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) throws ResourceNotFoundException {
        userService.register(request);
        return ResponseEntity.ok().body("Ok");
    }
}
