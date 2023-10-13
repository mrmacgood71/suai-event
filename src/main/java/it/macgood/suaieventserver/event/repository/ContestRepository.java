package it.macgood.suaieventserver.event.repository;

import it.macgood.suaieventserver.event.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
}
