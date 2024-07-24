package machine

/**
 * Represents the state where the coffee machine prints the remaining resources.
 *
 * @property coffeeMachine Reference to the main coffee machine object to allow access to its current resource levels.
 */
class StatePrintRemaining(private val coffeeMachine: CoffeeMachine) : IMachineState {
    /**
     * Outputs the current resource levels of the coffee machine.
     */
    override fun printOutput() {
        println(
            "The coffee machine has:\n" +
                    "${coffeeMachine.water} ${Resource.WATER.shortUnit} of ${Resource.WATER.nameToPrint}\n" +
                    "${coffeeMachine.milk} ${Resource.MILK.shortUnit} of ${Resource.MILK.nameToPrint}\n" +
                    "${coffeeMachine.beans} ${Resource.BEANS.shortUnit} of ${Resource.BEANS.nameToPrint}\n" +
                    "${coffeeMachine.cups} ${Resource.CUPS.nameToPrint}\n" +
                    "$${coffeeMachine.money} of ${Resource.MONEY.nameToPrint}\n"
        )
    }

    /**
     * Handles the transition back to the state where the user can choose an action
     * after printing the remaining resources.
     *
     * @param input The user input is an empty String as no input is read in this state.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop.
     */
    override fun executeStateLogic(input: String): Boolean {
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