package it.macgood.suaieventserver.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
public class SuaiEvent {
    @Id
    @JsonProperty("ItemId")
    private Integer itemId;
    @JsonProperty("DateBegin")
    private String dateBegin;
    @JsonProperty("DateEnd")
    private String dateEnd;
    @JsonProperty("DateDeadline")
    private String dateDeadline;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("TitleInt")
    private String titleInt;
    @JsonProperty("ShortTitle")
    private String shortTitle;
    @JsonProperty("Place")
    private String place;
    @JsonProperty("SiteLink")
    private String siteLink;
    @JsonProperty("Tags")
    private String tags;
    @JsonProperty("Orgs")
    private String organiser;
    @JsonProperty("Info")
    private String info;
}
