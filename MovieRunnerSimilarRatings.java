import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	
	public void printAverageRatings(){
		String moviefile = "ratedmovies_short.csv";
		String raterfile = "ratings_short.csv";
		
		FourthRatings.initializeRaters(raterfile);
		FourthRatings.initializeMovies(moviefile);
		
		//-a
		//printing the total number of raters and movies in the files:
		System.out.println("The total raters are: " + RaterDatabase.size());
		
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//-b
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 3; //minimal ratings
		ArrayList<Rating> averages = FourthRatings.getAverageRatings(minimalRatings);
		
		//printing the number of rating movies:
		System.out.println("Number of movies with ratings: " + averages.size());
		
		//sorting by highest rating:
		Collections.sort(averages);
		
		//printing them:
		for (Rating rating : averages){
			String title_movie = MovieDatabase.getTitle(rating.getItem());
			System.out.println(rating.getValue() + " " + title_movie);
		}
		
	}
	
	public void printAverageRatingByYearAfterAndGenre(){
		String moviefile = "ratedmoviesfull.csv";
		String raterfile = "data/ratings.csv";		
		
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);
		System.out.println("The total raters are: " + third.getRaterSize());
		MovieDatabase.initialize(moviefile);
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 8; //minimal ratings
		AllFilters criteria = new AllFilters(); //filter
		Filter f1 = new YearAfterFilter(1990);
		Filter f2 = new GenreFilter("Drama");
		criteria.addFilter(f1);
		criteria.addFilter(f2);
		ArrayList<Rating> averages = third.getAverageRatingsByFilter(minimalRatings, criteria);
		
		//printing the number of rating movies:
		System.out.println("Number of movies found: " + averages.size());

		//sorting by highest rating:
		Collections.sort(averages);

		//printing them:
		for (Rating rating : averages){
			String title_movie = MovieDatabase.getTitle(rating.getItem());
			String genres = MovieDatabase.getGenres(rating.getItem());
			System.out.println(rating.getValue() + " " + title_movie);
			System.out.println("\t" + genres);
		}		
	}
	
	public void printSimilarRatings(){
		FourthRatings fourth = new FourthRatings();
		
		FourthRatings.initializeMovies("ratedmoviesfull.csv");
		
		FourthRatings.initializeRaters("ratings.csv");
		
		ArrayList<Rating> ratings = fourth.getSimilarRatings("337", 10, 3);
		
		for (Rating rating : ratings){
			System.out.println(rating.getItem() + " " + rating.getValue());
		}
	}
	
	public void printSimilarRatingsByGenre(){
		FourthRatings fourth = new FourthRatings();
		
		FourthRatings.initializeMovies("ratedmoviesfull.csv");
		
		FourthRatings.initializeRaters("ratings.csv");
		
		Filter criteria = new GenreFilter("Mystery");
		ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("964", 20, 5, criteria);
		
		for (Rating rating : ratings){
			System.out.println(rating.getItem() + " " + rating.getValue());
		}		
	}

	public void printSimilarRatingsByDirector(){
		FourthRatings fourth = new FourthRatings();
		
		FourthRatings.initializeMovies("ratedmoviesfull.csv");
		
		FourthRatings.initializeRaters("ratings.csv");
		
		Filter criteria = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"); //filter
		ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("120", 10, 2, criteria);
		
		for (Rating rating : ratings){
			System.out.println(rating.getItem() + " " + rating.getValue());
		}
	}
	
	public void printSimilarRatingsByGenreAndMinutes(){
		FourthRatings fourth = new FourthRatings();
		
		FourthRatings.initializeMovies("ratedmoviesfull.csv");
		
		FourthRatings.initializeRaters("ratings.csv");
		
		AllFilters criteria = new AllFilters(); //filter
		Filter f1 = new MinuteFilter(80, 160);
		Filter f2 = new GenreFilter("Adventure");
		criteria.addFilter(f1);
		criteria.addFilter(f2);
		ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("65", 10, 3, criteria);
		
		for (Rating rating : ratings){
			System.out.println(rating.getItem() + " " + rating.getValue());
		}
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes(){
		FourthRatings fourth = new FourthRatings();
		
		FourthRatings.initializeMovies("ratedmoviesfull.csv");
		
		FourthRatings.initializeRaters("ratings.csv");
		
		AllFilters criteria = new AllFilters(); //filter
		Filter f1 = new YearAfterFilter(1975);
		Filter f2 = new MinuteFilter(70, 200);
		criteria.addFilter(f1);
		criteria.addFilter(f2);
		ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("314", 10, 5, criteria);
		
		for (Rating rating : ratings){
			System.out.println(rating.getItem() + " " + rating.getValue());
		}		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieRunnerSimilarRatings runner = new MovieRunnerSimilarRatings();
		
		//runner.printSimilarRatings();
		
		//runner.printSimilarRatingsByGenre();
		
		//runner.printSimilarRatingsByDirector();
		
		//runner.printSimilarRatingsByGenreAndMinutes();
		
		runner.printSimilarRatingsByYearAfterAndMinutes();
	}

}
