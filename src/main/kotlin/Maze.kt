data class Maze(val height: Int, val width: Int) {
    companion object {
        const val cell = 0
        const val wall = 1
        const val pathMark = 2
    }

    private val field = Array(height) { Array(width) { wall } }

    override fun toString(): String {
        var res = ""
        field.forEach { row ->
            row.forEach {
                when (it) {
                    cell -> res += ". "
                    wall -> res += "# "
                    pathMark -> res += "* "
                }
            }
            res += "\n"
        }
        return res
    }

    operator fun set(i: Int, value: Array<Int>) {
        field[i] = value
    }


    operator fun get(i: Int): Array<Int> {
        return field[i]
    }


}