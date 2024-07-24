package machine

/**
 * The entry point of the coffee machine application.
 *
 * Initializes the coffee machine with a predefined set of resources and enters the main operation loop.
 * In the loop, it continuously prints the current state's output, reads user input, and processes the input
 * until the machine is turned off.
 */
fun main() {
    // Initialize the coffee machine with starting resources: water, milk, coffee beans, money, and disposable cups.
    val coffeeMachine = CoffeeMachine(400, 540, 120, 550, 9)

    // Main loop that runs as long as the coffee machine is operational.
    loop@ while (coffeeMachine.isMachineRunning()) {
        // Print the current state's output to the user.
        coffeeMachine.printOutput()

        // Read the user's input.
        val input = coffeeMachine.readInput()

        // Process the user's input according to the current state.
        coffeeMachine.executeStateLogic(input)
    }
}