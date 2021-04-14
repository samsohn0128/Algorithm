package Promgrammers;

public class 신규아이디추천_KAKAO1 {
	public String solution(String new_id) {
		// 1
		String result = new_id.toLowerCase();

		// 2
		new_id = result;
		result = "";
		for (int i = 0; i < new_id.length(); i++) {
			if ('a' <= new_id.charAt(i) && new_id.charAt(i) <= 'z') {
				result += new_id.charAt(i);
			} else if ('0' <= new_id.charAt(i) && new_id.charAt(i) <= '9') {
				result += new_id.charAt(i);
			} else if (new_id.charAt(i) == '-' || new_id.charAt(i) == '_' || new_id.charAt(i) == '.') {
				result += new_id.charAt(i);
			}
		}

		// 3
		new_id = result;
		result = "";
		result += new_id.charAt(0);
		for (int i = 1; i < new_id.length(); i++) {
			if (result.charAt(result.length() - 1) != '.') {
				result += new_id.charAt(i);
			} else if (new_id.charAt(i) != '.') {
				result += new_id.charAt(i);
			}
		}

		// 4
		new_id = result;
		result = "";
		if (!new_id.isEmpty()) {
			if (new_id.length() > 1) {
				if (new_id.charAt(0) == '.' && new_id.charAt(new_id.length() - 1) == '.') {
					result = new_id.substring(1, new_id.length() - 1);
				} else if (new_id.charAt(0) == '.') {
					result = new_id.substring(1, new_id.length());
				} else if (new_id.charAt(new_id.length() - 1) == '.') {
					result = new_id.substring(0, new_id.length() - 1);
				} else {
					result = new_id;
				}
			} else {
				if (new_id.charAt(0) != '.')
					result += new_id.charAt(0);
			}
		}

		// 5
		new_id = result;
		result = "";
		if (new_id.isEmpty()) {
			new_id += 'a';
		}

		// 6
		if (new_id.length() >= 16) {
			result = new_id.substring(0, 15);
			if (result.charAt(14) == '.') {
				result = result.substring(0, 14);
			}
		} else {
			result = new_id;
		}

		// 7
		new_id = result;
		result = "";
		while (new_id.length() <= 2) {
			new_id += new_id.charAt(new_id.length() - 1);
		}

		return new_id;
	}
}