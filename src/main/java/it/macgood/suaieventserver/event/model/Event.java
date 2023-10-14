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
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private Long id;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String name;
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String regStartDate;
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String regEndDate;
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String eventStartDate;
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String eventEndDate;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String place;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String organiser;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String tags;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String link;
    @Column(columnDefinition = "TEXT")
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String info;
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonView({View.GetUserInfo.class, View.GetEventInfo.class})
    private List<Student> students = new ArrayList<>();
}
