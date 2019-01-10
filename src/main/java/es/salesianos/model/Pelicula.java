package es.salesianos.model;

public class Pelicula extends BaseEntity{

	private String title;
	private Integer codDirector;
	private Director director;
	
	
	public Integer getCodDirector() {
		return codDirector;
	}
	public void setCodDirector(Integer codDirector) {
		this.codDirector = codDirector;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	
}
