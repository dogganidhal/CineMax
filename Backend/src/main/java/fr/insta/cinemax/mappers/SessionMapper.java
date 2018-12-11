package fr.insta.cinemax.mappers;

import fr.insta.cinemax.model.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements IEntityMapper<Session> {

	@Override
	public Session map(ResultSet resultSet) throws SQLException {
		return new Session(
				resultSet.getInt("id"),
				resultSet.getDate("start_date"),
				null,
				null,
				resultSet.getInt("ticket_count")
		);
	}

}
