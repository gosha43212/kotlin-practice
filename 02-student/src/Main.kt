val catalog = mapOf("Хлеб" to 50.0,"Молоко" to 90.0,"Сыр" to 400.0, "Яблоки" to 120.0)
fun showCatalog(){
    var number = 1
    for ((name,price) in catalog){
        println("$number. $name - $price")
        number++
    }
}
fun add(): MutableList <String>{
    val spisok_pokupok  = mutableListOf<String>()
    while(true){
        val input = readln().trim()
        if (input.lowercase() == "стоп") break
        val name = input.lowercase().replaceFirstChar { it.uppercase() }
        if (name in catalog) {
            spisok_pokupok.add(name)
        } else {
            println("такого товара нет")
        }
    }
    return spisok_pokupok
}
fun calculateTotal(cart: List<String>): Double {
    if (cart.isEmpty()){
        println("Корзина пуста")
    }
    var total = cart.sumOf{ catalog[it] ?: 0.0 }
    val discount = if (total>500) total * 0.1 else 0.0
    total = total - discount
    print("Введите ваш купон: ")
    val kupon = readln()
    if (kupon == "SALE10") {
        var skidka_po_kuponu = total * 0.1
        println("Скидка по купону ${"%.2f".format(skidka_po_kuponu)}")
        total = total - total * 0.1
    } else{
        println("Такого купона не сущетсвует")
    }
    println("Скидка будет составлять: $discount")
    return total
}
fun main(){
    showCatalog()
    val cart = add()
    println("В корзине: ${cart.joinToString(", ")}")
    val tot = calculateTotal(cart)
    println("С вас: $tot рублей ")
}