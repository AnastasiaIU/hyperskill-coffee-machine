package machine

/**
 * Represents the state for choosing an action in the coffee machine.
 * This state prompts the user to select an action from a predefined set of options
 * (buy, fill, take, remaining, exit) and transitions the coffee machine to the appropriate state
 * based on the user's choice.
 *
 * @property coffeeMachine Reference to the main coffee machine object to allow state transitions.
 */
class StateChooseAction(private val coffeeMachine: CoffeeMachine) : IMachineState {
    /**
     * Outputs the prompt for the user to write an action.
     * This method is called to display the available actions to the user.
     */
    override fun printOutput() {
        print("Write action (buy, fill, take, remaining, exit): ")
    }

    /**
     * Executes the logic for choosing an action.
     * Based on the user's input, this method transitions the coffee machine to the corresponding state
     * (e.g., buying coffee, filling supplies, taking money, printing remaining supplies, or exiting the application).
     *
     * @param input The user input as a String, representing the chosen action.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop, which occurs if the user inputs "exit".
     */
    override fun executeStateLogic(input: String): Boolean {
        when (input) {
            "exit" -> return false

            "remaining" -> {
                val statePrintRemaining = coffeeMachine.getPrintRemainingState()
                coffeeMachine.setState(statePrintRemaining)
            }

            "buy" -> {
                val stateChooseCoffee = coffeeMachine.getChooseCoffeeState()
                coffeeMachine.setState(stateChooseCoffee)
            }

            "fill" -> {
                val stateFillSupplies = coffeeMachine.getFillSuppliesState()
                coffeeMachine.setState(stateFillSupplies)
            }

            "take" -> {
                val stateCollectMoney = coffeeMachine.getCollectMoneyState()
                coffeeMachine.setState(stateCollectMoney)
            }
        }

        return true
    }
}