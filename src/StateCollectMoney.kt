package machine

/**
 * Represents the state where the coffee machine handles the collection of money.
 *
 * In this state, the coffee machine displays the total amount of money collected from sales
 * and then resets the money counter to zero. After displaying and resetting, it transitions
 * back to the main menu state, allowing for further user interactions.
 *
 * @property coffeeMachine Reference to the main coffee machine object to allow access to its operations
 * and to facilitate state transitions.
 */
class StateCollectMoney(private val coffeeMachine: CoffeeMachine) : IMachineState {
    /**
     * Outputs the total amount of money collected by the coffee machine.
     */
    override fun printOutput() {
        println("I gave you $${coffeeMachine.money}\n")
    }

    /**
     * Executes the logic for collecting money.
     *
     * This method resets the coffee machine's money counter to zero and transitions the machine
     * back to the main menu state.
     *
     * @param input The user input is an empty String as no input is read in this state.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop.
     */
    override fun executeStateLogic(input: String): Boolean {
        coffeeMachine.resetMoneyAmount()

        val stateChooseAction = coffeeMachine.getChooseActionState()
        coffeeMachine.setState(stateChooseAction)

        return true
    }

    /**
     * Reads user input from the console. This method is not utilized in this state as the state does not require
     * input from the user to proceed.
     *
     * @return An empty String as no input is read in this state.
     */
    override fun readInput(): String = String()
}