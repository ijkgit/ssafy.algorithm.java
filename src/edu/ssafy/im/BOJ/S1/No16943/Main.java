package edu.ssafy.im.BOJ.S1.No16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int selected[];
	static boolean visited[];
	static List<Integer> arrA;
	static int B;
	static int ans = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = (st.nextToken());
		arrA = new ArrayList<>();
		B = Integer.parseInt(st.nextToken());
		int B_length = Integer.toString(B).length();

		selected = new int[Math.min(B_length, A.length())];
		visited = new boolean[A.length()];

		for (char c : A.toCharArray()) {
			arrA.add(Character.getNumericValue(c));
		}
		Collections.sort(arrA);
		if (B_length >= arrA.size()) {
			recursive(0, 0);
		}

		System.out.println(ans);
	}

	private static void recursive(int idx, int k) {
		if (idx == selected.length) {
			int cnt = 1;
			int num = 0;
			for (int i = selected.length - 1; i >= 0; i--) {

				num += selected[i] * cnt;
				cnt *= 10;
			}
			if (num < B && Integer.toString(num).length() == selected.length) {
//				System.out.println(Arrays.toString(selected));
				ans = num;
			}
			/*
			 * 원본 코드에서 테케
			 * 입력 : 1000 1000
			 * 출력 : 100
			 * 정답 : -1
			 * 이유 : selected 배열의 앞 인덱스의 value가 0일 경우에 대한 예외처리 안해줘서 틀림
			 */
			return;

		}
		for (int i = 0; i < arrA.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[idx] = arrA.get(i);
				recursive(idx + 1, k + 1);
				visited[i] = false;
			}

		}

	}

}
