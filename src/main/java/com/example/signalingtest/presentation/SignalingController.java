package com.example.signalingtest.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignalingController {

    /*
    offer 정보를 주고 받기 위한 WebSocket
    camKey 각 요청하는 캠의 key, roomId 룸아이디
     */
    @MessageMapping("/peer/offer/{camKey}/{roomId}")
    @SendTo("/topic/peer/offer/{camKey}/{roomId}")
    public String PeerHandleOffer(
            @Payload String offer,
            @DestinationVariable(value = "roomId") String roomId,
            @DestinationVariable(value = "camKey") String camKey
    ) {

        log.info("[OFFER] {} : {}", camKey, offer);
        return offer;
    }

    /*
    iceCandidate: 정보를 주고 받기 위한 webSocket
    camKey: 각 요청하는 캠의 key
    roomId: 룸 아이디
     */
    @MessageMapping("/peer/iceCandidate/{camKey}/{roomId}")
    @SendTo("/topic/peer/iceCandidate/{camKey}/{roomId}")
    public String PeerHandleIceCandidate(
            @Payload String candidate,
            @DestinationVariable(value = "roomId") String roomId,
            @DestinationVariable(value = "camKey") String camKey
    ) {

        log.info("[OFFER] {} : {}", camKey, candidate);
        return candidate;
    }

    @MessageMapping("/peer/answer/{camKey}/{roomId")
    @SendTo("/topic/peer/answer/{camKey}/{roomId}")
    public String PeerHandleAnswer (
            @Payload String answer,
            @DestinationVariable(value = "roomId") String roomId,
            @DestinationVariable(value = "camKey") String camKey
    ) {

        log.info("[OFFER] {} : {}", camKey, answer);
        return answer;
    }


    //camKey를 받기 위해 신호를 보내는 웹소켓
    @MessageMapping("/call/key")
    @SendTo("/topic/call/key")
    public String callKey (
            @Payload String message
    ) {

        log.info("[Key] {}", message);
        return message;
    }

    //자신의 camKey를 모든 연결된 세션에 보내는 웹소켓
    @MessageMapping("/send/key")
    @SendTo("/topic/send/key")
    public String sendKey (
            @Payload String message
    ) {
        return message;
    }
}
