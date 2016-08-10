import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ThirdRatings {
	//private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile){
    	FirstRatings first = new FirstRatings();
    	myRaters = first.loadRaters(ratingsfile);
    	//myMovies = first.loadMovies(moviefile);
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
    	Filter allmovies = new TrueFilter();
    	ArrayList<String> movies = MovieDatabase.filterBy(allmovies); // an array list that contains movie ids
    	FirstRatings first = new FirstRatings();
    	HashMap<String, Integer> ratingsMap = first.loadMoviesMap(myRaters); // a map that contains movie id and number of ratings
    	ArrayList<Rating> ratings = new ArrayList<Rating>(); // a new array list of Ratings
    	
    	for (String s : ratingsMap.keySet()){
    		if (movies.contains(s)){
        		if (ratingsMap.get(s) >= minimalRaters){
        			double average = getAverageByID(s, minimalRaters);
        			Rating rating = new Rating(s, average);
        			ratings.add(rating); //an array that contains the movie id and the average of ratings       			
        		}
    		}
    	}
    	return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    	FirstRatings first = new FirstRatings();
    	HashMap<String,Integer> ratingsMap = first.loadMoviesMap(myRaters); // a map that contains the movie id and number of ratings
    	ArrayList<Rating> ratings = new ArrayList<Rating>(); // a new array list of Ratings
    	
    	for (String m : movies){
    		if (ratingsMap.containsKey(m)){
    			if (ratingsMap.get(m) >= minimalRaters){
    				double average = getAverageByID(m, minimalRaters);
    				Rating rating = new Rating(m, average);
    				ratings.add(rating); //an array that contains the movie id and the average of ratings
    			}
    		}
    	}
    	return ratings;
    }
}
