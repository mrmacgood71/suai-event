package it.macgood.suaieventserver.user.repository;

import it.macgood.suaieventserver.user.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findAll();
    Optional<Student> findById(String id);
}
