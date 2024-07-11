package machine

class StateCollectMoney(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        val moneyAmount = coffeeMachine.getMoneyAmount()
        println("\nI gave you $$moneyAmount\n")
    }

    override fun processInput(input: String): Boolean {
        coffeeMachine.resetMoneyAmount()

        val stateChooseAction = coffeeMachine.getChooseActionState()
        coffeeMachine.setState(stateChooseAction)

        return true
    }

    override fun readInput(): String = String()
}