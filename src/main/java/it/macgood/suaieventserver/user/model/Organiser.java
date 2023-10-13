package it.macgood.suaieventserver.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Organiser {
    @Id
    private String id;
    private String companyName;
    private String firstname;
    private String lastname;
    private String patronymic;
}
