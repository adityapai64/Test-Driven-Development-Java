import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieStore {
    //Any List implementation can be used here.
    List<Movie> movies = new LinkedList<Movie>();

    /*
     * An important thing to notice in all the methods below is that
     * all of them implement more or less the same logic:
     * 1. Iterate over the list of movies
     * 2. Check if the movie matches the search criteria
     * 3. If it does, add it to the result list
     * 4. Return the result list
     * This is a great chance to refactor the code and extract the common logic
     * into an interface.
     *
     * In the next commit, this is exactly what will be implemented.
     */

    public List<Movie> findByPartialTitle(String partialTitle) {
        List<Movie> result = new LinkedList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase())) {
                result.add(movie);
            }
        }
        return result;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirector(String directorName) {
        List<Movie> result = new LinkedList<>();
        for (Movie movie : movies) {
            if (movie.getDirector().equalsIgnoreCase(directorName)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> findByReleaseYear(int yearFrom, int yearTo) {
        List <Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            int releaseYear = movie.getReleaseYear();
            if (releaseYear > yearFrom && releaseYear < yearTo) {
                result.add(movie);
            }
        }
        return result;
    }
}
