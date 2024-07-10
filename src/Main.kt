package machine

import java.util.*

val scanner = Scanner(System.`in`)

enum class Coffee(var water: Int, var milk: Int, var beans: Int, var money: Int, var cups: Int) {
    ESPRESSO(250, 0, 16, 4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12, 6, 1),
    SUPPLIES(400, 540, 120, 550, 9)
}

fun coffeeSubtraction(typeOfCoffee: Coffee) {
    when {
        Coffee.SUPPLIES.water < typeOfCoffee.water -> return println("Sorry, not enough water!")
        Coffee.SUPPLIES.milk < typeOfCoffee.milk -> return println("Sorry, not enough milk!")
        Coffee.SUPPLIES.beans < typeOfCoffee.beans -> return println("Sorry, not enough coffee beans!")
        Coffee.SUPPLIES.cups < typeOfCoffee.cups -> return println("Sorry, not enough disposable cups of coffee!")
        else -> println("I have enough resources, making you a coffee!")
    }

    Coffee.SUPPLIES.water -= typeOfCoffee.water
    Coffee.SUPPLIES.milk -= typeOfCoffee.milk
    Coffee.SUPPLIES.beans -= typeOfCoffee.beans
    Coffee.SUPPLIES.cups -= typeOfCoffee.cups
    Coffee.SUPPLIES.money += typeOfCoffee.money
}

fun coffeeMachineStatus() {
    println("\nThe coffee machine has:\n" +
            "${Coffee.SUPPLIES.water} of water\n" +
            "${Coffee.SUPPLIES.milk} of milk\n" +
            "${Coffee.SUPPLIES.beans} of coffee beans\n" +
            "${Coffee.SUPPLIES.cups} of disposable cups")

    if (Coffee.SUPPLIES.money != 0) print("$")

    print("${Coffee.SUPPLIES.money} of money\n" +
            "\n")
}

fun buy() {
    print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")

    when (scanner.next()) {
        "back" -> return println()
        "1" -> coffeeSubtraction(Coffee.ESPRESSO)
        "2" -> coffeeSubtraction(Coffee.LATTE)
        "3" -> coffeeSubtraction(Coffee.CAPPUCCINO)
    }

    println()
}

fun fill() {
    print("\nWrite how many ml of water do you want to add: ")
    Coffee.SUPPLIES.water += scanner.nextInt()
    print("Write how many ml of milk do you want to add: ")
    Coffee.SUPPLIES.milk += scanner.nextInt()
    print("Write how many grams of coffee beans do you want to add: ")
    Coffee.SUPPLIES.beans += scanner.nextInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    Coffee.SUPPLIES.cups += scanner.nextInt()
    println()
}

fun take() {
    println("\nI gave you $${Coffee.SUPPLIES.money}" +
            "\n")
    Coffee.SUPPLIES.money = 0
}

fun main() {
    loop@ while (true) {
        print("Write action (buy, fill, take, remaining, exit): ")

        when (scanner.next()) {
            "exit" -> break@loop
            "remaining" -> coffeeMachineStatus()
            "buy" -> buy()
            "fill" -> fill()
            "take" -> take()
        }
    }
}