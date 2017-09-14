package vertxAndSpring.vertxAndSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Serie {
	
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="temporada")
	private int temporada;

	public Serie() {}

	public Serie(long id, String nombre, int temporada) {
		this.id = id;
		this.nombre = nombre;
		this.temporada = temporada;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
}
