package machine

/**
 * Represents the state of the coffee machine when it is making a coffee.
 *
 * This state is responsible for subtracting the required supplies from the coffee machine's resources and
 * transitioning back to the choosing action state.
 *
 * @property coffeeMachine The coffee machine instance to operate on.
 */
class StateMakeCoffee(private val coffeeMachine: CoffeeMachine) : IMachineState {
    /**
     * Prints the output message for the state.
     */
    override fun printOutput() {
        println("I have enough resources, making you a coffee!\n")
    }

    /**
     * Executes the logic of the state.
     *
     * This method subtracts the required supplies from the coffee machine's resources, resets the chosen coffee, and
     * transitions back to the choosing action state.
     *
     * @param input The user input is an empty String as no input is read in this state.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop.
     */
    override fun executeStateLogic(input: String): Boolean {
        coffeeMachine.subtractSupplies()
        coffeeMachine.resetChosenCoffee()

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