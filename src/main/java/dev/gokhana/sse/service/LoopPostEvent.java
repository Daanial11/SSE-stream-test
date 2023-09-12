package dev.gokhana.sse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

@Service
public class LoopPostEvent{

    
    @Scheduled(fixedRate=360000)
    public void postEvent() {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080/api/v1/live-scores");
        String scores = "{\"homeTeam\":\"Arsenal\", \"awayTeam\":\"Tottenham\", \"homeScore\": 1, \"awayScore\": 1}";
        request.addHeader("Content-Type", "application/json");
        try {
            request.setEntity(new StringEntity(scores));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpclient.execute(request);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}