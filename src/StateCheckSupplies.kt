package machine

/**
 * Represents the state of the coffee machine when checking if the supplies are sufficient to make the selected coffee.
 *
 * This state is responsible for verifying the availability of resources required for making the selected
 * type of coffee. Depending on the check, it transitions the machine to either the state for making coffee
 * or back to the choice of action if supplies are insufficient.
 *
 * @property coffeeMachine The context of the coffee machine, allowing this state to access and modify its properties.
 */
class StateCheckSupplies(private val coffeeMachine: CoffeeMachine) : IMachineState {
    // Flags to track the sufficiency of each supply for making the selected coffee.
    private var isSuppliesSufficient = true
    private var isWaterSufficient = true
    private var isMilkSufficient = true
    private var isBeansSufficient = true
    private var isCupsSufficient = true

    /**
     * Prints output to the console based on the sufficiency of supplies.
     *
     * If supplies are not sufficient, it displays a message indicating which supplies are insufficient.
     */
    override fun printOutput() {
        // Check if the supplies are sufficient for the selected coffee.
        checkSupplies()

        // If supplies are not sufficient, print the message for insufficient supplies.
        if (!isSuppliesSufficient) {
            println("Sorry, not enough " +
                    when {
                        !isWaterSufficient -> "${Resource.WATER.nameToPrint}!\n"
                        !isMilkSufficient -> "${Resource.MILK.nameToPrint}!\n"
                        !isBeansSufficient -> "${Resource.BEANS.nameToPrint}!\n"
                        !isCupsSufficient -> "${Resource.CUPS.longUnit}!\n"
                        else -> String()
                    })
        }
    }

    /**
     * Executes the logic of the state by transitioning to the make coffee state if supplies are sufficient.
     * Otherwise, it transitions back to the choose action state.
     *
     * @param input The user input is an empty String as no input is read in this state.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop.
     */
    override fun executeStateLogic(input: String): Boolean {
        // Transition to the make coffee state if supplies are sufficient.
        if (isSuppliesSufficient) {
            val stateMakeCoffee = coffeeMachine.getMakeCoffeeState()
            coffeeMachine.setState(stateMakeCoffee)
        }
        // Otherwise, transition back to the choose action state.
        else {
            val stateChooseAction = coffeeMachine.getChooseActionState()
            coffeeMachine.setState(stateChooseAction)
        }

        // Reset the supply flags for the next check.
        resetSupplyFlags()

        return true
    }

    /**
     * Reads user input from the console. This method is not utilized in this state as the state does not require
     * input from the user to proceed.
     *
     * @return An empty String as no input is read in this state.
     */
    override fun readInput(): String = String()

    /**
     * Checks if the current supplies are sufficient to make the chosen type of coffee.
     *
     * Updates the supply sufficiency flags accordingly.
     */
    private fun checkSupplies() {
        val chosenCoffee = coffeeMachine.getChosenCoffee()!!

        when {
            coffeeMachine.water < chosenCoffee.water -> {
                isSuppliesSufficient = false
                isWaterSufficient = false
            }

            coffeeMachine.milk < chosenCoffee.milk -> {
                isSuppliesSufficient = false
                isMilkSufficient = false
            }

            coffeeMachine.beans < chosenCoffee.beans -> {
                isSuppliesSufficient = false
                isBeansSufficient = false
            }

            coffeeMachine.cups < chosenCoffee.cups -> {
                isSuppliesSufficient = false
                isCupsSufficient = false
            }
        }
    }

    /**
     * Resets the flags tracking the sufficiency of each supply.
     */
    private fun resetSupplyFlags() {
        isSuppliesSufficient = true
        isWaterSufficient = true
        isMilkSufficient = true
        isBeansSufficient = true
        isCupsSufficient = true
    }
}