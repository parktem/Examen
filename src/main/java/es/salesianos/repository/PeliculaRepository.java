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
import es.salesianos.model.Pelicula;

public class PeliculaRepository {

	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	AbstractConnection manager = new H2Connection();
	private static final Logger log = LogManager.getLogger(ActorRepository.class);
	
	public void insert(Pelicula pelicula) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO FILM (tittle)" + "VALUES (?)");
			preparedStatement.setString(1, pelicula.getTitle());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
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
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

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
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}
	
}
