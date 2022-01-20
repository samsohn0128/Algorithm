package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_B1976 {
    static int N, M;
    static int roots[];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        roots = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int adj = Integer.parseInt(st.nextToken());
                if (adj == 1) {
                    union(i, j);
                }
            }
        }

        boolean answer = true;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int travel[] = new int[M];
        for (int i = 0; i < M; i++) {
            travel[i] = Integer.parseInt(st.nextToken());
        }
        int root = find(travel[0]);
        for (int i = 1; i < M; i++) {
            if (root != find(travel[i])) {
                answer = false;
                break;
            }
        }
        if (answer)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static int find(int num) {
        if (roots[num] == num)
            return num;
        return roots[num] = find(roots[num]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB)
            roots[rootB] = rootA;
    }
}