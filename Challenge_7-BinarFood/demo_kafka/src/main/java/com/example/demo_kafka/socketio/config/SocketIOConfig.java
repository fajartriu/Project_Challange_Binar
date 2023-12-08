package com.example.demo_kafka.socketio.config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class SocketIOConfig {

    private SocketIOServer server;

    private final String HOSTNAME;
    private final int PORT;

    public SocketIOConfig(@Value("${socket.hostname}") String hostname,
                                 @Value("${socket.port}") int port){
        this.HOSTNAME = hostname;
        this.PORT = port;
    }

    @Bean
    public SocketIOServer startSocketIOServer(){
        Configuration configuration = new Configuration();
        configuration.setHostname(HOSTNAME);
        configuration.setPort(PORT);

        server = new SocketIOServer(configuration);
        server.start();

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                log.info("Ada user baru terkoneksi");
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                log.info("Ada user meninggalkan koneksi");
            }
        });

        return server;
    }

    @PreDestroy
    public void stopSocketIOServer(){
        server.start();
    }

    @Component
    @Log4j2
    @RequiredArgsConstructor
    public static class SocketIOController {
        private final SocketIOServer socketIOServer;
        private ConnectListener onUserConnected = new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                log.info("Controller onConnected");
            }
        };


        private DisconnectListener onUserDisconnected = new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                log.info("Controller onDisconnected");
            }
        };

        private final DataListener<TheMessage> onMessageSent = new DataListener<>() {
            @Override
            public void onData(SocketIOClient socketIOClient, TheMessage theMessage, AckRequest ackRequest) throws Exception {
                log.info("Pesan dari"+theMessage.getFrom()+" ke "+theMessage.getTo()+": "+theMessage.getMessage());

                socketIOServer.getBroadcastOperations()
                        .sendEvent(theMessage.getTo(), socketIOClient, theMessage.getMessage());

                ackRequest.sendAckData("Pesan sudah terkirim ke "+theMessage.getTo());
            }
        };

    }
}
