package com.itsol.bank.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	private int statusCode;
    private String message;
    private String errorValue;
	public ErrorMessage(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
}
