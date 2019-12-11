import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, args[0]);
		map.put(2, args[1]);
		System.out.println(map.get(1) + " "+ map.get(2));
	}
}
