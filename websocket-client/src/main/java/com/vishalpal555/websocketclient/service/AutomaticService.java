package com.vishalpal555.websocketclient.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AutomaticService {
    @Autowired
    private ClientService clientService;
    @PostConstruct
    private void init(){
        System.out.println("running port construct init");
        try {
            clientService.connect();
        } catch (ExecutionException e) {
            System.out.println("execution exception " +e);
            System.out.println("connecting again.......");
            try {
                for (int i=5; i>0; i--){
                    Thread.sleep(1000);
                    System.out.println(i);
                }
            } catch (InterruptedException ex) {
                System.out.println("interrupted exception for counter " +e);
            }
            init();
        } catch (InterruptedException e) {
            System.out.println("interrupted exception for stomp.connect() " +e);
        }
    }
}
