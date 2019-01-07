package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.connection.H2Connection;
import es.salesianos.model.Actor;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;

public class PeliculaActorRepository {
	
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
	
	
	
}
