package fr.insta.cinemax.interfaces;


import fr.insta.cinemax.model.Movie;

import java.util.List;

public interface IMovieRepository {

	List<Movie> getMovies();
	Integer getAvailabililities(Integer movieId);

}
