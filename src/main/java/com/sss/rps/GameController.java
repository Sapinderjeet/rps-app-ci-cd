package com.sss.rps;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private ScoreRepository scoreRepo;

    private Score score; // Do not use @Autowired here

    // Display the main page
    @GetMapping("/")
    public String showGamePage() {
    	 System.out.print("just checking codebuild");
        return "index"; // Renders index.html
    }

   
    
    
    
    // Handle the POST request when the user submits their choice
    @PostMapping("/play")
    public String playGame(@RequestParam("choice") String userChoice, Model model) {
        // Fetch the score from the database if it exists, or create a new one
        score = scoreRepo.findById(1).orElse(new Score());
        score.setId(1); // Ensure the ID is set
        scoreRepo.save(score);

        // Define possible choices
        String[] choices = { "rock", "paper", "scissors" };
        Random random = new Random();
        int computerIndex = random.nextInt(choices.length);
        String computerChoice = choices[computerIndex];

        // Determine the outcome of the game
        String outcome = determineOutcome(userChoice.toLowerCase(), computerChoice);

        // Update the score based on the outcome
        if (outcome.equals("You win!")) {
            score.setWin(score.getWin() + 1);
        } else if (outcome.equals("You lose!")) {
            score.setLose(score.getLose() + 1);
        } else {
            score.setTies(score.getTies() + 1);
        }

        // Save the updated score to the database
        scoreRepo.save(score);

        // Add data to the model to pass to the results page
        model.addAttribute("userChoice", userChoice);
        model.addAttribute("computerChoice", computerChoice);
        model.addAttribute("outcome", outcome);

        // Render the results page
        return "results";
    }

    // Show the current score
    @GetMapping("/score")
    public String showScore(Model model) {
        // Fetch the score from the database if it exists, or create a new one
        score = scoreRepo.findById(1).orElse(new Score());
        score.setId(1); // Ensure the ID is set
        scoreRepo.save(score);

        // Add the score object to the model
        model.addAttribute("score", score);

        // Render the score page
        return "score";
    }

    // Helper method to determine the outcome
    private String determineOutcome(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        }
        if ((userChoice.equals("rock") && computerChoice.equals("scissors"))
                || (userChoice.equals("paper") && computerChoice.equals("rock"))
                || (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "You win!";
        }
        return "You lose!";
    }
}