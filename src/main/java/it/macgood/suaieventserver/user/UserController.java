package it.macgood.suaieventserver.user;

import com.fasterxml.jackson.annotation.JsonView;
import it.macgood.suaieventserver.user.model.*;
import it.macgood.suaieventserver.view.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/students")
    @JsonView(View.GetUserInfo.class)
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(userService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    @JsonView(View.GetUserInfo.class)
    public ResponseEntity<Student> getStudentById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.getStudentById(id));
    }

    @GetMapping("/student/{id}/addAchievement")
    public ResponseEntity<ResponseAchievement> addAchievement(
            @PathVariable String id,
            @RequestBody Achievement achievement
    ) {
        return ResponseEntity.ok(userService.addAchievement(achievement, id));
    }

    @GetMapping("/student/{id}/achievement")
    public ResponseEntity<List<ResponseAchievement>> getAchievementByStudent(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.getAchievementsByStudentId(id));
    }


    @PostMapping("/addAdmin")
    public ResponseEntity<Administrator> addAdministrator(
            @RequestBody Administrator administrator
    ) {
        return ResponseEntity.ok(userService.addAdministrator(administrator));
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addAdministrator(
            @RequestBody Student student
    ) {
        return ResponseEntity.ok(userService.addStudent(student));
    }

    @PostMapping("/addOrganiser")
    public ResponseEntity<Organiser> addAdministrator(
            @RequestBody Organiser organiser
    ) {
        return ResponseEntity.ok(userService.addOrganiser(organiser));
    }


}
