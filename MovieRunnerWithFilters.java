import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

	public void printAverageRatings(){
		String moviefile = "ratedmovies_short.csv";
		String raterfile = "data/ratings_short.csv";
		
		//-a
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);

		System.out.println("The total raters are: " + third.getRaterSize());
		
		MovieDatabase.initialize(moviefile);
		
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//-b
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 3; //minimal ratings
		ArrayList<Rating> averages = third.getAverageRatings(minimalRatings);
		
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
	
	public void printAverageRatingsByYear(){
		String moviefile = "ratedmoviesfull.csv";
		String raterfile = "data/ratings.csv";
		
		//-a
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);
		System.out.println("The total raters are: " + third.getRaterSize());
		MovieDatabase.initialize(moviefile);
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//-b
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 20; //minimal ratings
		Filter criteria = new YearAfterFilter(2000); //filter
		ArrayList<Rating> averages = third.getAverageRatingsByFilter(minimalRatings, criteria);
		
		//printing the number of rating movies:
		System.out.println("Number of movies found: " + averages.size());
		
		//sorting by highest rating:
		Collections.sort(averages);
		
		//printing them:
		for (Rating rating : averages){
			String title_movie = MovieDatabase.getTitle(rating.getItem());
			int year_movie = MovieDatabase.getYear(rating.getItem());
			System.out.println(rating.getValue() + " " + year_movie + " " + title_movie);
		}
	}
	
	public void printAverageRatingsByGenre() throws IOException{
		String moviefile = "ratedmoviesfull.csv";
		String raterfile = "data/ratings.csv";
		
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);
		System.out.println("The total raters are: " + third.getRaterSize());
		MovieDatabase.initialize(moviefile);
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 20; //minimal ratings
		Filter criteria = new GenreFilter("Comedy"); //filter
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

	public void printAverageRatingsByMinutes(){
		String moviefile = "ratedmoviesfull.csv";
		String raterfile = "data/ratings.csv";
		
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);
		System.out.println("The total raters are: " + third.getRaterSize());
		MovieDatabase.initialize(moviefile);
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 5; //minimal ratings
		Filter criteria = new MinuteFilter(105, 135); //filter
		ArrayList<Rating> averages = third.getAverageRatingsByFilter(minimalRatings, criteria);

		//printing the number of rating movies:
		System.out.println("Number of movies found: " + averages.size());

		//sorting by highest rating:
		Collections.sort(averages);

		//printing them:
		for (Rating rating : averages){
			String title_movie = MovieDatabase.getTitle(rating.getItem());
			String genres = MovieDatabase.getGenres(rating.getItem());
			int time_movie = MovieDatabase.getMinutes(rating.getItem());
			System.out.println(rating.getValue() + " Time: " + time_movie + " " + title_movie);
		}
	}
	
	public void printAverageRatingsByDirector(){
		String moviefile = "ratedmoviesfull.csv";
		String raterfile = "data/ratings.csv";
		
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);
		System.out.println("The total raters are: " + third.getRaterSize());
		MovieDatabase.initialize(moviefile);
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 4; //minimal ratings
		Filter criteria = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"); //filter
		ArrayList<Rating> averages = third.getAverageRatingsByFilter(minimalRatings, criteria);

		//printing the number of rating movies:
		System.out.println("Number of movies found: " + averages.size());

		//sorting by highest rating:
		Collections.sort(averages);

		//printing them:
		for (Rating rating : averages){
			String title_movie = MovieDatabase.getTitle(rating.getItem());
			String genres = MovieDatabase.getGenres(rating.getItem());
			String directors_movie = MovieDatabase.getDirector(rating.getItem());
			System.out.println(rating.getValue() + " " + title_movie);
			System.out.println("\t" + directors_movie);
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

	public void printAverageRatingsByDirectorsAndMinutes(){
		String moviefile = "ratedmoviesfull.csv";
		String raterfile = "data/ratings.csv";		
		
		//printing the total number of raters and movies in the files:
		ThirdRatings third = new ThirdRatings(raterfile);
		System.out.println("The total raters are: " + third.getRaterSize());
		MovieDatabase.initialize(moviefile);
		System.out.println("The total movies are: " + MovieDatabase.size());
		
		//loading the list of averages whose minimal ratings is as specified:
		int minimalRatings = 3; //minimal ratings
		AllFilters criteria = new AllFilters(); //filter
		Filter f1 = new MinuteFilter(90, 180);
		Filter f2 = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
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
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MovieRunnerWithFilters runner = new MovieRunnerWithFilters();
		//runner.printAverageRatings();
		/*runner.printAverageRatingsByYear();
		//runner.printAverageRatingsByGenre();
		//runner.printAverageRatingsByMinutes();
		//runner.printAverageRatingsByDirector();
		//runner.printAverageRatingByYearAfterAndGenre();*/
		runner.printAverageRatingsByDirectorsAndMinutes();
	}

}
