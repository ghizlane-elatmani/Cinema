package io.javacode.cinema.dao;

import io.javacode.cinema.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long>  {
}
