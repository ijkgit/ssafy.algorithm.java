package edu.ssafy.im.BOJ.S2.No12891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int s, p, ans = 0;
	static int arr[];
	static int c[] = new int[4];
	static int r[] = new int[4];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		input = br.readLine();
		arr = new int[s];
		for (int i = 0; i < s; i++) {
			char c = input.charAt(i);
			switch (c) {
			case 'A':
				arr[i] = 0;
				break;
			case 'C':
				arr[i] = 1;
				break;
			case 'G':
				arr[i] = 2;
				break;
			case 'T':
				arr[i] = 3;
				break;
			}
		}

		input = br.readLine();
		st = new StringTokenizer(input);
		for (int i = 0; i < 4; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < p; i++) {
			r[arr[i]]++;
		}

		int ans = 0;
		int start = 0, end = 0 + p - 1;
		for (int i = 0; i < s - p + 1; i++) {
			System.out.println(Arrays.toString(r));
			for (int j = 0; j < 4; j++) {
				if (r[j] != c[j]) {
					break;
				} else if (j == 3) {
					ans++;
				}
			}
			r[arr[start++]]--;
			r[arr[++end]]++;
			System.out.println(Arrays.toString(r));
		}
		sb.append(ans);
		System.out.println(sb);
	}
}
