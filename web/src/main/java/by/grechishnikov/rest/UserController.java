package by.grechishnikov.rest;

import by.grechishnikov.dto.UserDTO;
import by.grechishnikov.entity.User;
import by.grechishnikov.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserController {
    private IUserService userService;
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Boolean> add(@RequestBody User user) {
        try {
            logger.warn("NEW USER: " + user);
            return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ERROR!", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        try {
            logger.warn("CHECK USER: " + user);
            return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ERROR!", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
