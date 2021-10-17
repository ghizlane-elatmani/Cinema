package io.javacode.cinema.web;

import io.javacode.cinema.dao.FilmRepository;
import io.javacode.cinema.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CinemaRestController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/listFilms")
    public List<Film> films(){
        return filmRepository.findAll();
    }

}
