package machine

class StateMakeCoffee(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        println("I have enough resources, making you a coffee!\n")
    }

    override fun processInput(input: String): Boolean {
        coffeeMachine.subtractSupplies()
        coffeeMachine.resetChosenCoffee()

        val stateChooseAction = coffeeMachine.getChooseActionState()
        coffeeMachine.setState(stateChooseAction)

        return true
    }

    override fun readInput(): String = String()
}