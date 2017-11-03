package by.grechishnikov.rest;

import by.grechishnikov.entity.User;
import by.grechishnikov.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private IUserService userService;
    private Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    public MainController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody User user) {
        try {
            logger.warn("NEW USER: " + user);
            userService.saveOrUpdate(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ERROR!", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable int id) {
        try {
            logger.warn("GET: " + id);
            User user = userService.get(id);
            logger.warn("USER: " + user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ERROR!", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
