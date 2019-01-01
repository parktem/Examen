package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.connection.H2Connection;
import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Actor;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;

public class Repository {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	AbstractConnection manager = new H2Connection();

	public void insert(PeliculaActor peliculaActor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO FILMACTOR (cache, role, codActor, codFilm)" + "VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, peliculaActor.getCache());
			preparedStatement.setString(2, peliculaActor.getRole());
			preparedStatement.setInt(3, peliculaActor.getCodActor());
			preparedStatement.setInt(4, peliculaActor.getCodPelicula());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}

	public void insert(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO ACTOR (name,yearOfBirthDate)" + "VALUES (?, ?)");
			preparedStatement.setString(1, actor.getNombre());
			preparedStatement.setInt(2, actor.getYear());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}

	public void insert(Pelicula pelicula) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO FILM (tittle)" + "VALUES (?)");
			preparedStatement.setString(1, pelicula.getTitle());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}

	public void insert(Director director) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO DIRECTOR (name)" + "VALUES (?)");
			preparedStatement.setString(1, director.getNombre());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}

	public void delete(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM ACTOR WHERE cod=?");
			preparedStatement.setInt(1, actor.getCod());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}
	
	public void delete(Director director) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM DIRECTOR WHERE cod=?");
			preparedStatement.setInt(1, director.getCod());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}

	public void delete(Pelicula pelicula) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM FILM WHERE cod=?");
			preparedStatement.setInt(1, pelicula.getCod());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}


	public List<Actor> filterAllActor(int beginDate, int endDate) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> list = new ArrayList<Actor>();
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM ACTOR WHERE yearOfBirthDate BETWEEN (?) AND (?)");
			preparedStatement.setInt(1, beginDate);
			preparedStatement.setInt(2, endDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setNombre(resultSet.getNString(2));
				actor.setYear(resultSet.getInt(3));
				list.add(actor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

	public PeliculaActor filterAllPeliculaActor(String role) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		PeliculaActor peliculaActor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM FILMACTOR WHERE ROLE = (?)");
			preparedStatement.setString(1, role);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PeliculaActor peliculaActorfromDataBase = new PeliculaActor();
				peliculaActorfromDataBase.setCache(resultSet.getInt(1));
				peliculaActorfromDataBase.setRole(resultSet.getString(2));
				peliculaActorfromDataBase.setCodActor(resultSet.getInt(3));
				peliculaActorfromDataBase.setCodPelicula(resultSet.getInt(4));
				peliculaActor = peliculaActorfromDataBase;
			}
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor where cod=" + peliculaActor.getCodActor());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setNombre(resultSet.getString(2));
				actorfromDataBase.setYear(resultSet.getInt(3));
				peliculaActor.setActor(actorfromDataBase);
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILM where cod=" + peliculaActor.getCodPelicula());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pelicula peliculafromDataBase = new Pelicula();
				peliculafromDataBase.setCod(resultSet.getInt(1));
				peliculafromDataBase.setTitle(resultSet.getString(2));
				peliculafromDataBase.setCodDirector(resultSet.getInt(3));
				peliculaActor.setPelicula(peliculafromDataBase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return peliculaActor;
	}

	public Actor filterAllDirector(String name) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		Actor actor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor WHERE name = (?)");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setCod(resultSet.getInt(1));
				actorfromDataBase.setNombre(resultSet.getString(2));
				actorfromDataBase.setYear(resultSet.getInt(3));
				actor = actorfromDataBase;
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILMACTOR where codactor=" + actor.getCod());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PeliculaActor peliculaActorfromDataBase = new PeliculaActor();
				peliculaActorfromDataBase.setCodPelicula(resultSet.getInt(4));
				actor.getPeliculaActor().add(peliculaActorfromDataBase);
			}
			
			int index = 0;
			for (PeliculaActor peliculaActor : actor.getPeliculaActor()) {
				preparedStatement = conn
						.prepareStatement("SELECT * FROM FILM where cod=" + peliculaActor.getCodPelicula());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Pelicula peliculafromDataBase = new Pelicula();
					peliculafromDataBase.setTitle(resultSet.getString(2));
					peliculafromDataBase.setCodDirector(resultSet.getInt(3));
					actor.getPeliculaActor().get(index).setPelicula(peliculafromDataBase);
				}
				index++;
			}
			index = 0;
			for (PeliculaActor peliculaActor : actor.getPeliculaActor()) {
				preparedStatement = conn
						.prepareStatement("SELECT * FROM DIRECTOR where COD=" + peliculaActor.getPelicula().getCodDirector());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Director directorfromDataBase = new Director();
					directorfromDataBase.setNombre(resultSet.getString(2));
					actor.getPeliculaActor().get(index).getPelicula().setDirector(directorfromDataBase);
				}
				index++;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return actor;
	}

	public List<Actor> selectAllActor() {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> list = new ArrayList<Actor>();
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM ACTOR");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setNombre(resultSet.getNString(2));
				actor.setYear(resultSet.getInt(3));
				list.add(actor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

	public List<Director> selectAllDirector() {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Director> list = new ArrayList<Director>();
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM DIRECTOR");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor director = new Actor();
				director.setCod(resultSet.getInt(1));
				director.setNombre(resultSet.getString(2));
				list.add(director);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

	public List<Pelicula> selectAllPelicula() {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Pelicula> list = new ArrayList<Pelicula>();
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM FILM");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Pelicula director = new Pelicula();
				director.setCod(resultSet.getInt(1));
				director.setTitle(resultSet.getString(2));
				director.setCodDirector(resultSet.getInt(3));
				list.add(director);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

}
