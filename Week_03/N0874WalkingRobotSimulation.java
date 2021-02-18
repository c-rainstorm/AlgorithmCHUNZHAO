package s0800;

import java.util.HashSet;
import java.util.Set;

/**
 * @author traceless
 */
public class N0874WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add((toLong(obstacle[0], obstacle[1])));
        }

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0, ans = 0;
        for (int cmd : commands) {
            if (cmd == -2) di = (di + 3) % 4;
            else if (cmd == -1) di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    if (!obstacleSet.contains(toLong(nx, ny))) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }

        return ans;
    }

    private Long toLong(int x, int y) {
        return (((long) x + 30000) << 16) + y + 30000;
    }
}
