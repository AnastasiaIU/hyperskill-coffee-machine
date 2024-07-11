package machine

class StateFillSupplies(private val coffeeMachine: CoffeeMachine) : IMachineState {
    override fun printOutput() {
        if (coffeeMachine.supplyToFill == null) coffeeMachine.supplyToFill = "water"

        when (coffeeMachine.supplyToFill) {
            "water" -> print("\nWrite how many ml of water do you want to add: ")
            "milk" -> print("Write how many ml of milk do you want to add: ")
            "beans" -> print("Write how many grams of coffee beans do you want to add: ")
            "cups" -> print("Write how many disposable cups of coffee do you want to add: ")
        }
    }

    override fun processInput(input: String): Boolean {
        when (coffeeMachine.supplyToFill) {
            "water" -> {
                coffeeMachine.addWater(input.toInt())
                coffeeMachine.supplyToFill = "milk"
            }

            "milk" -> {
                coffeeMachine.addMilk(input.toInt())
                coffeeMachine.supplyToFill = "beans"
            }

            "beans" -> {
                coffeeMachine.addBeans(input.toInt())
                coffeeMachine.supplyToFill = "cups"
            }

            "cups" -> {
                coffeeMachine.addCups(input.toInt())
                coffeeMachine.supplyToFill = null
                println()
                val stateChooseAction = coffeeMachine.getChooseActionState()
                coffeeMachine.setState(stateChooseAction)
            }
        }

        return true
    }
}