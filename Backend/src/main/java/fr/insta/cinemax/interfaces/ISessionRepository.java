package fr.insta.cinemax.interfaces;

import fr.insta.cinemax.model.Session;

import java.util.List;


public interface ISessionRepository {

	List<Session> getSessionsForMovie(Integer movieId);

}
