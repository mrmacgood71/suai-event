package it.macgood.suaieventserver.user.model;

import com.fasterxml.jackson.annotation.JsonView;
import it.macgood.suaieventserver.event.model.Contest;
import it.macgood.suaieventserver.event.model.Event;
import it.macgood.suaieventserver.view.View;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @JsonView(View.GetUserInfo.class)
    private String id;
    @JsonView(View.GetUserInfo.class)
    private String identificationNumber;
    @JsonView(View.GetUserInfo.class)
    private String firstname;
    @JsonView(View.GetUserInfo.class)
    private String lastname;
    @JsonView(View.GetUserInfo.class)
    private String groupNumber;
    @JsonView(View.GetUserInfo.class)
    private String patronymic;

    @ManyToMany(mappedBy = "students")
    @JsonView(View.GetUserInfo.class)
    private List<Event> events = new ArrayList<>();

    @ManyToMany(mappedBy = "students")
    @JsonView(View.GetUserInfo.class)
    private List<Contest> contests = new ArrayList<>();

}
