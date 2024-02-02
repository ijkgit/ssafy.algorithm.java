package edu.ssafy.im.BOJ.Silver.S3.No15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] sel;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		sel = new int[m];
		recursion(0);
		
		System.out.print(sb);
	}
	
	private static void recursion(int k) {
		// basis part
		if (k == m) {
			for (int s : sel) {
				sb.append(s).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// inductive part
		for (int i = 0; i < n; i++) {
			sel[k] = i+1;
			recursion(k+1);
		}
	}
}
