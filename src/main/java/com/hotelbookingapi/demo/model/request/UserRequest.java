package com.hotelbookingapi.demo.model.request;

import lombok.Data;

@Data
public class UserRequest {
	private String name;
	private long bonus;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBonus() {
		return bonus;
	}
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}
}
