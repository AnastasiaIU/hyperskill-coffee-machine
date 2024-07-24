package machine

import java.util.*

/**
 * Represents the state of the coffee machine when refilling supplies.
 *
 * @property coffeeMachine The coffee machine instance to refill supplies for.
 */
class StateFillSupplies(private val coffeeMachine: CoffeeMachine) : IMachineState {
    // The specific supply item to refill.
    private var supplyToRefill = Resource.WATER

    // Flag to track if the coffee machine is currently refilling supplies.
    private var isRefillingSupplies = true

    /**
     * Prints the output message for the current state.
     */
    override fun printOutput() {
        if (isRefillingSupplies) {
            print(
                when (supplyToRefill) {
                    Resource.BEANS -> "Write how many ${supplyToRefill.longUnit} of ${supplyToRefill.nameToPrint} you want to add: "
                    Resource.CUPS -> "Write how many ${supplyToRefill.longUnit} you want to add: "
                    else -> "Write how many ${supplyToRefill.shortUnit} of ${supplyToRefill.nameToPrint} you want to add: "
                }
            )
        }
        else {
            println()
        }
    }

    /**
     * Handles the logic for refilling supplies based on user input.
     *
     * This method processes the user's input to add the specified amount of the current supply item to the coffee machine.
     * After updating the supply, it transitions to the next supply item to be refilled. Once all supplies are refilled,
     * it transitions the state back to the main menu, allowing for further user interactions.
     *
     * @param input The user input as a String, representing the amount of the current supply item to add.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop.
     */
    override fun executeStateLogic(input: String): Boolean {
        if (isRefillingSupplies) {
            addSupply(input.toInt())
        } else {
            val stateChooseAction = coffeeMachine.getChooseActionState()
            coffeeMachine.setState(stateChooseAction)
            isRefillingSupplies = true
        }

        return true
    }

    /**
     * Reads the user input for the current state.
     *
     * @return The user input as a string.
     */
    override fun readInput(): String {
        if (isRefillingSupplies) {
            val scanner = Scanner(System.`in`)
            return scanner.next()
        }
        else {
            return String()
        }
    }

    /**
     * Adds the specified amount of a supply item to the coffee machine and
     * updates the supply item to refill based on the current state.
     *
     * @param amount The amount of the supply item to add.
     */
    private fun addSupply(amount: Int) {
        when (supplyToRefill) {
            Resource.WATER -> {
                coffeeMachine.addWater(amount)
                supplyToRefill = Resource.MILK
            }

            Resource.MILK -> {
                coffeeMachine.addMilk(amount)
                supplyToRefill = Resource.BEANS
            }

            Resource.BEANS -> {
                coffeeMachine.addBeans(amount)
                supplyToRefill = Resource.CUPS
            }

            Resource.CUPS -> {
                coffeeMachine.addCups(amount)
                supplyToRefill = Resource.WATER
                isRefillingSupplies = false
            }

            else -> {}
        }
    }
}