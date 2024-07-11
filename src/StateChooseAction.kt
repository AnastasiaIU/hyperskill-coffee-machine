package machine

class StateChooseAction(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        print("Write action (buy, fill, take, remaining, exit): ")
    }

    override fun processInput(input: String): Boolean {
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