package fr.insta.cinemax.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IEntityMapper<Entity> {

	Entity map(ResultSet resultSet) throws SQLException;

}
