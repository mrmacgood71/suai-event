package it.macgood.suaieventserver.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import it.macgood.suaieventserver.event.model.*;
import it.macgood.suaieventserver.event.repository.ContestRepository;
import it.macgood.suaieventserver.event.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final ContestRepository contestRepository;
    private final EventRepository eventRepository;
//    private final SuaiConferencesRepository suaiConferencesRepository;
//    private final SuaiEventRepository suaiEventRepository;


    public List<Contest> getLatestContests() throws IOException {
        List<SuaiContest> suaiContests = getSuaiContests();

        List<Contest> contests;
        try {
            contests = suaiContests
                    .stream()
                    .filter(contest -> {
                        String s = contest.getDateOff().split("-")[0];
                        String s1 = contest.getDateOff().split("-")[1];
                        if (Integer.parseInt(s) > 2023) {
                            return true;
                        } else if (Integer.parseInt(s) == 2023) {
                            return Integer.parseInt(s) >= 10;
                        } else {
                            return false;
                        }

                    })
                    .map(EventMapper::toContest)
                    .toList();
        } catch (Exception e) {
            contests = new ArrayList<>();
        }

        return contests;
    }

    public List<Event> getAllEvents() throws IOException {
        List<SuaiEvent> suaiContests = getSuaiConferences();

        List<Event> contests;
        try {
            contests = suaiContests
                    .stream()
                    .filter(contest -> {
                        String s = contest.getDateDeadline().split("-")[0];
                        String s1 = contest.getDateDeadline().split("-")[1];
                        if (Integer.parseInt(s) > 2023) {
                            return true;
                        } else if (Integer.parseInt(s) == 2023) {
                            return Integer.parseInt(s) >= 10;
                        } else {
                            return false;
                        }

                    })
                    .map(EventMapper::toEvent)
                    .toList();
        } catch (Exception e) {
            contests = new ArrayList<>();
        }

        return contests;
    }

    public void upsertSuaiData() throws IOException {
        List<SuaiContest> suaiContests = getSuaiContests();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate maxDeadline = LocalDate.now().minusMonths(2L);

        List<Contest> contests = suaiContests
                .stream()
                .filter(contest -> LocalDate.parse(contest.getDateDeadline(), formatter).isBefore(maxDeadline))
                .map(suaiContest -> EventMapper.toContest(suaiContest))
                .toList();

    }

    public List<SuaiContest> getSuaiContests() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType suaiListType = objectMapper
                .getTypeFactory()
                .constructCollectionType(List.class, SuaiContest.class);

        URL src = new URL("https://api.guap.ru/data/get-nidgrants");
        List<SuaiContest> suaiContest = objectMapper.readValue(src, suaiListType);

        return suaiContest;
    }

    public List<SuaiEvent> getSuaiConferences() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType suaiListType = objectMapper
                .getTypeFactory()
                .constructCollectionType(List.class, SuaiEvent.class);
        URL src = new URL("https://api.guap.ru/data/get-nidannoconfs");
        List<SuaiEvent> suaiEvent = objectMapper.readValue(src, suaiListType);

        return suaiEvent;
    }

}
