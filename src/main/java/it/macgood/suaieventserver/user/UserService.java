package it.macgood.suaieventserver.user;

import it.macgood.suaieventserver.user.model.*;
import it.macgood.suaieventserver.user.repository.AchievementRepository;
import it.macgood.suaieventserver.user.repository.AdministratorRepository;
import it.macgood.suaieventserver.user.repository.OrganiserRepository;
import it.macgood.suaieventserver.user.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final AdministratorRepository administratorRepository;
    private final OrganiserRepository organiserRepository;
    private final StudentRepository studentRepository;
    private final AchievementRepository achievementRepository;

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

    public Administrator getAdminById(String id) {
        return administratorRepository.findById(id).get();
    }


    public ResponseAchievement addAchievement(Achievement achievement, String id) {
        Student student = studentRepository.findById(id).get();
        achievement.setStudentId(student.getId());
        Achievement save = achievementRepository.save(achievement);
        var response = ResponseAchievement.builder()
                .id(save.getId())
                .link(save.getLink())
                .status(save.getStatus())
                .name(save.getName())
                .result(save.getResult())
                .studentFirstname(student.getFirstname())
                .studentIdentity(student.getIdentificationNumber())
                .studentGroupName(student.getGroupNumber())
                .studentPatronymic(student.getPatronymic())
                .studentLastname(student.getLastname())
                .build();
        return response;
    }

    public List<ResponseAchievement> getAchievementsByStudentId(String id) {
        List<Achievement> all = achievementRepository.findAll();
        all = all.stream().filter(achievement -> achievement.getStudentId().equals(id)).toList();
        return all.stream().map(achievement -> {
                    Student student = studentRepository.findById(achievement.getStudentId()).get();

                    return ResponseAchievement.builder()
                            .id(achievement.getId())
                            .name(achievement.getName())
                            .result(achievement.getResult())
                            .link(achievement.getLink())
                            .status(achievement.getStatus())
                            .studentId(student.getId())
                            .studentFirstname(student.getFirstname())
                            .studentIdentity(student.getIdentificationNumber())
                            .studentLastname(student.getLastname())
                            .studentGroupName(student.getGroupNumber())
                            .studentPatronymic(student.getPatronymic())
                            .build();
                }
        ).toList();
    }
}
