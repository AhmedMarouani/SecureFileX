package com.example.Challenge2.component;

import com.example.Challenge2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ScheduledTasks {
    private UserRepository userRepository;

    @Autowired
    private ScheduledTasks(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        DateFormat timeStamp = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Integer n = userRepository.numberOfUsers();
        System.out.println("we have "+ n + " Users" + " at " + timeStamp.format( cal.getTime()));
    }
}
