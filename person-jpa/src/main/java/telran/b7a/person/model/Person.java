package telran.b7a.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "person")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7768434475852222184L;
	@Id
	int id;
	String name;
	LocalDate birthDate;
	@Embedded
	Address address;
}
