package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.connection.H2Connection;
import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;

public class ActorRepository {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	AbstractConnection manager = new H2Connection();
	private static final Logger log = LogManager.getLogger(ActorRepository.class);
	
	public void insert(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO ACTOR (name,yearOfBirthDate)" + "VALUES (?, ?)");
			preparedStatement.setString(1, actor.getName());
			preparedStatement.setInt(2, actor.getYear());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
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
			log.error(e);
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
				actor.setName(resultSet.getNString(2));
				actor.setYear(resultSet.getInt(3));
				list.add(actor);
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}
	
	public Director filterAllDirector(String name) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		Actor actor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT DIRECTOR.NAME" + 
					" FROM (((ACTOR" + 
					" INNER JOIN FILMACTOR ON FILMACTOR.CODACTOR = ACTOR.COD)" + 
					" INNER JOIN FILM ON FILM.COD = FILMACTOR.CODFILM)" + 
					" INNER JOIN DIRECTOR ON DIRECTOR.COD = FILM.CODOWNER)" + 
					" WHERE ACTOR.NAME = (?)");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setName(resultSet.getString(1));
				actor = actorfromDataBase;
			}			
		} catch (SQLException e) {
			log.error(e);
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
				actor.setName(resultSet.getNString(2));
				actor.setYear(resultSet.getInt(3));
				list.add(actor);
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}
	
	
	
}
