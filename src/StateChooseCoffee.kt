package machine

class StateChooseCoffee(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    }

    override fun processInput(input: String): Boolean {
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

    private fun checkSuppliesFor(coffee: Coffee) {
        val stateCheckSupplies = coffeeMachine.getCheckSuppliesState()
        coffeeMachine.setState(stateCheckSupplies)
        coffeeMachine.setChosenCoffee(coffee)
    }
}