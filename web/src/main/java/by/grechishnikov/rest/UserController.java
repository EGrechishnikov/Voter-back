package by.grechishnikov.rest;

import by.grechishnikov.dto.UserDTO;
import by.grechishnikov.entity.User;
import by.grechishnikov.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for users entity
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserController {
    private IUserService userService;
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Add a new user to DB
     *
     * @param user - user
     * @return - true if all right
     */
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

    /**
     * Do login
     *
     * @param user - user
     * @return - DTO with users data
     */
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
