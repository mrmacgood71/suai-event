package it.macgood.suaieventserver.event.model;

import com.fasterxml.jackson.annotation.JsonView;
import it.macgood.suaieventserver.user.model.Student;
import it.macgood.suaieventserver.view.View;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.GetUserInfo.class)
    private Long id;
    @Column(columnDefinition = "TEXT")
    @JsonView(View.GetUserInfo.class)
    private String name;
    @JsonView(View.GetUserInfo.class)
    private String regStartDate;
    @JsonView(View.GetUserInfo.class)
    private String regEndDate;
    @JsonView(View.GetUserInfo.class)
    private String eventStartDate;
    @JsonView(View.GetUserInfo.class)
    private String eventEndDate;
    @Column(columnDefinition = "TEXT")
    @JsonView(View.GetUserInfo.class)
    private String place;
    @Column(columnDefinition = "TEXT")
    @JsonView(View.GetUserInfo.class)
    private String organiser;
    @Column(columnDefinition = "TEXT")
    @JsonView(View.GetUserInfo.class)
    private String tags;
    @Column(columnDefinition = "TEXT")
    @JsonView(View.GetUserInfo.class)
    private String link;
    @Column(columnDefinition = "TEXT")
    @JsonView(View.GetUserInfo.class)
    private String info;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();
}
