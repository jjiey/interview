package interview.leetcode.medium;

/**
 * Word Search
 * 单词搜索
 */
public class Solution79 {

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        String words[] = {"ABCCED", "SEE", "ABCB"};
        Solution79 lc = new Solution79();
        for (String word : words) {
            boolean exist = lc.exist(board, word);
            System.out.println(word + " : " + exist);
        }
    }

    /**
     * 四个方向, 遍历顺序为: 上右下左
     *        x-1,y
     * x,y-1  x,y    x,y+1
     *        x+1,y
     */
    private int[][] direction = {{-1, 0},
                                 {0, 1},
                                 {1, 0},
                                 {0, -1}};
    // board上有多少行
    private int rows;
    // board上有多少列
    private int cols;
    // 标识已经访问过
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (searchWord(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchWord(char[][] board, String word, int startX, int startY, int index) {
        // 终止条件, 当遍历到word的最后一个字符时, 直接返回和startX, startY处的字符是否相等
        if (index == word.length() - 1) {
            return board[startX][startY] == word.charAt(index);
        }
        if (board[startX][startY] == word.charAt(index)){
            visited[startX][startY] = true;
//            System.out.println(startX + "," + startY);
            // 四个方向, 遍历顺序为: 上右下左
            for (int i = 0; i < direction.length; i++){
                int newX = startX + direction[i][0];
                int newY = startY + direction[i][1];
                if (inArea(newX, newY) && !visited[newX][newY] && searchWord(board, word, newX, newY, index + 1)) {
                    return true;
                }
            }
            // 如果找到了就在上边直接返回了, 当没有找到才会走到这里来, 开启下一次寻找之前复原标记
            visited[startX][startY] = false;
//            System.out.println(startX + ",,," + startY);
        }
        return false;
    }

    /**
     * 判断(x, y)坐标是否在board内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

}
