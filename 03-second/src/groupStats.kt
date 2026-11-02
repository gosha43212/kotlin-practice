data class Stats(val average: Double, val best: Int, val worst: Int)

fun groupStats(): String {
    print("Введите группу: ")
    val group = readln()
    if (group in students.map{it.group}) {
        val avg_of_group = students
            .filter { it.group == group }
            .map { it.grade }
            .average()
        return "$group - ${"%.2f".format(avg_of_group)}"
    } else {
        return "такой группы нет"
    }
}
fun main(){
    println(groupStats())
}