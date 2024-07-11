package machine

fun main() {
    val coffeeMachine = CoffeeMachine(400, 540, 120, 550, 9)

    loop@ while (coffeeMachine.isMachineRunning()) {
        coffeeMachine.printOutput()
        val input = coffeeMachine.readInput()
        coffeeMachine.processInput(input)
    }
}