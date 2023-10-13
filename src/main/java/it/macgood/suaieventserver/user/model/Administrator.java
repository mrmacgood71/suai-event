package it.macgood.suaieventserver.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Administrator {

    @Id
    private String id;
    private String identificationNumber;
    private String firstname;
    private String lastname;
    private String patronymic;

}
