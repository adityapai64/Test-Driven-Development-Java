import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieStore {
    //Any List implementation can be used here.
    List<Movie> movies = new LinkedList<Movie>();

    /*
     **NOTES FROM THE PREVIOUS COMMIT:**
     * An important thing to notice in all the search methods below is that
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

    /*
    * This time around, a new interface, called Predicate, is created.
    * The interface's only method, called matches, can be implemented
    * differently each time to customise the search criteria as required.
    * The helper method findByPredicate is used to iterate over the
    * list of movies, apply the predicate and find matches.
    *
    * This way, the search methods are simplified. The for-loop and the
    * if-condition are removed from each search method, and outsourced
    * to the findByPredicate method, thereby eliminating repetitive,
    * redundant code.
    *
    * This is also a great example of the Open/Closed Principle, where the
    * code is open for extension (by adding new predicates) but closed
    * for modification (the existing code does not need to change
    * to add new search criteria).
    *
    * In the implementation below, the findByPartialTitle method uses the
    * classic anonymous class syntax to implement the Predicate interface,
    * while the findByDirector and findByReleaseYear methods use a lambda
    * expression.
    * */

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByPartialTitle(String partialTitle) {
        Predicate predicate = new Predicate() {
            @Override
            public boolean matches(Movie movie) {
                return movie.getTitle().toLowerCase().contains(partialTitle.toLowerCase());
            }
        };

        return findByPredicate(predicate);
    }


    public List<Movie> findByDirector(String directorName) {
        Predicate predicate = movie -> movie.getDirector()
                .equalsIgnoreCase(directorName);
        return findByPredicate(predicate);
    }

    public List<Movie> findByReleaseYear(int yearFrom, int yearTo) {
        //No need for a new Predicate instance here, a lambda expression is sufficient.
        return findByPredicate(movie -> movie.getReleaseYear() > yearFrom
                && movie.getReleaseYear() < yearTo);
    }

    private List<Movie> findByPredicate(Predicate predicate) {
        List<Movie> result = new LinkedList<>();
        for (Movie movie : movies) {
            if (predicate.matches(movie)) {
                result.add(movie);
            }
        }
        return result;
    }


    interface Predicate{
        boolean matches(Movie movie);
    }
}
