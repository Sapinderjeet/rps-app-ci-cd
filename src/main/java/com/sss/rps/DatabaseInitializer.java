package com.sss.rps;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(ScoreRepository scoreRepo) {
        return args -> {
            // Check if a score entry exists
            if (scoreRepo.findById(1).isEmpty()) {
                // Create a default score entry
                Score initialScore = new Score();
                initialScore.setId(1);
                initialScore.setWin(0);
                initialScore.setLose(0);
                initialScore.setTies(0);
                scoreRepo.save(initialScore);
            }
        };
    }
}