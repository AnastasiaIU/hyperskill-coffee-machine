package machine

import java.util.*

/**
 * An interface representing a state of the coffee machine.
 */
interface IMachineState {
    /**
     * Reads user input from the console.
     * @return The user input as a String.
     */
    fun readInput(): String {
        val scanner = Scanner(System.`in`)
        val input = scanner.next()
        println()
        return input
    }

    /**
     * Prints output to the console.
     *
     * The specific output will depend on the current state.
     */
    fun printOutput()

    /**
     * Executes the main logic associated with the current state of the coffee machine.
     *
     * This method is responsible for handling the state-specific actions based on the user input,
     * which may or may not be provided. Depending on the state, it may involve checking supplies,
     * making coffee, or any other state-related task. The method also determines the next state
     * of the coffee machine by updating its current state or maintaining it based on the logic executed.
     *
     * @param input The user input as a String, which may influence the state's logic.
     *         It can be an empty String, indicating that no user input is provided.
     * @return A Boolean indicating whether the coffee machine should continue running. Returning false
     *         signals the machine to stop its operation loop.
     */
    fun executeStateLogic(input: String): Boolean
}