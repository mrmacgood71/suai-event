package it.macgood.suaieventserver.user.repository;

import it.macgood.suaieventserver.user.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {

    List<Administrator> findAll();
    Optional<Administrator> findById(String id);
}
