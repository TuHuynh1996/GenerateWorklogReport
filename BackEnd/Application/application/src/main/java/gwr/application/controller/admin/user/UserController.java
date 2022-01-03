package gwr.application.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gwr.application.model.user.UserRequest;
import gwr.application.service.admin.user.UsersService;
import lombok.var;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    
    @Autowired
    private UsersService userService;

    @GetMapping
    public ResponseEntity<?> getUser(UserRequest request, Pageable pageable) {
        var result = userService.getUsers(request, pageable);
        return ResponseEntity.ok().body(result);
    }
    
    
    
}
