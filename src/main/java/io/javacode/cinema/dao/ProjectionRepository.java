package io.javacode.cinema.dao;

import io.javacode.cinema.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
