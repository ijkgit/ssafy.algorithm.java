package edu.ssafy.im.BOJ.Silver.S3.No15651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] sel;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		sel = new int[m];
		visited = new boolean[m];
		recursive(0, 1);
		System.out.print(sb);
	}
	private static void recursive(int i, int k) {
		// basis part
		if (i == m) {
			for(int s: sel) {
				sb.append(s).append(" ");
			}
			sb.append("\n");
			return;
		}
		if (k > n) {
			return;
		}
		
		// inductive part
		
		sel[i] = k;
		visited[i] = true;
		if(!visited[i])
			recursive(i+1, k);
		visited[i] = false;
		recursive(i, k+1);
		
	}
}
