package org.capestart.dto;

public class LMSResponseDTO {
	
	private String message;
	
	private Object data;

	public LMSResponseDTO(String message, Object data) {
		this.message = message;
		this.data = data;
	}
	
	public LMSResponseDTO( Object data) {
		this.data = data;
	}
	
	public LMSResponseDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
