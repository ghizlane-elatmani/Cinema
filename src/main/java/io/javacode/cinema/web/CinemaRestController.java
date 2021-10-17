package io.javacode.cinema.web;

import io.javacode.cinema.dao.FilmRepository;
import io.javacode.cinema.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class CinemaRestController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film film = filmRepository.findById(id).get();
        String photoName = film.getPhoto();

        /*
        String home = System.getProperty("user.home");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> #home: " + home);
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
        Path path = Paths.get(file.toURI());
        */

        // TODO do path as external source in application properties
        Path path = Paths.get(System.getProperty("user.home"), "cinema", "images", photoName);
        return Files.readAllBytes(path);
    }

}
