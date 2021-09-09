import java.util.*

object MazeSolver {

    fun findExitWay(lb: Maze, start: Pair<Int, Int>) {
        val h = lb.height
        val w = lb.width

        val used = Array(h) { Array(w) { false } }
        val par = Array(h) { Array(w) { -1 to -1 } }


        val q = LinkedList<Pair<Int, Int>>()
        q.add(start)
        while (q.isNotEmpty()) {
            val v = q.pollFirst()
            used[v.first][v.second] = true
            (-1..1 step 2).forEach { m ->
                if (v.first + m in 0 until h && lb[v.first + m][v.second] == Maze.cell && !used[v.first + m][v.second]) {
                    q.add(v.first + m to v.second)
                    par[v.first + m][v.second] = v
                }
                if (v.second + m in 0 until w && lb[v.first][v.second + m] == Maze.cell && !used[v.first][v.second + m]) {
                    q.add(v.first to v.second + m)
                    par[v.first][v.second + m] = v
                }
            }
        }

        val exitPoints = findExitPoints(lb, h, w)
        for (p in exitPoints) {
            if (used[p.first][p.second]) {

                var cur = p
                while (par[cur.first][cur.second] != -1 to -1) {
                    lb[cur.first][cur.second] = Maze.pathMark
                    cur = par[cur.first][cur.second]
                }
                lb[cur.first][cur.second] = Maze.pathMark
                break
            }
        }

    }

    private fun findExitPoints(lb: Maze, h: Int, w: Int): List<Pair<Int, Int>> {
        val exps = mutableListOf<Pair<Int, Int>>()
        listOf(0, h - 1).forEach { x ->
            for (y in 0 until w) {
                if (lb[x][y] == Maze.cell)
                    exps.add(x to y)
            }
        }

        listOf(0, w - 1).forEach { y ->
            for (x in 0 until h) {
                if (lb[x][y] == Maze.cell)
                    exps.add(x to y)
            }
        }
        return exps
    }


}