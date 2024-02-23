package edu.ssafy.im.BOJ.Gold.G4.No17281;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private int N;
	private Integer[][] PLAYER;
	private static final int SIZE = 9;
	private int NOW = 0;
	private int SCORE = 0;
	private int OUT = 0;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		PLAYER = new Integer[N][SIZE];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < SIZE; j++) {
				PLAYER[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sol();
	}

	private void sol() {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int first = PLAYER[NOW][0];
		int cnt = 1;
		for (int i = 1; i < SIZE; i++) {
			if(cnt == 4) {
				queue.addFirst(PLAYER[NOW][0]);
				i--;
				cnt++;
			}
			if(PLAYER[NOW][i] == 0) {
				queue.addLast(0);
			} else if(PLAYER[NOW][i] != 0) {
				queue.addFirst(PLAYER[NOW][i]);
				cnt++;
			} 
		}
		
		System.out.println(queue);
//		int base = 0;
//		int count = 0;
//		boolean status = false;
//		while(!pq.isEmpty()) {
//			int order = pq.poll();
//			count++;
//			
//			if(order == 0) OUT++;
//			else if(order == 1) base += 1;
//			else if(order == 2) base += 2;
//			else if(order == 3) base += 3;
//			else if(base != 0 && order == 4) {
//				base = 0;
//				SCORE = (base/4) + (base%4);
//			} else {
//				SCORE++;
//			}
//			
//			if(OUT == 3) status = true;
//			
//		}
	}
}
