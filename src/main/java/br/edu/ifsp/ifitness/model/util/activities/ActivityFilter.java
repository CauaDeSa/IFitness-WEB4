package br.edu.ifsp.ifitness.model.util.activities;

import java.time.LocalDate;

import br.edu.ifsp.ifitness.model.ActivityType;
import br.edu.ifsp.ifitness.model.User;

public class ActivityFilter {

	private User user;
	
	private ActivityType type;
	
	private LocalDate initialDate;
	
	private LocalDate finalDate;

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public LocalDate getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(LocalDate initialDate) {
		this.initialDate = initialDate;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
