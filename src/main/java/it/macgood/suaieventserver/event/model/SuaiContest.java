package it.macgood.suaieventserver.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class SuaiContest {
    @Id
    @JsonProperty("ItemId")
    private Integer itemId;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("DateAnnounce")
    private String dataAnnounce;
    @JsonProperty("DateDeadline")
    private String dateDeadline;
    @JsonProperty("DateOff")
    private String dateOff;
    @JsonProperty("Period")
    private String period;
    @JsonProperty("Value")
    private String value;
    @JsonProperty("DocLink")
    private String docLink;
    @JsonProperty("SiteLink")
    private String siteLink;
    @JsonProperty("Summary")
    private String summary;
    @JsonProperty("Info")
    private String info;
    @JsonProperty("OldOrgPtr")
    private Integer oldOrgPtr;

}
