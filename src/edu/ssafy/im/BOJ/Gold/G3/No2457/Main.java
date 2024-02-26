package edu.ssafy.im.BOJ.Gold.G3.No2457;
// https://www.acmicpc.net/board/view/86573
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	private ArrayList<Flower> flowerList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private int N;
	private int ans = 0;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		flowerList = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());

			flowerList.add(new Flower(sm * 100 + sd, em * 100 + ed));
		}
		sol();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol() {
		Collections.sort(flowerList);

		int start = 301;
		int end = 1201;
		int now = 0;
		int idx = 0;

		while(start < end) {
			boolean flag = false;

			for (int i = idx; i < N; i++) {
				if (flowerList.get(i).s > start) break;
				if (flowerList.get(i).e > now) {
					flag = true;
					now = flowerList.get(i).e;
					idx = i + 1;
				}
			}

			if(flag) {
				start = now;
				ans++;
			} else {
				break;
			}
		}

		if(now < end) ans = 0;
	}

	class Flower implements Comparable<Flower> {
		int s, e;

		public Flower(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Flower o) {
			if(this.s < o.s) return -1;
			else if(this.s == o.s) {
				if(this.e > o.e) return -1;
				else if(this.e == o.e) return 0;
				else return 1;
			} else return 1;
		}
	}
}
