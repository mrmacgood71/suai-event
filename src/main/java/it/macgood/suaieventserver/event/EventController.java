package it.macgood.suaieventserver.event;

import com.fasterxml.jackson.annotation.JsonView;
import it.macgood.suaieventserver.event.model.Contest;
import it.macgood.suaieventserver.event.model.Event;
import it.macgood.suaieventserver.view.View;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/all")
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {

        try {
            List<Event> latestEvents = eventService.getLatestEvents();
            return ResponseEntity.ok(latestEvents);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/contests/byTag")
    public ResponseEntity<List<Contest>> getContestsByTag(
            @RequestParam String tag
    ) {
        return ResponseEntity.ok(eventService.getContestsByTag(tag));
    }


    @GetMapping("/contests")
    public ResponseEntity<List<Contest>> getAllContests() {
        try {
            List<Contest> latestContests = eventService.getLatestContests();
            return ResponseEntity.ok(latestContests);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Event> addEvent(
            @RequestBody Event event
    ) {
        return ResponseEntity.ok(eventService.addEvent(event));
    }

    @PostMapping("/addContest")
    public ResponseEntity<Contest> addContest(
            @RequestBody Contest contest
    ) {
        return ResponseEntity.ok(eventService.addContest(contest));
    }
    @GetMapping("/events/byStudent/{id}")
    @JsonView(View.GetUserInfo.class)
    public ResponseEntity<List<Event>> getEventsByUserId(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(eventService.getEventByStudentId(id));
    }

    @GetMapping("/contests/byStudent/{id}")
    @JsonView(View.GetUserInfo.class)
    public ResponseEntity<List<Contest>> getContestsByUserId(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(eventService.getContestByStudentId(id));
    }


    @GetMapping("/update")
    public void updateEvents() throws IOException {
        eventService.saveAll();
    }

    @GetMapping("/contests/{id}")
    public ResponseEntity<Contest> getContestById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(eventService.getContestById(id));
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventsById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }


}
