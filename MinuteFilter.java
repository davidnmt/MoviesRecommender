
public class MinuteFilter implements Filter {
	private int minminutes;
	private int maxminutes;

	public MinuteFilter(int min, int max){
		minminutes = min;
		maxminutes = max;
	}
	
	@Override
	public boolean satisfies(String id){
		return (minminutes <= MovieDatabase.getMinutes(id) && MovieDatabase.getMinutes(id) <= maxminutes);
	}
}
