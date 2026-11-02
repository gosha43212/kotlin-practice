data class Weapon(
    val name: String,
    val bonusDamage: Int,
)

data class Potion(
    val name: String,
    val healAmount: Int,
)

data class ManaPotion(
    val name: String,
    val manaAmount: Int,
)

abstract class Hero(
    val name: String,
    initialHealth: Int,
) {
    var health = initialHealth
        private set

    val portfolio: MutableList<Potion> = mutableListOf()

    fun takeDamage(amount: Int) {
        health -= amount
        if (health <= 0) {
            health = 0
            println("$name получает $amount урона и падает без сознания! (0 HP)")
        } else {
            println("️$name получает $amount урона. Осталось здоровья: $health HP.")
        }
    }

    fun receiveHeal(amount: Int) {
        if (health == 0) {
            println("$name без сознания, протое лечение тут не поможет!")
        } else {
            health += amount
            println("$name восстанавливает $amount здоровья. Теперь у него $health HP.")
        }
    }

    abstract fun attack(target: Hero)

    fun drinkPotion(potion: Potion) {
        println("$name выпил ${potion.name}")
        receiveHeal(potion.healAmount)
    }

    fun addPotion(potion: Potion) {
        portfolio.add(potion)
        println("$name положил в свой портфель ${potion.name}. Всего зелей: ${portfolio.size}")
    }

    fun drinkPotionFromPortfolio() {
        if (portfolio.isNotEmpty()) {
            val potion = portfolio.removeFirst()
            println("$name достает из своего портфеля ${potion.name} и выпивает его")
            receiveHeal(potion.healAmount)
        } else {
            println("В портфеле ничего нет, $name уже все выпил")
        }
    }
}

interface Defender {
    fun defend()
}

class Warrior(
    name: String,
    health: Int,
    val strength: Int,
    var weapon: Weapon,
) : Hero(name, health),
    Defender {
    override fun attack(target: Hero) {
        val totalDamage = strength + weapon.bonusDamage
        target.takeDamage(totalDamage)
        println("Герой $name рубит ${target.name} мечом с силой  $totalDamage! У ${target.name} осталось ${target.health} здороья.")
    }

    override fun defend() {
        println("Воин $name закрылся щитом!")
    }
}

class Mage(
    name: String,
    health: Int,
    var mana: Int,
) : Hero(name, health) {
    override fun attack(target: Hero) {
        if (mana >= 10) {
            mana -= 10
            val magicDamage = 35
            target.takeDamage(magicDamage)
            println("маг $name скастовал фаербол в ${target.name} и оставил ему ${target.health} здоровья! Остаток маны у $name - $mana")
        } else {
            println("У мага $name недостаточно маны!")
        }
    }

    fun drinkManaPotion(manaPotion: ManaPotion) {
        mana += manaPotion.manaAmount
        println("$name выпил ${manaPotion.name}, теперь у него $mana маны")
    }
}

interface HealerRole {
    fun heal(target: Hero)
}

class Healer(
    name: String,
    health: Int,
    val healPower: Int,
) : Hero(name, health),
    HealerRole {
    override fun attack(target: Hero) {
        println("Целитель $name слабо стукает посохом ${target.name}!")
        target.takeDamage(5)
    }

    override fun heal(target: Hero) {
        target.receiveHeal(healPower)
        println("Целитель $name вылечил игрока ${target.name} на $healPower здоровья. Теперь у него ${target.health} хп.")
    }
}

fun main() {
    val stick = Weapon("палка", 3)
    val conan = Warrior("Конан", 125, 25, stick) // Сначала Конан тоже с палкой!
    val vasyaMag = Mage("вася крутой", 100, 15)
    val helper = Healer("Гендальф", 100, 10)

    val manaPotion = ManaPotion("малое зелья на ману", 30)
    val smallPotion = Potion("малое зелье здоровья", 10)
    val bigPotion = Potion("большое зелье здоровья", 40)

    conan.addPotion(smallPotion)
    conan.addPotion(bigPotion)

    println(" БОЙ НА АРЕНЕ НАЧИНАЕТСЯ: ${conan.name} против ${vasyaMag.name}!")

    var round = 1

    while (conan.health > 0 && vasyaMag.health > 0) {
        println("\n--- Раунд $round ---")

        conan.attack(vasyaMag)

        if (vasyaMag.health > 0) {
            vasyaMag.attack(conan)
        }
        if (vasyaMag.health > 0 && vasyaMag.health < 40) {
            helper.heal(vasyaMag)
        }
        if (vasyaMag.mana < 10) {
            vasyaMag.drinkManaPotion(manaPotion)
        }
        if (conan.health > 0 && conan.health < 40 && conan.portfolio.isNotEmpty()) {
            conan.drinkPotionFromPortfolio()
        }
        round++
    }

    println("\n ИТОГИ БОЯ:")
    if (conan.health > 0) {
        println("Победил ${conan.name}! У него осталось ${conan.health} HP.")
    } else {
        println("Победил ${vasyaMag.name}! У него осталось ${vasyaMag.health} HP.")
    }
} 
