package com.sss.rps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id; // Use this import for JPA
import lombok.Data;

@Data
@Entity
public class Score {
	
	
	@Id
	private int id;
	
	private int win;
	private int lose;
	private int ties;
	
	 public Score() {
	        this.id = 1; // Default ID for simplicity
	        this.win = 0;
	        this.lose = 0;
	        this.ties = 0;
	    }
}
