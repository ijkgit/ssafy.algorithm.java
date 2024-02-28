package edu.ssafy.im.BOJ.Gold.G4.No17471;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] num;
	private static ArrayList<ArrayList<Integer>> graph;
	private static int[] s;
	private static int[] r;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
			
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			for (int j = 0; j < E; j++) {
				int V = Integer.parseInt(st.nextToken()) - 1;
				graph.get(j).add(V);
				graph.get(V).add(j);
			}
		}
		
		sol();
	}

	private static void sol() {
		for (int i = 1; i < 2 / N; i++) {
			s = new int[i];
			r = new int[N-i];
			permutation(0, 0);
		}
	}
	
	private static boolean check(int k, int v, int[] a) {
		if (k == a.length) {
			return true;
		}
		
		for (int i = 0; i < a.length; i++) {
			int cur = graph.get(a[i]);
			
		}
	}

	private static void permutation(int k, int v) {
		if (k == s.length) {
			makeR();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((v & (1 << i)) == 0) {
				s[k] = i;
				permutation(k+1, v |= 1 << i);
			}
		}
	}

	private static void makeR() {
		int idx = 0;
		for (int i = 0; i < N; i++) {
			boolean f = false;
			for(int j : s) {
				if (i == j) {
					f = true;
					break;
				}
			}
			if (!f) r[idx++] = i;
		}
	}
}