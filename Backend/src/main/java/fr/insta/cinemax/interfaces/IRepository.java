package fr.insta.cinemax.interfaces;

import java.util.List;

public interface IRepository<Entity> {

	Entity create(Entity entity);
	Entity get(Entity entity);
	List<Entity> getAll();
	Entity update(Entity entity);
	void delete(Entity entity);

}
