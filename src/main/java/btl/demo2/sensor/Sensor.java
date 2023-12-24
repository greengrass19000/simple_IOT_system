package btl.demo2.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import btl.demo2.controller.TemperatureController;
import jakarta.websocket.ClientEndpoint;

import java.lang.reflect.Type;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ClientEndpoint
public class Sensor implements StompSessionHandler {
    private Logger logger;
    private StompSession session;

    public Sensor() {
        this.logger = LoggerFactory.getLogger(TemperatureController.class);
    }

    void connect(String endpoint) {
        try {
            WebSocketClient client = new StandardWebSocketClient();

            WebSocketStompClient stompClient = new WebSocketStompClient(client);
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            StompSessionHandler sessionHandler = this;
            stompClient.connectAsync(endpoint, sessionHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        String msg = (String) payload;
        logger.info("Task received : " + msg);
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.session = session;
        this.startPubTemperatures();
        // this.startReceiveTasks();
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
            Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    private void startPubTemperatures() {
        Runnable task = () -> {
            logger.info("Publish temperature to broker ...");
            Double temperatureData = generateTemperatureData();
            session.send("/app/temperature", temperatureData);
        };
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleWithFixedDelay(task, 0, 3, TimeUnit.SECONDS);
    }

    private Double generateTemperatureData() {
        Double temperature = Math.random() * 100;
        return temperature;
    }

    private void startReceiveTasks() {
        session.subscribe("/topic/tasks", this);
    }

    public static void main(String[] args) {
        String endpoint = "ws://localhost:8080/ws";
        Sensor sensor = new Sensor();
        sensor.connect(endpoint);
    }
}
