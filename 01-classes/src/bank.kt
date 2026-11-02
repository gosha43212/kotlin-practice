private const val COMMISSION_RATE = 0.01
class BankAccount(val owner: String, balance: Double) {
    init {
        require(balance >= 0) { "Начальный баланс не может быть отрицательным" }
    }

    var balance: Double = balance
        private set

    private val history = mutableListOf<String>()

    private fun fmt(x: Double) = "%.2f".format(x)

    fun deposit(amount: Double): Boolean {
        if (amount <= 0) return false
        balance += amount
        history.add("Пополнение +${fmt(amount)}")
        return true
    }

    fun withdraw(amount: Double): Boolean {
        val fee = amount * COMMISSION_RATE
        val total = amount + fee
        if (amount <= 0 || total > balance) return false
        balance -= total
        history.add("Снятие -${fmt(amount)} (комиссия ${fmt(fee)})")
        return true
    }

    fun showHistory() {
        if (history.isEmpty()) {
            println("Операций нет")
        } else {
            history.forEachIndexed { i, record -> println("${i + 1}. $record") }
        }
    }

    fun show() {
        println("$owner: ${fmt(balance)}")
    }

    private fun Share(amount: Double ){
        if (amount<balance){
            print("Введите получателя: ")
            val poluchatel = readln()
            balance -= amount
            var balance_poluchatel = +amount
            println(owner отправил $poluchatel $amount рублей. Баланс $poluchatel - $balance_poluchatel)
        } else{
            println("Введенная сумма превышает баланс.")
        }
    }
}

fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        val value = readln().trim().replace(',', '.').toDoubleOrNull()
        if (value != null) return value
        println("Нужно ввести число")
    }
}

fun readStartBalance(): Double {
    while (true) {
        val value = readDouble("Начальный баланс: ")
        if (value >= 0) return value
        println("Баланс не может быть отрицательным")
    }
}

fun main() {
    val account = BankAccount("Евген", readStartBalance())

    while (true) {
        print("Действие (+, -, баланс, история, стоп): ")
        when (readln().trim().lowercase()) {
            "+" -> {
                if (account.deposit(readDouble("Сумма: "))) println("Готово")
                else println("Сумма должна быть положительной")
            }
            "-" -> {
                if (account.withdraw(readDouble("Сумма: "))) println("Готово")
                else println("Недостаточно средств (с учётом комиссии) или неверная сумма")
            }
            "баланс" -> account.show()
            "история" -> account.showHistory()
            "отправить" -> BankAccount.Share(readDouble("Сумма:"))
            "стоп" -> break
            else -> println("Неизвестная команда")
        }
    }
}
