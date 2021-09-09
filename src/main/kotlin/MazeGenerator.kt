import java.util.*

object MazeGenerator {
    fun genMaze(height: Int, width: Int): Maze {
        val res = Maze(height, width)

        val used = Array(height) { Array(width) { false } }

        var unvisitedCnt = 0
        for (x in 0 until height) {
            for (y in 0 until width) {
                if (x % 2 == 1 && y % 2 == 1) {
                    res[x][y] = Maze.cell
                    unvisitedCnt++
                }

            }
        }

        var cur = 1 to 1
        used[1][1] = true
        unvisitedCnt--
        val st = LinkedList<Pair<Int, Int>>()

        do {
            val ns = getNeighbours(cur, used, height, width)
            when {
                ns.isNotEmpty() -> {
                    val nc = ns.random()
                    st.addLast(nc)
                    removeWall(res, cur, nc)
                    cur = nc
                    used[cur.first][cur.second] = true
                    unvisitedCnt--
                }
                else -> if (st.isNotEmpty()) {
                    cur = st.pollLast()
                } else {
                    val unvisitedList = getUnvisited(used, height, width)
                    cur = unvisitedList.random()
                    used[cur.first][cur.second] = true
                    unvisitedCnt--
                }
            }
        } while (unvisitedCnt != 0)

        res[height - 1][width - 2] = Maze.cell
        return res
    }

    private fun getNeighbours(
        p: Pair<Int, Int>,
        used: Array<Array<Boolean>>,
        height: Int,
        width: Int
    ): List<Pair<Int, Int>> {
        val res = mutableListOf<Pair<Int, Int>>()
        listOf(-2, 2).forEach { m ->
            if (p.first + m in 0 until height && !used[p.first + m][p.second]) {
                res.add(p.first + m to p.second)
            }
            if (p.second + m in 0 until width && !used[p.first][p.second + m]) {
                res.add(p.first to p.second + m)
            }
        }
        return res
    }

    private fun getUnvisited(used: Array<Array<Boolean>>, height: Int, width: Int): List<Pair<Int, Int>> {
        val res = mutableListOf<Pair<Int, Int>>()
        for (x in 0 until height) {
            for (y in 0 until width) {
                if (x % 2 == 1 && y % 2 == 1 && !used[x][y]) {
                    res.add(x to y)
                }
            }
        }
        return res
    }

    private fun removeWall(lb: Maze, cur: Pair<Int, Int>, nc: Pair<Int, Int>) {
        lb[(cur.first + nc.first) / 2][(cur.second + nc.second) / 2] = Maze.cell
    }


}