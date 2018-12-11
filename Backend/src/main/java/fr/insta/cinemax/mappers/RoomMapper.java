package fr.insta.cinemax.mappers;

import fr.insta.cinemax.model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements IEntityMapper<Room> {

	@Override
	public Room map(ResultSet resultSet) throws SQLException {
		return new Room(
			resultSet.getInt("id"),
			resultSet.getString("name"),
			resultSet.getInt("capacity")
		);
	}

}
