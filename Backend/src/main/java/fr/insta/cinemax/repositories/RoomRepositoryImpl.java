package fr.insta.cinemax.repositories;

import fr.insta.cinemax.interfaces.IRoomRepository;
import fr.insta.cinemax.manager.ConnectionManager;
import fr.insta.cinemax.mappers.RoomMapper;
import fr.insta.cinemax.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomRepositoryImpl implements IRoomRepository {

	@Override
	public Room create(Room room) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "INSERT INTO room (name, capacity) VALUES (?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, room.getName());
			preparedStatement.setInt(2, room.getCapacity());

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next())
				return new Room(
					resultSet.getInt(1),
					room.getName(),
					room.getCapacity()
				);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public Room getRoomById(Integer id) {

		try {

			Connection connection = ConnectionManager.getInstance().getConnection();
			String selectStatement = "SELECT * FROM room WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			RoomMapper mapper = new RoomMapper();

			if (resultSet.next())
				return mapper.map(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
