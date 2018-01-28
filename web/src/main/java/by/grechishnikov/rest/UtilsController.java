package by.grechishnikov.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ResourceBundle;

/**
 * Rest controller for util functions
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class UtilsController {
    private static int defaultCountPerPage =
            Integer.parseInt(ResourceBundle.getBundle("config").getString("default.count.per.page"));
    private static Logger logger = Logger.getLogger(UtilsController.class);


    /**
     * Get records count per voting list page
     *
     * @return - count
     */
    @RequestMapping(value = "/voting/settings", method = RequestMethod.GET)
    public ResponseEntity<Integer> getRecordsCountPerPage() {
        try {
            logger.warn("GET RECORD COUNT");
            return new ResponseEntity<>(defaultCountPerPage, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("GET RECORDS COUNT EXCEPTION.", e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
