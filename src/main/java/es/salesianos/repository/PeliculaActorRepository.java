package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.connection.H2Connection;
import es.salesianos.model.Actor;
import es.salesianos.model.DtoActorFilm;
import es.salesianos.model.Pelicula;
import es.salesianos.model.PeliculaActor;

public class PeliculaActorRepository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	AbstractConnection manager = new H2Connection();
	private static final Logger log = LogManager.getLogger(ActorRepository.class);

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
			log.error(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}

	}
	
	public DtoActorFilm filterAllPeliculaActor(String role) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		DtoActorFilm dto = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT TITTLE, NAME, YEAROFBIRTHDATE" + 
					" FROM ((FILMACTOR" + 
					" INNER JOIN FILM ON FILM.COD = FILMACTOR.CODFILM)" + 
					" INNER JOIN ACTOR ON ACTOR.COD = FILMACTOR.CODACTOR)" + 
					" WHERE FILMACTOR.ROLE = (?)");
			preparedStatement.setString(1, role);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DtoActorFilm dtofromDataBase = new DtoActorFilm();
				dtofromDataBase.setTitle(resultSet.getString(1));
				dtofromDataBase.setName(resultSet.getString(2));
				dtofromDataBase.setYear(resultSet.getInt(3));
				dto = dtofromDataBase;
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		return dto;
	}
	
	
	
}
