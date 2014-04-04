package com.filmkampen.filmkampen_server.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.Movie;

@Component
public class MovieService extends Service<Movie> {

    public static void main(String[] args) {
        MovieService service = new MovieService();
        Movie movie = new Movie();
        movie.setTitle("Dragon");
        service.save(movie);
        List<Movie> movies = service.list();
        for (Movie tempMovie : movies) {
            tempMovie.setDescription("Description");
            service.save(tempMovie);
        }
    }
}
