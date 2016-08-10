
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
    	FirstRatings first = new FirstRatings();
    	myRaters = first.loadRaters(ratingsfile);
    	myMovies = first.loadMovies(moviefile);
    }
    
    public Integer getMovieSize(){
    	return myMovies.size();
    }
    
    public Integer getRaterSize(){
		HashSet<String> ratersSet = new HashSet<String>();
		for (EfficientRater rater : myRaters){
			ratersSet.add(rater.getID());
		}
    	return ratersSet.size();
    }
    
    private double getAverageByID(String id, Integer minimalRaters){
    	int ratings = 0;
    	double rating_average = 0.0;
    	for (EfficientRater rater : myRaters){
    		ArrayList<String> raters = rater.getItemsRated();
    		String movie_id = raters.get(0);
    		if (movie_id.equals(id)){
    			ratings += 1;
    			rating_average = rating_average + rater.getRating(movie_id);
    		}
    	}
    	if (ratings >= minimalRaters){
    		return rating_average/ratings;
    	}else{
    		return 0.0;
    	}
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    	FirstRatings first = new FirstRatings();
    	HashMap<String, Integer> ratingsMap = first.loadMoviesMap(myRaters); // a map that contains movie id and number of ratings
    	ArrayList<Rating> ratingArray = new ArrayList<Rating>();
    	for (String s : ratingsMap.keySet()){
    		int value = ratingsMap.get(s);
    		if (value >= minimalRaters){
    			double average = getAverageByID(s, minimalRaters);
    			Rating rating = new Rating(s, average);
    			ratingArray.add(rating); //an array that contains the movie id and the average of ratings
    		}
    	}
    	return ratingArray;
    }
    
    public String getTitle(String id){
    	for (Movie movie : myMovies){
    		if (movie.getID().equals(id)){
    			return movie.getTitle();
    		}
    	}
    	return "The movie, which id is " + id + "was not found.";
    }
    
    public String getID(String title){
    	for (Movie movie : myMovies){
    		if (movie.getTitle().equals(title)){
    			return movie.getID();
    		}
    	}
    	return "NO SUCH TITLE";
    }
}