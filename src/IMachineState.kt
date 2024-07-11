package machine

import java.util.*

interface IMachineState {
    fun readInput(): String {
        val scanner = Scanner(System.`in`)
        return scanner.next()
    }

    fun printOutput()
    fun processInput(input: String): Boolean
}