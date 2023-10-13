package it.macgood.suaieventserver.user.repository;

import it.macgood.suaieventserver.user.model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganiserRepository extends JpaRepository<Organiser, String> {

    List<Organiser> findAll();
    Optional<Organiser> findById(String id);
}
