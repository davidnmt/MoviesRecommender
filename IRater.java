import java.util.ArrayList;

/**
 * 
 */

/**
 * @author David Martín-Tereso
 *
 */
public interface IRater {
	public void addRating(String item, double rating);
	public boolean hasRating(String item);
	public String getID();
	public int numRatings();
	public ArrayList<String> getItemsRated();
}