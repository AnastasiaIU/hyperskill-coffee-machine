package machine

class StatePrintRemaining(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        coffeeMachine.printRemaining()
    }

    override fun processInput(input: String): Boolean {
        val stateChooseAction = coffeeMachine.getChooseActionState()
        coffeeMachine.setState(stateChooseAction)
        return true
    }

    override fun readInput(): String = String()
}