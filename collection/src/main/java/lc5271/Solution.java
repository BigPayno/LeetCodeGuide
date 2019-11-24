package lc5271;

/**
 * @author payno
 * @date 2019/11/24 14:25
 * @description
 */
public class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        if (points == null || points.length <= 1) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < points.length; i++) {
            int endX = points[i][0];
            int endY = points[i][1];
            int startX = points[i - 1][0];
            int startY = points[i - 1][1];
            res += Math.max(Math.abs(endX - startX), Math.abs(endY - startY));
        }
        return res;
    }
}
