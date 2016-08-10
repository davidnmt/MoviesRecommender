import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {
	
    public static void initializeMovies(String moviefile) {
    	MovieDatabase.initialize(moviefile);
    }

    public static void initializeRaters(String filename) {
    	RaterDatabase.initialize(filename);
 	}
	
    private static double getAverageByID(String id, Integer minimalRaters){
    	int ratings = 0;
    	double rating_average = 0.0;
    	ArrayList<Rater> myRaters = RaterDatabase.getRaters();
    	
    	for (Rater rater : myRaters){
    		ArrayList<String> raters = rater.getItemsRated();
    		for (String r : raters){
        		if (r.equals(id)){
        			ratings += 1;
        			rating_average = rating_average + rater.getRating(r);
        		}
    		}
    	}
    	return rating_average = rating_average/ratings;
    }
    
    private static HashMap<String, Integer> getNumberRatingsMap(ArrayList<Rater> ratersArray){
    	HashMap<String, Integer> myMap = new HashMap<String, Integer>();
    	
    	for (Rater rater : ratersArray){
    		ArrayList<String> items = rater.getItemsRated();
    		for (String i : items){
    			if (myMap.containsKey(i)){
        			myMap.put(i, myMap.get(i)+1);
        		}else{
        			myMap.put(i, 1);
        		}    			
    		}
    		
    	}
    	return myMap;
    }
    
    public static ArrayList<Rating> getAverageRatings(int minimalRaters){
    	//Filter allmovies = new TrueFilter();
    	//ArrayList<String> movies = MovieDatabase.filterBy(allmovies); // an array list that contains movie ids
    	ArrayList<Rater> myRaters = RaterDatabase.getRaters(); //myRaters
    	HashMap<String, Integer> ratingsMap = FourthRatings.getNumberRatingsMap(myRaters); // a map that contains movie id and number of ratings
    	ArrayList<Rating> ratings = new ArrayList<Rating>(); // a new array list of Ratings
    	
    	for (String r : ratingsMap.keySet()){
    		if (ratingsMap.get(r) >= minimalRaters){
    			double average = getAverageByID(r, minimalRaters);
    			Rating rating = new Rating(r, average);
    			ratings.add(rating); //an array that contains the movie id and the average of ratings whose minimal rater is as specified      			
    		}
    	}
    	return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    	ArrayList<Rater> myRaters = RaterDatabase.getRaters(); //myRaters
    	HashMap<String, Integer> ratingsMap = FourthRatings.getNumberRatingsMap(myRaters); // a map that contains movie id and number of ratings
    	ArrayList<Rating> ratings = new ArrayList<Rating>(); // a new array list of Ratings
    	
    	for (String m : movies){
    		if (ratingsMap.containsKey(m)){
    			if (ratingsMap.get(m) >= minimalRaters){
    				double average = FourthRatings.getAverageByID(m, minimalRaters);
    				Rating rating = new Rating(m, average);
    				ratings.add(rating); //an array that contains the movie id and the average of ratings
    			}
    		}
    	}
    	return ratings;
    }
    
    private Double translateFromScales(Double n){
    	return n-5;
    }
    
    private double dotProduct(Rater me, Rater r){
    	ArrayList<String> me_movies = me.getItemsRated();
    	ArrayList<String> r_movies = r.getItemsRated();
    	double closeness = 0.0;
    	
    	for (String me_movie : me_movies){
    		Double total_rating = 0.0;
    		if (r_movies.contains(me_movie)){
				int index = r_movies.indexOf(me_movie);
				total_rating = translateFromScales(me.getRating(me_movie)) * translateFromScales(r.getRating(r_movies.get(index)));
    		}
    		closeness = closeness + total_rating;
    	}
    	return closeness;
    }
    
//********GET SIMILARITIES BETWEEN THIS ID AND THE REST OF IDS********************************** 
    public ArrayList<Rating> getSimilarities(String id){ 
    	ArrayList<Rating> ratings = new ArrayList<Rating>(); //new array
    	ArrayList<Rater> raters = RaterDatabase.getRaters(); //all raters to evaluate
    	Rater main_rater = RaterDatabase.getRater(id); //rater to evaluate against all
    	
    	//if (main_rater != null){
	    	for (Rater rater : raters){
	    		if (!rater.getID().equals(id)){ //search similarities on other raters, not id
	    			double similarity = dotProduct(main_rater, rater);
	    			if (similarity > 0.0){
	    				Rating rating = new Rating(rater.getID(), similarity);
	    				ratings.add(rating);
	    			}
	    		}
	    	}
    //	}
    	
    	Collections.sort(ratings, Collections.reverseOrder());
    	
    	return ratings;
    }
    /*getRecommendations function, not used*/
    public static ArrayList<String> getRecommendations(String id, Integer minimalRaters){
    	ArrayList<String> list = new ArrayList<String>(); //new array
    	ArrayList<Rater> raters = RaterDatabase.getRaters(); //
    	/*
    	ArrayList<String> movies_watched = null;
    	
    	for (Rater rater : raters){
    		if (rater.getID().equals(id)){
    			movies_watched = rater.getItemsRated();
    			break;
    		}
    	}
    	
    	for (Rater rater : raters){
    		if (!rater.getID().equals(id)){ //don't search on the movies that I already watched
    			ArrayList<String> movies = rater.getItemsRated();
    			for (String movie : movies){ //iterate over the movies of this rater
    				if (!list.contains(movie)){ //don't add if already stored
    					if (getNumberRatingsMap(raters).get(movie) >= minimalRaters){ //if number of ratings is greater than the minimum
							if (!movies_watched.contains(movie)){ //dont add my movies
								list.add(movie);
							}
    					}
    				}
    			}
    		}
    	}
    	*/
    	HashMap<String, Integer> ratingsMap = FourthRatings.getNumberRatingsMap(raters);
    	
    	for (String r : ratingsMap.keySet()){
    		if (ratingsMap.get(r) >= minimalRaters){
    			list.add(r);
    			/*
    			double average = getAverageByID(r, minimalRaters);
    			Rating rating = new Rating(r, average);
    			ratings.add(rating); //an array that contains the movie id and the average of ratings whose minimal rater is as specified      			
    			*/
    		}
    	}
    	
    	return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, Integer numSimilarRaters, Integer minimalRaters){
    	ArrayList<Rating> list = new ArrayList<Rating>(); //new array
    	ArrayList<Rating> weights = getSimilarities(id); //raters with similar taste
    	HashMap<String, Integer> movies = getNumberRatingsMap(RaterDatabase.getRaters());//getRecommendations(id, minimalRaters); //movies to recommend
       	
       	if (numSimilarRaters > weights.size()){
       		list.add(new Rating("ERROR: SPECIFIED SIMILAR RATERS GREATER THAN RATERS",0.0));
       		return list;
       	}

    	for (String movieID : movies.keySet()){ //iterate over the top recommended movies
        		double weighted_average = 0.0;
        		Double nratings = 0.0;
        		for (int i=0;i<numSimilarRaters;i++){ //iterate over the top similar raters
        			double weighted_per_rater = 0.0;
        			Rating r = weights.get(i);
        			double similarity = r.getValue();
        			String rater_id = r.getItem();
        			Rater rater = RaterDatabase.getRater(rater_id);
        			ArrayList<String> movies_per_rater = rater.getItemsRated();
        			for (String mpr : movies_per_rater){
        				if (mpr.equals(movieID)){
        					double rating = rater.getRating(mpr); 
        					weighted_per_rater = similarity * rating;
        					nratings = nratings + 1;
        					break;
        				}
        			}
        			weighted_average = weighted_average + weighted_per_rater;
        		}
        		if (nratings >= minimalRaters){
        			list.add(new Rating (movieID, ((double) Math.round((weighted_average/nratings)*100))/100)); //operation to round to two decimal places
        		}
    	}

    	Collections.sort(list, Collections.reverseOrder());
    	
    	return list;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, Integer numSimilarRaters, Integer minimalRaters, Filter filterCriteria){
    	ArrayList<Rating> list = new ArrayList<Rating>(); //new array
    	ArrayList<Rating> weights = getSimilarities(id); //raters with similar taste
    	//ArrayList<String> movies = getRecommendations(id, minimalRaters); //movies to recommend
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
       	
       	if (numSimilarRaters > weights.size()){
       		list.add(new Rating("ERROR: SPECIFIED SIMILAR RATERS GREATER THAN RATERS",0.0));
       		return list;
       	}

    	for (String movieID : movies){ //iterate over the top recommended movies
    		double weighted_average = 0.0;
    		Double nratings = 0.0;
    		for (int i=0;i<numSimilarRaters;i++){ //iterate over the top similar raters
    			double weighted_per_rater = 0.0;
    			Rating r = weights.get(i);
    			String rater_id = r.getItem();
    			double similarity = r.getValue();
    			Rater rater = RaterDatabase.getRater(rater_id);
    			ArrayList<String> movies_per_rater = rater.getItemsRated();
    			for (String mpr : movies_per_rater){
    				if (mpr.equals(movieID)){
    					double rating = rater.getRating(mpr); 
    					weighted_per_rater = similarity * rating;
    					nratings = nratings + 1;
    					break;
    				}
    			}
    			weighted_average = weighted_average + weighted_per_rater;
    		}
    		if (nratings >= minimalRaters){
    			list.add(new Rating (movieID, ((double) Math.round((weighted_average/nratings)*100))/100)); //operation to round to two decimal places
    		}
    	}

    	Collections.sort(list, Collections.reverseOrder());
    	
    	return list;
    }
}
