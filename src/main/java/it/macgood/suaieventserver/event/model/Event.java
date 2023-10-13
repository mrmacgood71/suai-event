package it.macgood.suaieventserver.event.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String regStartDate;
    private String regEndDate;
    private String eventStartDate;
    private String eventEndDate;
    private String place;
    private String organiser;
    private String tags;
    private String link;
    private String info;
}
