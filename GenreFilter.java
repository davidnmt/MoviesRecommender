
public class GenreFilter implements Filter {
	private String mygenre;
	
	public GenreFilter(String genre){
		mygenre = genre;
	}
	
	@Override
	public boolean satisfies(String id){
		return MovieDatabase.getGenres(id).contains(mygenre);
	}
}
