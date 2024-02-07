package edu.ssafy.im.BOJ.Silver.S1.No11286;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {
	int n;
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(
				(a, b) -> {
					int diff = Math.abs(a) - Math.abs(b);
					if(diff == 0) return a-b;
					else return diff;
			});
		
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			
		}
	}
}
