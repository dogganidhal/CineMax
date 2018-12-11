package fr.insta.cinemax.interfaces;


import fr.insta.cinemax.model.Movie;

import java.util.List;

public interface IMovieRepository {

	Movie getMovieById(Integer id);
	List<Movie> getMovies();
	Movie create(Movie movie);

}
