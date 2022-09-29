package com.vuongkma.luci.routes;

import com.vuongkma.luci.dto.OnCreateUser;
import com.vuongkma.luci.dto.OnDeleteUser;
import com.vuongkma.luci.dto.UserDTO;
import com.vuongkma.luci.entities.UserEntity;
import com.vuongkma.luci.helpers.APIHelper;
import com.vuongkma.luci.helpers.ResponseFormat;
import com.vuongkma.luci.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = APIHelper.restRoot + "users")
@Validated
@Component
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        var users = this.userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseFormat.build(users, "Congrats, all users have been fetched successfully.")
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> findUser(@PathVariable("id") Long userId) {
        var user = this.userService.findUser(userId);
        return ResponseEntity.ok(ResponseFormat.build(
                user,
                "Congrats, the user information has been fetched successfully."
        ));

    }

    @Validated(OnDeleteUser.class)
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long userId) {
        try {
            var status = this.userService.deleteUser(userId);
            if (!status) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("We could not delete this user");
            }

            return ResponseEntity.ok(
                    ResponseFormat.build(userId, "The user has been deleted successfully.")
            );
        } catch (IllegalStateException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserDTO userDTO
    ) {
        System.out.println(userDTO.getUsername());
        UserEntity userEntity = this.modelMapper.map(userDTO, UserEntity.class);

        var user = this.userService.update(id, userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseFormat.build(
                user,
                "Congrats, the user has been updated with the id " + user.getId() + "  successfully."
        ));
    }

    @Validated(OnCreateUser.class)
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserDTO userDTO) {
        UserEntity userEntity = this.modelMapper.map(userDTO, UserEntity.class);
        var user = this.userService.insert(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseFormat.build(
                        user.getId(),
                        "Congrats, the user has been created with the id " + user.getId() + "  successfully."
                )
        );
    }
}
