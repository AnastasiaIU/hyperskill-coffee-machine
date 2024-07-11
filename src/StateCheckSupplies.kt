package machine

class StateCheckSupplies(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        coffeeMachine.checkSupplies()
        var message = "Sorry, not enough "

        if (!coffeeMachine.isSuppliesSufficient!!) {
            when {
                !coffeeMachine.isWaterSufficient!! -> message += "water!\n"
                !coffeeMachine.isMilkSufficient!! -> message += "milk!\n"
                !coffeeMachine.isBeansSufficient!! -> message += "coffee beans!\n"
                !coffeeMachine.isCupsSufficient!! -> message += "disposable cups of coffee!\n"
            }

            println(message)
        }
    }

    override fun processInput(input: String): Boolean {
        if (coffeeMachine.isSuppliesSufficient!!) {
            val stateMakeCoffee = coffeeMachine.getMakeCoffeeState()
            coffeeMachine.setState(stateMakeCoffee)
        }
        else {
            val stateChooseAction = coffeeMachine.getChooseActionState()
            coffeeMachine.setState(stateChooseAction)
        }

        coffeeMachine.resetSuppliesState()

        return true
    }

    override fun readInput(): String = String()
}