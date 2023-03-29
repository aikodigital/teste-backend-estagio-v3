package machado.antonio.testebackendestagiov3.dtos;

import org.springframework.stereotype.Component;

@Component
public class EquipmentStateDTO {

	private String name;

	private String color;

	// Getters and Setters;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
