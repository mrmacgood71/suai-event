package it.macgood.suaieventserver.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import it.macgood.suaieventserver.event.model.*;
import it.macgood.suaieventserver.event.repository.ContestRepository;
import it.macgood.suaieventserver.event.repository.EventRepository;
import it.macgood.suaieventserver.user.model.Student;
import it.macgood.suaieventserver.user.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private final ContestRepository contestRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
//    private final SuaiConferencesRepository suaiConferencesRepository;
//    private final SuaiEventRepository suaiEventRepository;


    public void saveAll() throws IOException {
        try {
            contestRepository.deleteAll();
            eventRepository.deleteAll();
            List<Contest> latestContests = getParsedLatestContests();
            List<Contest> contests = contestRepository.saveAll(latestContests);
            List<Event> latestEvents = getParsedLatestEvents();
            List<Event> events = eventRepository.saveAll(latestEvents);
            System.out.println(contests);
            System.out.println(events);
        } catch (Exception e) {
            System.out.println("asdf");
        }
    }

    public Contest addContest(Contest contest) {
        return contestRepository.save(contest);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Contest> getLatestContests() {
        return contestRepository.findAll();
    }

    public List<Event> getLatestEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(String id) {
        return eventRepository.findById(Long.parseLong(id)).get();
    }

    public Contest getContestById(String id) {
        return contestRepository.findById(Long.parseLong(id)).get();
    }


    public List<Contest> getParsedLatestContests() throws IOException {
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
                            return Integer.parseInt(s1) >= 10;
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

    public List<Event> getParsedLatestEvents() throws IOException {
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
                            return Integer.parseInt(s1) >= 10;
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

    public List<Event> getEventByStudentId(String id) {
        Optional<Student> student = studentRepository.findById(id);

        return student.get().getEvents();
    }

    public List<Contest> getContestByStudentId(String id) {
        Optional<Student> student = studentRepository.findById(id);

        return student.get().getContests();
    }

    public List<Contest> getContestsByTag(String tag) {
        List<Contest> response = new ArrayList<>();
        switch (tag) {
            case "Беспилотники":
                try {
                    response = contestRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTag().equals("Беспилотники"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Soft Skills":
                try {
                    response = contestRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTag().equals("Soft Skills"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "IT/Программирование":
                try {
                    response = contestRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTag().equals("IT/Программирование"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Радиоэлектроника":
                try {
                    response = contestRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTag().equals("Радиоэлектроника"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Социальные":
                try {
                    response = contestRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTag().equals("Социальные"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Развлекательные":
                try {
                    response = contestRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTag().equals("Развлекательные"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            default:
                try {
                    response = contestRepository.findAll();
                } catch (Exception e) {

                }

                return response;
        }
    }

    public List<Event> getEventsByTag(String tag) {
        List<Event> response = new ArrayList<>();
        switch (tag) {
            case "Беспилотники":
                try {
                    response = eventRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTags().contains("Беспилотники"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Soft Skills":
                try {
                    response = eventRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTags().contains("Soft Skills"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "IT/Программирование":
                try {
                    response = eventRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTags().contains("IT/Программирование"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Радиоэлектроника":
                try {
                    response = eventRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTags().contains("Радиоэлектроника"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Социальные":
                try {
                    response = eventRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTags().contains("Социальные"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            case "Развлекательные":
                try {
                    response = eventRepository.findAll()
                            .stream()
                            .filter(contest -> contest.getTags().contains("Развлекательные"))
                            .toList();
                } catch (Exception e) {

                }

                return response;
            default:
                try {
                    response = eventRepository.findAll();
                } catch (Exception e) {

                }

                return response;
        }
    }
}
