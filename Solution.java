package a;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	private static int T;
	private static int N;
	private static int SIZE = 15;
	private static int[][] map;
	private static ArrayList<House> houseList;
	private static int ans;
	
	static class House implements Comparable<House> {
		int x, y, d;

		public House(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(House o) {
			return Integer.compare(this.d, o.d);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			houseList = new ArrayList<>();
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				houseList.add(new House(x, y, d));
			}
			
			Collections.sort(houseList);
			
			// 주유소 한개만 세운다.
			for (int x = -SIZE; x <= SIZE; x++) {
				for (int y = -SIZE; y <= SIZE; y++) {
					int sum = 0, cnt = 0;
					L:for (House h : houseList) {
						if (h.x == x && h.y == y) break L; // 집에는 못세워요
						int diff = Math.abs(x - h.x) + Math.abs(y - h.y);
						if (h.d < diff) break L; // 너 탈락
						else {
//							System.out.println(x + " " + y);
							sum += diff;
							cnt++;
						}
						if (cnt == houseList.size()) {
							ans = Math.min(ans, sum);
						}
					}
				}
			}
			
			// 주유소 두개 세운다.
			// 하나 가능하면 하나만 세우니까.. skip
			if (ans == Integer.MAX_VALUE) {
				// 첫번째 주유소입니당.
				for (int x = -SIZE; x <= SIZE; x++) {
					for (int y = -SIZE; y <= SIZE; y++) {
						
						// 두번째 주유소
						for (int x2 = -SIZE; x2 <= SIZE; x2++) {
							for (int y2 = -SIZE; y2 <= SIZE; y2++) {
								
								if (x == x2 && y == y2) continue; // 중복 처리
								
								int sum = 0, cnt = 0;
								L:for (House h : houseList) {
									int diff = Math.abs(x - h.x) + Math.abs(y - h.y);
									int diff2 = Math.abs(x2 - h.x) + Math.abs(y2 - h.y);
									
									if (h.d < diff && h.d < diff2) break L; // 너 탈락
									
									if (h.d >= diff && h.d >= diff2) { // 집이 주유소를 두개나 가지네요
										if (h.x == x && h.y == y) { // 알고보니 내집이 주유소였어요
											sum += diff2;
											cnt++;
										} else if (h.x == x2 && h.y == y2) { // 알고보니 내집이 주유소였어요
											sum += diff;
											cnt++;
										} else { // 둘 중에 가까운 주유소를 구해요
											sum += Math.min(diff, diff2);
											cnt++;
										}
									}
									else if (h.d >= diff) {
										if (!(h.x == x && h.y == y)) { // 집에는 못세워요
											sum += diff;
											cnt++;
										}
									} else if (h.d >= diff2) {
										if (!(h.x == x2 && h.y == y2)) { // 집에는 못세워요
											sum += diff2;
											cnt++;	
										}
									}
									
									if (cnt == houseList.size()) {
										ans = Math.min(ans, sum);
									}
								}
							}
						}
					}
				}
				
			}
			
			sb.append("#").append(t).append(" ").append(ans = ans == Integer.MAX_VALUE ? -1 : ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
