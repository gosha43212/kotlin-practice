data class Student(val name: String, val group: String, val grade: Int)
val students = listOf(
    Student("Аня", "A1", 5),
    Student("Борис","A1",3),
    Student("Вика","B2",4),
    Student("Глеб","B2",5),
    Student("Дина","A1",4),
    Student("Егор","B2",2),
)
fun sort(){
    val good = students
        .filter { it.grade >= 4 }
        .map { it.name }
    println(good.joinToString(", "))
    val sorted = students.sortedByDescending { it.grade }
    println(sorted.joinToString("\n") {"${it.name} - ${it.grade}"})
}
fun task_1(){
    val groupe = students
        .filter {it.grade >= 4 }
        .map{ it.group }
    println(groupe.joinToString(", ") )
}
fun task_2(){
    val name =
        students
            .sortedByDescending { it.name }
            .map{it.name}
    println(name.joinToString(", "))
}
fun task_3(){
    val student = students
        .filter {it.group == "A1" && it.grade >= 4}
        .map{it.name}
    println(student.joinToString(", "))
}
fun task_4(){
    val student =
        students
            .sortedWith(compareBy<Student> { it.group }.thenByDescending { it.grade })
            .joinToString("\n") { "${it.group} ${it.name} ${it.grade}" }
    println(student)
}
fun task_5(){
    val student =
        students
            .filter{it.name.length>3}
            .map{it.name.uppercase()}
            .joinToString (", ")
    println(student)
}
fun task_6(): List<String>{
    val student =
        students
            .sortedByDescending { it.grade }
            .map{it.name}
            .take(3)
    return student
}
fun average(): Double? {
    if(students.isEmpty()) {
        return null
    } else {
        val student =
            students
                .filter { it.group == "A1" }
                .map { it.grade }
                .average()
        return student
    }
}
fun best_student(){ //String {
    val best_grade_all = students.maxOfOrNull { it.grade }
    val bests =
        students
            .filter{it.grade == best_grade_all}
            .map{it.name}

    println(bests.joinToString(", "))
    //return  if (best == null) "нет лучшего студента" else "${best.name} - ${best.grade}"
    //return best?.let {"${it.name} - ${it.grade}" } ?: "нет студентов"
}
fun bad_student(): String ? {
    val student =
        students
            .filter { it.grade < 3 }
            .map{it.name}
            .joinToString(", ")
    if (student.isEmpty()) return null else return student
}
fun bolshe_average(): String {
    if (students.isEmpty()) return "нет студентов"
    val student_average =
        students
            .map{it.grade}
            .average()
    return students
        .filter {it.grade > student_average}
        .map{it.name}
        .joinToString(", ")
}
fun main(){
    sort()
    task_1()
    task_2()
    task_3()
    task_4()
    task_5()
    val (first, second, third) = task_6()
    println("$first, $second, $third")
    println("%.2f".format(average()))
    best_student()
    println(bad_student())
    println(bolshe_average())
}