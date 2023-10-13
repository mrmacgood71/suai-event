package it.macgood.suaieventserver.event.tasks;

import it.macgood.suaieventserver.event.EventService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final EventService eventService;

    @Scheduled(cron = "0 7 1 * * ?")
    public void updateEventsData() {


    }
}
