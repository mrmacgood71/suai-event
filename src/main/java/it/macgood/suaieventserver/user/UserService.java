package it.macgood.suaieventserver.user;

import it.macgood.suaieventserver.user.model.Administrator;
import it.macgood.suaieventserver.user.model.Organiser;
import it.macgood.suaieventserver.user.model.Student;
import it.macgood.suaieventserver.user.repository.AdministratorRepository;
import it.macgood.suaieventserver.user.repository.OrganiserRepository;
import it.macgood.suaieventserver.user.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final AdministratorRepository administratorRepository;
    private final OrganiserRepository organiserRepository;
    private final StudentRepository studentRepository;

    public Student addStudent(Student student) {
        Student saved = studentRepository.save(student);
        return saved;
    }

    public Administrator addAdministrator(Administrator administrator) {
        Administrator saved = administratorRepository.save(administrator);
        return saved;
    }
    public Organiser addOrganiser(Organiser organiser) {
        Organiser saved = organiserRepository.save(organiser);
        return saved;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).get();
    }
}
