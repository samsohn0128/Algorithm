import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) throws Exception {
		Map<String, Integer> m = new HashMap<>();
		String push = "asdf";
		m.put(push, 1);
		System.out.println(m.size());
		m.forEach((k,v)->{
			System.out.println("key : "+k +" value : "+v);
		});
		String push2 = "asdf";
		m.put(push2, 2);
		System.out.println(m.size());
		m.forEach((k,v)->{
			System.out.println("key : "+k +" value : "+v);
		});
	}
}
