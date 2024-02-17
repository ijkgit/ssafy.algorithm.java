package edu.ssafy.im.SWEA.D5.No7206;

import java.io.*;
import java.util.*;

public class Solution_fail {
    private String num;
    private int[] sel;
    private ArrayList<Integer> numList;
    private int ans;
    private Map<String, Integer> map;
    public static void main(String[] args) throws IOException {
        new Solution_fail().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            num = br.readLine();
            ans = Integer.MIN_VALUE;
            map = new HashMap<>();
            sol();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        if (num.length() > 1) {
            setPermutation(num, 0);
        } else {
            ans = 0;
        }
    }

    // 하나의 숫자에 대해, 터치할 횟수를 정하여 어느 부분을 터치할지 결정한다.
    private void setPermutation(String num, int cnt) {
//        System.out.println(num);
        for (int i = 1; i < num.length(); i++) {
            int[] sel = new int[i];
            permutation(0, 0, num, cnt, sel);
        }
    }


    // 터치할 부분을 뽑아주는 조합 메소드
    private void permutation(int k, int v, String num, int cnt, int[] sel) {
        if(k == sel.length) {
//            System.out.println(Arrays.toString(sel));
            int res = makeNum(num, sel);
            cnt++;
//            System.out.println("cnt : " + cnt);
            if(res != -1) { // 10 이상일 경우 다시 스플릿
                if(map.containsKey(num)) {
                    cnt += map.get(num);
                    ans = Math.max(ans, cnt);
                    return;
                } else {
                    setPermutation(String.valueOf(res), cnt);
                }
            } else {
                ans = Math.max(ans, cnt);
                if(!map.containsKey(num)) map.put(num, 1);
                System.out.println(map);
            }
            return;
        }

        for (int i = 0; i < num.length()-1; i++) {
            if((v & (1 << i)) == 0) {
                sel[k] = i+1;
                permutation(k+1, v |= 1 << i, num, cnt, sel);
            }
        }
    }

    // 터치한 부분에 따라 숫자를 생성해주는 메소드
    // sel 배열에는 터치된 숫자 사이 중 오른쪽 숫자의 인덱스를 담고 있다.
    // 따라서, 슬라이싱 중 첫번째와 끝부분의 숫자는 직접 슬라이싱 해줘야한다.
    private int makeNum(String num, int[] sel) {
        numList = new ArrayList<>();
        numList.add(Integer.parseInt(num.substring(0, sel[0])));
        if (sel.length > 1) {
            for (int i = 0; i < sel.length-1; i++) {
                numList.add(Integer.parseInt(num.substring(sel[i], sel[i+1])));
            }
        }
        numList.add(Integer.parseInt(num.substring(sel[sel.length-1], num.length())));
//        System.out.println(numList.toString());

        int sum = 1;
        for (int n: numList) {
            sum *= n;
        }
        if (sum < 10) {
            return -1;
        }
        return sum;
    }
}
