package com.ratiose.testtask.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.ratiose.testtask.entity.Actor;
import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.entity.UserActors;
import com.ratiose.testtask.exception.RequestException;
import com.ratiose.testtask.service.UserActorsService;
import com.ratiose.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserActorsService userActorsService;

    @RequestMapping(value = "/add", method = POST)
    public ResponseEntity addFavoriteActor(@RequestParam String email,
                                           @RequestParam String password,
                                           @RequestParam String actorFirstName,
                                           @RequestParam String actorLastName) {
        try {
            User user = userService.findUser(email, password);
            checkInputActor(actorFirstName, actorLastName);
            Actor actor = userActorsService.addFavoriteActor(user.getId(), actorFirstName, actorLastName);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(actor);
        } catch (RequestException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/favorite", method = GET)
    public ResponseEntity getFavoriteActors(@RequestParam String email,
                                            @RequestParam String password) {
        try {
            User user = userService.findUser(email, password);
            UserActors userActors = userActorsService.getUserFavoriteActors(user.getId());
            return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).body(userActors.getActors());
        } catch (RequestException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/remove", method = DELETE)
    public ResponseEntity deleteActorFromFavorites(@RequestParam String email,
                                                   @RequestParam String password,
                                                   @RequestParam long actorId) {
        try {
            User user = userService.findUser(email, password);
            userActorsService.removeActorFromFavorites(user.getId(), actorId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RequestException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }
    }

    private void checkInputActor(String actorFirstName, String actorLastName) {
        if (StringUtils.isEmpty(actorFirstName) ||
            StringUtils.isEmpty(actorLastName)) {
            throw new RequestException(HttpStatus.BAD_REQUEST,
                "You should provide actor with filled first and last names");
        }
    }

}
