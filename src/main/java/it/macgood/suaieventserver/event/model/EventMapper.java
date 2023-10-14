package it.macgood.suaieventserver.event.model;

public class EventMapper {
    public static Event toEvent(SuaiEvent suaiEvent) {

        return Event.builder()
                .name(suaiEvent.getTitle())
                .regStartDate(suaiEvent.getDateBegin())
                .regEndDate(suaiEvent.getDateDeadline())
                .eventStartDate(suaiEvent.getDateBegin())
                .eventEndDate(suaiEvent.getDateEnd())
                .place(suaiEvent.getPlace())
                .organiser(suaiEvent.getOrganiser())
                .tags(suaiEvent.getTags())
                .link(suaiEvent.getSiteLink())
                .build();

    }

    public static Contest toContest(SuaiContest suaiContest) {
        return Contest.builder()
                .name(suaiContest.getTitle())
                .regStartDate(suaiContest.getDataAnnounce())
                .regEndDate(suaiContest.getDateDeadline())
                .eventStartDate(suaiContest.getDataAnnounce())
                .eventEndDate(suaiContest.getDateOff())
                .place("")
                .value(suaiContest.getValue())
                .period(suaiContest.getPeriod())
                .link(suaiContest.getSiteLink())
                .build();
    }
}
