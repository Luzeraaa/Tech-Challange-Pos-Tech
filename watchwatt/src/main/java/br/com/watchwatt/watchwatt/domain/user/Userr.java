package br.com.watchwatt.watchwatt.domain.user;

import java.time.ZonedDateTime;

import br.com.watchwatt.watchwatt.domain.user.dto.DataRegister;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Userr")
@Table(name = "userr")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Userr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cpf;
	
	private String name;
	
	private ZonedDateTime birthday;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	public Userr(DataRegister data) {
		
		this.cpf = data.cpf();
		this.name = data.name();
		this.birthday = data.birthday();
		this.gender = data.gender();
		
	}
	
}
