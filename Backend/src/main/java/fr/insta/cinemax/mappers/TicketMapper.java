package fr.insta.cinemax.mappers;

import fr.insta.cinemax.model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements IEntityMapper<Ticket> {

	@Override
	public Ticket map(ResultSet resultSet) throws SQLException {
		return new Ticket(
			resultSet.getInt("id"),
			null,
			null,
			resultSet.getDouble("price"),
			resultSet.getDate("created_date")
		);
	}

}
