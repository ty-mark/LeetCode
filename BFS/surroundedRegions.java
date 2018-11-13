/*
    Given a 2D board containing 'X' and 'O' (the letter O), 
    capture all regions surrounded by 'X'.

    A region is captured by flipping all 'O's into 'X's in that surrounded region.

    Example:

    X X X X
    X O O X
    X X O X
    X O X X
    After running your function, the board should be:

    X X X X
    X X X X
    X X X X
    X O X X
    Explanation:

    Surrounded regions shouldn’t be on the border, which means that 
    any 'O' on the border of the board are not flipped to 'X'. Any 'O' that 
    is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
    Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/

// ----------1------------
// DFS seems simpler and faster
class Solution {
    public void solve(char[][] board) {
        if (board.length < 3) return;
        int R = board.length, C = board[0].length;
        for (int row = 0; row < R; row++){
            if (board[row][0] == 'O') {
                helper(row, 0, board);
            }
            if (board[row][C - 1] == 'O') {
                helper(row, C - 1, board);
            }
        }
        for (int col = 1; col < C - 1; col++){
            if (board[0][col] == 'O') {
                helper(0, col, board);
            }
            if (board[R - 1][col] == 'O') {
                helper(R - 1, col, board);
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '+') board[i][j] = 'O';
            }
        }
    }
    private void helper(int row, int col, char[][] board) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || board[row][col] != 'O') return;
        board[row][col] = '+';
        helper(row + 1, col, board);
        helper(row - 1, col, board);
        helper(row, col + 1, board);
        helper(row, col - 1, board);
    }
}

// ----------2------------
// Traverse all the 'O's that are accessible from the boundary
// and change it to '+' as a placeholder, then finally
// change all the 'O's that remain to 'X' and change the '+'s back to 'O'.
// Use a point class to store the coordinates, clever!
class Solution {
    public void solve(char[][] board) {
        if (board.length < 2) return;
        int R = board.length, C = board[0].length;
        // use a point class to store the coordinates
        Queue<Point> queue = new LinkedList<Point>();
        for (int row = 0; row < R; row++){
            if (board[row][0] == 'O') {
                board[row][0] = '+';
                queue.add(new Point(row, 0));
            }
            if (board[row][C - 1] == 'O') {
                board[row][C - 1] = '+';
                queue.add(new Point(row, C - 1));
            }
        }
        for (int col = 1; col < C - 1; col++){
            if (board[0][col] == 'O') {
                board[0][col] = '+';
                queue.add(new Point(0, col));
            }
            if (board[R - 1][col] == 'O') {
                board[R - 1][col] = '+';
                queue.add(new Point(R - 1, col));
            }
        }
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int row = p.x, col = p.y;
            if (row + 1 < R && board[row + 1][col] == 'O') {
                board[row + 1][col] = '+';
                queue.add(new Point(row + 1, col));
            }
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                board[row - 1][col] = '+';
                queue.add(new Point(row - 1, col));
            }
            if (col + 1 < C && board[row][col + 1] == 'O') {
                board[row][col + 1] = '+';
                queue.add(new Point(row, col + 1));
            }
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '+';
                queue.add(new Point(row, col - 1));
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '+') board[i][j] = 'O';
            }
        }
    }
}

// ----------3------------
// Naive approach
// too many conversions between 1d and 2d, not necessary, can be done with point class
class Solution {
    public void solve(char[][] board) {
        if (board.length <= 2) return;
        int R = board.length, C = board[0].length;
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> fringe = new LinkedList<>();
        // covert the 2d array to an 1d list
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) { 
                list.add(board[i][j]);
                List<Integer> newList = Arrays.asList(i, j);
                map.put(toOneD(i, j, C), newList);
            }
        }
        // find all the 'O's at the boundary (row = 0, R - 1; col = 0, C - 1)
        for (int i = 0; i < C; i++) {
            if (board[0][i] == 'O') {
                int index = toOneD(0, i, C);
                fringe.add(index);
                visited.add(index);
            }
            if (board[R-1][i] == 'O') {
                int index = toOneD(R-1, i, C);
                fringe.add(index);
                visited.add(index);
            }
        }
        for (int j = 1; j < R-1; j++) {
            if (board[j][0] == 'O') {
                int index = toOneD(j, 0, C);
                fringe.add(index);
                visited.add(index);
            }
            if (board[j][C-1] == 'O') {
                int index = toOneD(j, C-1, C);
                fringe.add(index);
                visited.add(index);
            }
        }
        // BFS to traverse all the connected 'O's and mark them as visited
        while (!fringe.isEmpty()) {
            int index = fringe.poll();
            // check its up, down, left and right
            int left = index - 1, right = index + 1, up = index - C, down = index + C;
            if ((left + 1) % C != 0 && checkIndex(left, R, C) && list.get(left) == 'O' && !visited.contains(left)) {
                fringe.add(left);
                visited.add(left);
            }
            if (right % C != 0 && checkIndex(right, R, C) && list.get(right) == 'O' && !visited.contains(right)) {
                fringe.add(right);
                visited.add(right);
            }
            if (checkIndex(up, R, C) && list.get(up) == 'O' && !visited.contains(up)) {
                fringe.add(up);
                visited.add(up);
            }
            if (checkIndex(down, R, C) && list.get(down) == 'O' && !visited.contains(down)) {
                fringe.add(down);
                visited.add(down);
            }
        }
        // Traverse all 'O's and reverse it if it is not visited
        for (int k = 0; k < R * C; k++) {
            int row = map.get(k).get(0), col = map.get(k).get(1);
            if (board[row][col] == 'O' && !visited.contains(k)) {
                board[row][col] = 'X';
            }
        }
    }
    private int toOneD(int row, int col, int total) {
        return row * total + col;
    }
    private boolean checkIndex(int index, int R, int C) {
        return index >= 0 && index <= R * C - 1;
    }
}