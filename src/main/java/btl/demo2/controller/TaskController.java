package btl.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class TaskController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 1000) // Push message every 1 seconds
    public void sendPeriodicMessage() {
        long value = Math.round(Math.random() * 3);
        String message = String.format("Task: %d", value);
        messagingTemplate.convertAndSend("/topic/tasks", message);
    }
}
