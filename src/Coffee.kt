package machine

enum class Coffee(val water: Int, val milk: Int, val beans: Int, val money: Int, val cups: Int) {
    ESPRESSO(250, 0, 16, 4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12, 6, 1)
}