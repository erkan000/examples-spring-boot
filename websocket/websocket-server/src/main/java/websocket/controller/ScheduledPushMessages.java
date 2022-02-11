package websocket.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import websocket.dto.WalletDto;

import java.util.UUID;

@Service
public class ScheduledPushMessages {

    private final SimpMessagingTemplate simpMessagingTemplate;
    
    public ScheduledPushMessages(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
    	WalletDto wallet = new WalletDto(UUID.randomUUID().toString(), "My Wallet");
        simpMessagingTemplate.convertAndSend("/topic/wallet", wallet);
    }
    
}
