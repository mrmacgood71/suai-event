package it.macgood.suaieventserver.event.tasks;

import it.macgood.suaieventserver.event.EventService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final EventService eventService;

    @Scheduled(cron = "0 46 2 * * ?")
    public void updateEventsData() throws IOException {
        eventService.saveAll();
    }
}
