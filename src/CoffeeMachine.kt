package machine

/**
 * Represents the main class of the coffee machine application.
 *
 * This class manages the overall state of the coffee machine, including its resources and current state.
 *
 * @property _water The current amount of water in milliliters available in the coffee machine.
 * @property _milk The current amount of milk in milliliters available in the coffee machine.
 * @property _beans The current amount of coffee beans in grams available in the coffee machine.
 * @property _money The current amount of money in currency units collected by the coffee machine.
 * @property _cups The current number of disposable cups available in the coffee machine.
 */
class CoffeeMachine(
    private var _water: Int,
    private var _milk: Int,
    private var _beans: Int,
    private var _money: Int,
    private var _cups: Int
) {
    val water: Int
        get() = _water
    val milk: Int
        get() = _milk
    val beans: Int
        get() = _beans
    val money: Int
        get() = _money
    val cups: Int
        get() = _cups

    // State instances representing different actions or phases in the coffee machine's operation.
    private val stateChooseAction: IMachineState by lazy { StateChooseAction(this) }
    private val stateChooseCoffee: IMachineState by lazy { StateChooseCoffee(this) }
    private val stateCheckSupplies: IMachineState by lazy { StateCheckSupplies(this) }
    private val stateMakeCoffee: IMachineState by lazy { StateMakeCoffee(this) }
    private val statePrintRemaining: IMachineState by lazy { StatePrintRemaining(this) }
    private val stateFillSupplies: IMachineState by lazy { StateFillSupplies(this) }
    private val stateCollectMoney: IMachineState by lazy { StateCollectMoney(this) }

    // The current operational state of the coffee machine.
    private var currentState: IMachineState = stateChooseAction

    // Flag to control the main operation loop of the coffee machine.
    private var isMachineRunning = true

    // The type of coffee selected by the user, if any.
    private var chosenCoffee: Coffee? = null

    /**
     * Checks if the coffee machine is currently running.
     * @return true if the machine is running, false otherwise.
     */
    fun isMachineRunning(): Boolean = isMachineRunning

    /**
     * Sets the current state of the coffee machine to a new state.
     * @param state The new state to set the coffee machine to.
     */
    fun setState(state: IMachineState) {
        currentState = state
    }

    /**
     * Sets the chosen coffee type to the specified coffee.
     *
     * @param coffee The coffee type chosen by the user.
     */
    fun setChosenCoffee(coffee: Coffee) {
        chosenCoffee = coffee
    }

    // Getter methods for each state, allowing transitions between states.
    fun getChooseActionState(): IMachineState = stateChooseAction
    fun getChooseCoffeeState(): IMachineState = stateChooseCoffee
    fun getCheckSuppliesState(): IMachineState = stateCheckSupplies
    fun getMakeCoffeeState(): IMachineState = stateMakeCoffee
    fun getPrintRemainingState(): IMachineState = statePrintRemaining
    fun getFillSuppliesState(): IMachineState = stateFillSupplies
    fun getCollectMoneyState(): IMachineState = stateCollectMoney

    /**
     * Retrieves the chosen coffee type.
     *
     * @return The chosen coffee type, or null if no coffee has been chosen.
     */
    fun getChosenCoffee(): Coffee? = chosenCoffee

    /**
     * Resets the amount of money collected by the coffee machine to 0.
     */
    fun resetMoneyAmount() {
        _money = 0
    }

    /**
     * Resets the chosen coffee type to null.
     */
    fun resetChosenCoffee() {
        chosenCoffee = null
    }

    /**
     * Subtracts the required supplies from the current supplies based on the chosen coffee type.
     * Adds the cost of the coffee to the total money collected.
     */
    fun subtractSupplies() {
        _water -= chosenCoffee!!.water
        _milk -= chosenCoffee!!.milk
        _beans -= chosenCoffee!!.beans
        _cups -= chosenCoffee!!.cups
        _money += chosenCoffee!!.money
    }

    // Methods to add supplies to the coffee machine.
    fun addWater(amount: Int) { _water += amount }
    fun addMilk(amount: Int) { _milk += amount }
    fun addBeans(amount: Int) { _beans += amount }
    fun addCups(amount: Int) { _cups += amount }

    /**
     * Delegates the printing of output to the current state.
     */
    fun printOutput() {
        currentState.printOutput()
    }

    /**
     * Delegates reading of input to the current state.
     *
     * @return The user input as a String.
     */
    fun readInput(): String = currentState.readInput()

    /**
     * Delegates the execution of state logic to the current state.
     *
     * @param userInput The user input as a String.
     */
    fun executeStateLogic(userInput: String) {
        isMachineRunning = currentState.executeStateLogic(userInput)
    }
}