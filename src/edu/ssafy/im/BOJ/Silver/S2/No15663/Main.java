package edu.ssafy.im.BOJ.Silver.S2.No15663;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[] arr;
	private static int[] sel;
	private static Set<String> ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sel = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		
		ans = new LinkedHashSet<>();
		combination(0, 0);
		
		for (String a : ans) {
			sb.append(a).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void combination(int k, int v) {
		if (k == M) {
			StringBuilder sb = new StringBuilder();
			for(int s: sel) sb.append(s).append(" ");
			ans.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = arr[i];
				combination(k+1, v | 1 << i);
			}
		}
	}
}
