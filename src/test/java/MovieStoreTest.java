import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class MovieStoreTest {

    private final MovieStore movieStore = new MovieStore();
    private final Movie harryPotter1 = new Movie("Harry Potter and the Philosopher's Stone", "Rowling", 1997);
    private final Movie harryPotter2 = new Movie("Harry Potter and the Chamber of Secrets", "Rowling", 2000);
    private final Movie harryPotter3 = new Movie("Harry Potter and the Prisoner of Azkaban", "Rowling", 2001);
    private final Movie shawshankRedemption = new Movie("Shawshank Redemption", "Schwimmer", 1991);

    @Before
    public void setup() {
        movieStore.addMovie(harryPotter1);
        movieStore.addMovie(harryPotter2);
        movieStore.addMovie(harryPotter3);
        movieStore.addMovie(shawshankRedemption);
    }

    @Test
    public void returnNoResultsWhenNoTitlesPartiallyMatchSearch() {
        MovieStore movieStore = new MovieStore();
        List<Movie> result = movieStore.findByPartialTitle("NonExistentTitle");
        assertThat(result.size(), is(0));
    }

    @Test
    public void findAMoviesWhenTitleIsPartiallyMatches() {
        List<Movie> result = movieStore.findByPartialTitle("Harry");
        assertThat(result.size(), is(3));
        assertThat(result, containsInAnyOrder(harryPotter1,
                harryPotter2,
                harryPotter3));
    }

    @Test
    public void findAMoviesWhenDirectorIsExactlyMatches() {
        List<Movie> result = movieStore.findByDirector("Rowling");
        assertThat(result.size(), is(3));
        assertThat(result, containsInAnyOrder(harryPotter1,
                harryPotter2,
                harryPotter3));
    }

    @Test
    public void findMoviesWhenReleaseYearIsBetweenTwoValues() {
        List<Movie> result = movieStore.findByReleaseYear(1999, 2002);
        assertThat(result.size(), is(2));
        assertThat(result, containsInAnyOrder(harryPotter2, harryPotter3));
    }
}