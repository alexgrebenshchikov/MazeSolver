
import java.util.*

// . - свободная клетка
// # - стена
// * - найденный путь

fun main() {
    val maze = MazeGenerator.genMaze(9, 37)
    val startPoint = 1 to 1
    MazeSolver.findExitWay(maze, startPoint)
    println(maze)
}