package io.javacode.cinema.web;

import io.javacode.cinema.dao.FilmRepository;
import io.javacode.cinema.dao.TicketRepository;
import io.javacode.cinema.entities.Film;
import io.javacode.cinema.entities.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

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

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> ticketList = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNameClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePaiement(ticketForm.getCodePaiement());
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }

}

@Data
class TicketForm {

    private String nomClient;
    private int codePaiement;
    private List<Long> tickets = new ArrayList<>();

}
