package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.model.Room;

public interface IRoomRepository {

	Room create(Room room);
	Room getRoomById(Integer id);

}
