import edu.duke.*;
import java.io.IOException;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> moviearray = new ArrayList<Movie>();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            Movie movie = new Movie(record.get("id"), record.get("title"), 
            record.get("year"), record.get("genre"), record.get("director"), 
            record.get("country"), record.get("poster"), 
            Integer.parseInt(record.get("minutes")));
            moviearray.add(movie);
        }
        
        return moviearray;
    }
    
    public void printMovies(ArrayList<Movie> moviearray){
        /*
    	int nmovies = 0;
        for (Movie movie : moviearray){
            //System.out.println(movie);
            nmovies +=1;
        }
        */
        System.out.println("Nº of movies: " + moviearray.size());    
    }
    
    public void printOnlyComedy(ArrayList<Movie> moviearray){
        int nmovies = 0;
        for (Movie movie : moviearray){
            if (movie.getGenres().contains("Comedy")){
                //System.out.println(movie);
                nmovies +=1;
            }
        }
        System.out.println("NÂº of movies that include comedy: " + nmovies);
    }
    
    public void printGreater150(ArrayList<Movie> moviearray){
        int nmovies = 0;
        for (Movie movie : moviearray){
            if (movie.getMinutes() > 150){
                //System.out.println(movie);
                nmovies +=1;
            }
        }
        System.out.println("NÂº of movies greater than 150 minutes: " + nmovies);
    }
    
    public HashMap<String, Integer> loadMap(ArrayList<Movie> moviearray){
        HashMap<String, Integer> directormap = new HashMap<String, Integer>();
        for (Movie movie : moviearray){
            if (directormap.containsKey(movie.getDirector())){
                int valuehash = directormap.get(movie.getDirector())+1;
                directormap.put(movie.getDirector(), valuehash);
            }
            else{
                directormap.put(movie.getDirector(), 1);
            }
        }
        return directormap;
    }
    
    public void printDirector(ArrayList<Movie> moviearray){
        HashMap<String, Integer> directormap = loadMap(moviearray);
        int most = 0;
        for (Integer v : directormap.values()){
            if (most < v){
                most = v;
            }
        }
        ArrayList<String> directorarray = new ArrayList<String>();
        for (String k : directormap.keySet()){
            int nmovies = directormap.get(k);
            if (nmovies == most){
                directorarray.add(k);
            }
        }
        System.out.println("Maximum nº of movies by director: " + most);
        System.out.println("Director(s) who directed more movies: " + directorarray);
    }
    //test about Movies**********************************************************
    public void testLoadMovies(){
        String filemovies = "data/ratedmoviesfull.csv";
        ArrayList<Movie> mainarray = loadMovies(filemovies);
        /*Print each movie and the number of movies*/
        printMovies(mainarray);
        System.out.println("******************");
        /*Determine how many movies include the Comedy genre*/
        printOnlyComedy(mainarray);
        System.out.println("******************");
        /*Determine how many movies are greater than 150 minutes in length*/
        printGreater150(mainarray);
        System.out.println("******************");
        /*Determine the maximum number of movies by any director*/
        printDirector(mainarray);
        System.out.println("******************");
    }
    //***************************************************************************
    public ArrayList<EfficientRater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);        
        ArrayList<EfficientRater> raterArray = new ArrayList<EfficientRater>();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
        	EfficientRater eRater = new EfficientRater(record.get("rater_id"));
        	Rating rating = new Rating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
        	eRater.addRating(record.get("movie_id"), rating);        	
        	raterArray.add(eRater);
        }
        return raterArray;
    }
    
    public HashMap<String,Integer> loadMapRater(ArrayList<EfficientRater> raterarray){
        HashMap<String, Integer> idmap = new HashMap<String, Integer>();
        for (EfficientRater rater : raterarray){
            String id = rater.getID();
            if (idmap.containsKey(id)){
                int valuemap = idmap.get(id)+1;
                idmap.put(id, valuemap);
            }else {
                idmap.put(id, 1);
            }
        }
        return idmap;
    }
    
    public void printInfoAboutRaters(ArrayList<EfficientRater> raterArray){
        HashMap<String, Integer> raterMap = loadMapRater(raterArray);
        /*
        for (String s : raterMap.keySet()){
            int ratingsByRater=raterMap.get(s);
            System.out.println(s + " " + ratingsByRater);
            for (Rater rater : raterArray){
                if (rater.getID().equals(s)){
                    ArrayList<String> item = rater.getItemsRated();
                    String movieid = item.get(0);
                    System.out.println(movieid + " " + rater.getRating(movieid));
                }
            }
        }
        */
        System.out.println("Total number of raters: " + raterMap.size());        
    }
    
    public void printRatingsByRater(ArrayList<EfficientRater> raterArray, String rater_id){
        Integer nratings = 0;
        for (EfficientRater rater : raterArray){
            if (rater.getID().equals(rater_id)){
                nratings += 1;
            }
        }
        System.out.println("The rater whose id is: " + rater_id + " has: " +
        nratings + " ratings");
    }
    
    public HashMap<String, Integer> loadRatingsMap(ArrayList<EfficientRater> raterArray){
        HashMap<String, Integer> ratingsMap = new HashMap<String, Integer>();
        for (EfficientRater rater : raterArray){
            String rater_id = rater.getID();
            if (ratingsMap.containsKey(rater_id)){
                int value_ratings = ratingsMap.get(rater_id) + 1;
                ratingsMap.put(rater.getID(), value_ratings);
            } else{
                ratingsMap.put(rater.getID(), 1);
            }
        }
        return ratingsMap;
    }
    
    public void printMaximumRatingsByRater(ArrayList<EfficientRater> raterArray){
        HashMap<String, Integer> ratingsMap = loadRatingsMap(raterArray);
        ArrayList<String> ratersArray = new ArrayList<String>();
        int maximum_nrating = 0;
        for (Integer v : ratingsMap.values()){
            if (maximum_nrating < v){
                maximum_nrating = v;
            }
        }
        for (String k : ratingsMap.keySet()){
            int value = ratingsMap.get(k);
            if (value == maximum_nrating){
                ratersArray.add(k);
            }
        }
        System.out.println("The maximum number of ratings is: " + maximum_nrating +
        " for the rater whose id is: " + ratersArray);
    }
    
    public HashMap<String, Integer> loadMoviesMap(ArrayList<EfficientRater> raterArray){
        HashMap<String, Integer> moviesMap = new HashMap<String, Integer>();
        for (EfficientRater rater : raterArray){
            ArrayList<String> movies = rater.getItemsRated();
            for (String m : movies){
                if (moviesMap.containsKey(m)){
                    int value = moviesMap.get(m) + 1;
                    moviesMap.put(m, value);
                }else{
                    moviesMap.put(m, 1);
                }
            }
        }
        return moviesMap;
    }
    
    public void printRatingsByMovie(ArrayList<EfficientRater> raterArray, String movie_id){
        HashMap<String, Integer> moviesMap = loadMoviesMap(raterArray);
        int ratings_bymovie = 0;
        for (String s : moviesMap.keySet()){
            if (s.equals(movie_id)){
                ratings_bymovie = moviesMap.get(s);
            }
        }
        System.out.println("The movie whose id is: " + movie_id + " has: " +
        ratings_bymovie + " ratings");
    }
    
    public void printUniqueMovies(ArrayList<EfficientRater> raterArray){
        ArrayList<String> movieList = new ArrayList<String>();
        int umovies = 0;
        for (EfficientRater rater : raterArray){
            ArrayList<String> ratings = rater.getItemsRated();
            String movie_id = ratings.get(0);
            if (!(movieList.contains(movie_id))){
                movieList.add(movie_id);
                umovies += 1;
            }
        }
        System.out.println("These are unique movies that have been rated: ");
        System.out.println(movieList);
        System.out.println("Total: " + umovies + " unique movies");
    }
    //******************************************************************************
    public void testLoadRaters(){
        String fileraters = "data/ratings_short.csv";
        System.out.println("\f File reading: " + fileraters);
        System.out.println("*************");
        
        ArrayList<EfficientRater> mainarray = loadRaters(fileraters);
        
        /*Determine rater and nº of movies. For each rater, movies and ratings. Total.*/
        printInfoAboutRaters(mainarray);
        System.out.println("*************");
        
        /*Determine the nº of ratings for a specified rater*/
        printRatingsByRater(mainarray, "193");
        System.out.println("*************");
        
        /*Determine the rater whose ratings are higher*/
        printMaximumRatingsByRater(mainarray);
        System.out.println("*************");
        
        /*Determine the number of ratings a particular movie has*/
        printRatingsByMovie(mainarray, "1798709");
        System.out.println("*************");
        
        /*Determine how many different movies have been rated by all these raters*/
        printUniqueMovies(mainarray);
        System.out.println("*************");
    }
    //********************************************************************************
    public static void main(String[] args) throws IOException{
    	FirstRatings first = new FirstRatings();
    	first.testLoadRaters();
    }
}
