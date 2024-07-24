package machine

import java.util.*

/**
 * Represents the state where the user chooses a type of coffee to buy or the option to return to the main menu.
 *
 * Based on the selection, it either initiates a check for supplies for the chosen coffee type or
 * returns to the main menu state.
 *
 * @property coffeeMachine Reference to the main coffee machine object to allow state transitions and
 * access and modify its properties.
 */
class StateChooseCoffee(private val coffeeMachine: CoffeeMachine) : IMachineState {
    /**
     * Outputs the prompt for the user to choose a type of coffee or to return to the main menu.
     */
    override fun printOutput() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    }

    /**
     * Executes the logic based on the user's coffee selection.
     *
     * Depending on the user's input, this method either sets the machine to check the supplies for the selected
     * coffee type or transitions back to the main menu.
     *
     * @param input The user input as a String, representing the chosen coffee type or the command to return to the main menu.
     * @return A Boolean indicating that the coffee machine should continue running, allowing further user interactions.
     */
    override fun executeStateLogic(input: String): Boolean {
        when (input) {
            "back" -> {
                println()
                val stateChooseAction = coffeeMachine.getChooseActionState()
                coffeeMachine.setState(stateChooseAction)
            }

            "1" -> checkSuppliesFor(Coffee.ESPRESSO)
            "2" -> checkSuppliesFor(Coffee.LATTE)
            "3" -> checkSuppliesFor(Coffee.CAPPUCCINO)
        }

        return true
    }

    /**
     * Reads the user input for choosing a coffee type or navigating back to the main menu.
     *
     * @return The user input as a String.
     */
    override fun readInput(): String {
        val scanner = Scanner(System.`in`)
        return scanner.next()
    }

    /**
     * Initiates the transition to check supplies for the selected coffee type.
     *
     * This method sets the chosen coffee type in the coffee machine and transitions the state to checking supplies,
     * which will verify if the machine has enough resources to make the selected type of coffee.
     *
     * @param coffee The selected type of coffee as a Coffee enum.
     */
    private fun checkSuppliesFor(coffee: Coffee) {
        coffeeMachine.setChosenCoffee(coffee)

        val stateCheckSupplies = coffeeMachine.getCheckSuppliesState()
        coffeeMachine.setState(stateCheckSupplies)
    }
}