package machine

/**
 * Represents the resources available in the coffee machine for display purposes.
 *
 * @property nameToPrint The name of the resource to be displayed in the output.
 * @property shortUnit The short unit representation of the resource for display.
 * @property longUnit The long unit representation of the resource for display.
 */
enum class Resource(val nameToPrint: String, val shortUnit: String, val longUnit: String) {
    WATER("water", "ml", "milliliters"),
    MILK("milk", "ml", "milliliters"),
    BEANS("coffee beans", "g","grams"),
    CUPS("disposable cups", "cups", "disposable cups of coffee"),
    MONEY("money", "$","dollars")
}