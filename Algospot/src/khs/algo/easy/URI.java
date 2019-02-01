package khs.algo.easy;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

// https://algospot.com/judge/problem/read/URI
public class URI {
	private static Map<String, String> encodeMap;
	static {
		encodeMap = new LinkedHashMap<>();
		encodeMap.put("%20", " ");
		encodeMap.put("%21", "!");
		// $가 The end of a line (boundary matcher) 이므로 escape 처리. java.util.Pattern 주석 참고
		encodeMap.put("%24", "\\$");
		
		encodeMap.put("%28", "(");
		encodeMap.put("%29", ")");
		encodeMap.put("%2a", "*");
		
		// %2520 같은 경우에 %20으로 나오기 위해 %25를 맨 뒤로 배치.
		encodeMap.put("%25", "%");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			String encodedURI = sc.next();
			
			String decodedURI = decode(encodedURI);
			System.out.println(decodedURI);
		}
		
		sc.close();
	}
	
	private static String decode(String encodedURI) {
		String decodedURI = encodedURI;
		
		Iterator<String> iter = encodeMap.keySet().iterator();
		while(iter.hasNext()) {
			String encodedString = iter.next();
			decodedURI = decodedURI.replaceAll(encodedString, encodeMap.get(encodedString));
		}
		
		return decodedURI;
	}
}
