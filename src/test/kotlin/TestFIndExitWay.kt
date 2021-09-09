import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestFindExitWay {

    @Test
    fun testSmallMaze() {
        test(11, 11)
    }

    @Test
    fun testBigMaze() {
        test(111, 151)
    }

    private fun test(height : Int, width: Int) {
        val maze = MazeGenerator.genMaze(height, width)
        val startPoint = 1 to 1
        MazeSolver.findExitWay(maze, startPoint)

        assertTrue(checkWay(maze, startPoint))
    }

    private fun checkWay(maze: Maze, sp : Pair<Int, Int>): Boolean {
        val used = Array(maze.height) { Array(maze.width) { false } }
        var cur = sp
        used[cur.first][cur.second] = true
        while(cur.first !in listOf(0,maze.height - 1) && cur.second !in listOf(0,maze.width - 1)) {
            cur = nextMarkedCell(maze, cur, used) ?: return false
            used[cur.first][cur.second] = true
        }
        return true
    }

    private fun nextMarkedCell(maze : Maze, cur: Pair<Int, Int>, used: Array<Array<Boolean>>): Pair<Int, Int>? {
        val cells = mutableListOf<Pair<Int, Int>>()
        (-1..1 step 2).forEach { m ->
            if (cur.first + m in 0 until maze.height && maze[cur.first + m][cur.second] == Maze.pathMark && !used[cur.first + m][cur.second]) {
                cells.add(cur.first + m to cur.second)
            }
            if (cur.second + m in 0 until maze.width && maze[cur.first][cur.second + m] == Maze.pathMark && !used[cur.first][cur.second + m]) {
                cells.add(cur.first to cur.second + m)
            }
        }
        return if(cells.size == 1) cells.first() else null
    }


}