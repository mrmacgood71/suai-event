package it.macgood.suaieventserver.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.macgood.suaieventserver.event.model.Contest;
import it.macgood.suaieventserver.event.model.Event;
import it.macgood.suaieventserver.event.model.SuaiContest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/all")
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {

        try {
            List<Event> latestContests = eventService.getAllEvents();
            return ResponseEntity.ok(latestContests);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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

}
