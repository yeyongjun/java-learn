package com.learn.alg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 3D接雨水
 * @author yeyongjun
 * @since 2025/1/3 14:18
 */
public class Trap2 {

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int i=0; i<m; i++) {
            for (int j=0; j < n; j++) {
                if (i==0 || i == m-1 || j==0 || j==n-1){
                    queue.offer(new int[]{i, j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[][] position = new int[][]{{0,1}, {0,-1}, {-1,0}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int[] pos: position) {
                int x = poll[0] + pos[0];
                int y = poll[1] + pos[1];
                if (x>=0 && x<m && y>=0 && y<n && !visit[x][y]) {
                    if (heightMap[x][y] < poll[2]) {
                        res += poll[2] - heightMap[x][y];
                        queue.offer(new int[]{x, y, poll[2]});
                    }else {
                        queue.offer(new int[]{x, y, heightMap[x][y]});
                    }
                    visit[x][y] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] heightMap = new int[][]{{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};
        int res = new Trap2().trapRainWater(heightMap);
        System.out.println(res);
    }
}
