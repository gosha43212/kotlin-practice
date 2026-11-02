fun main() {
    val vasya =  GoodStudent("Васян","A2")
    vasya.showGroup()
    vasya.action()
    val badClassmate = BadStudent("маским", "B2")
    badClassmate.showGroup()
    badClassmate.action()
    badClassmate.attack(vasya)
    vasya.zayava("Паша")
}
abstract class Student(
    val name: String,
    val group: String,
) {
    abstract fun action()

    fun showGroup() {
        println("Группа студента $name - $group")
    }
}

class BadStudent(
    name: String,
    group: String,
) : Student(name, group) {
    override fun action() {
        println("Плохой студент не хочет учиться")
    }

    fun attack(target: Student) {
        println("Студент $name атаковал ${target.name} и за это будет наказан")
    }


class GoodStudent(
    name: String,
    group: String,
) : Student(name, group) {
    override fun action() {
        println("$name оттого и хороший, что прилежно учиться")
    }

    fun zayava(target: String) {
        println("студент $name пожаловался $target. тебе пизда копечный")
    }
}
