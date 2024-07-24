package machine

/**
 * Enum representing different types of coffee that can be made by the coffee machine.
 *
 * Each enum constant represents a unique type of coffee, encapsulating the required resources
 * for making that specific coffee type.
 *
 * @property water The amount of water (in milliliters) required to make the coffee.
 * @property milk The amount of milk (in milliliters) required to make the coffee.
 * @property beans The amount of coffee beans (in grams) required to make the coffee.
 * @property money The cost (in currency units) of the coffee.
 * @property cups The number of disposable cups required to serve the coffee.
 */
enum class Coffee(val water: Int, val milk: Int, val beans: Int, val money: Int, val cups: Int) {
    ESPRESSO(250, 0, 16, 4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12, 6, 1)
}