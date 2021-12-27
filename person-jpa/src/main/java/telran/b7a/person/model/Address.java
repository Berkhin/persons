package telran.b7a.person.model;

import java.io.Serializable;
import java.time.LocalDate;

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
@EqualsAndHashCode
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7443996357528027225L;
	String city;
	String street;
	int building;
}
