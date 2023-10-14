package it.macgood.suaieventserver.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAchievement {
    private Long id;

    private String name;

    private String link;

    private String status;

    private String result;

    private String studentId;

    private String studentFirstname;

    private String studentLastname;

    private String studentPatronymic;

    private String studentGroupName;

    private String studentIdentity;



}
