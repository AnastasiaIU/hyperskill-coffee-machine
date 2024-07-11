package machine

class CoffeeMachine(
    private var water: Int,
    private var milk: Int,
    private var beans: Int,
    private var money: Int,
    private var cups: Int
) {
    private val stateChooseAction: IMachineState = StateChooseAction(this)
    private val stateChooseCoffee: IMachineState = StateChooseCoffee(this)
    private val stateCheckSupplies: IMachineState = StateCheckSupplies(this)
    private val stateMakeCoffee: IMachineState = StateMakeCoffee(this)
    private val statePrintRemaining: IMachineState = StatePrintRemaining(this)
    private val stateFillSupplies: IMachineState = StateFillSupplies(this)
    private val stateCollectMoney: IMachineState = StateCollectMoney(this)

    private var currentState: IMachineState = stateChooseAction
    private var isMachineRunning = true
    private var chosenCoffee: Coffee? = null
    var supplyToFill: String? = null

    var isSuppliesSufficient: Boolean? = null
    var isWaterSufficient: Boolean? = null
    var isMilkSufficient: Boolean? = null
    var isBeansSufficient: Boolean? = null
    var isCupsSufficient: Boolean? = null

    fun isMachineRunning(): Boolean = isMachineRunning

    fun setState(state: IMachineState) {
        currentState = state
    }

    fun setChosenCoffee(coffee: Coffee) {
        chosenCoffee = coffee
    }

    fun getChooseActionState(): IMachineState = stateChooseAction
    fun getChooseCoffeeState(): IMachineState = stateChooseCoffee
    fun getCheckSuppliesState(): IMachineState = stateCheckSupplies
    fun getMakeCoffeeState(): IMachineState = stateMakeCoffee
    fun getPrintRemainingState(): IMachineState = statePrintRemaining
    fun getFillSuppliesState(): IMachineState = stateFillSupplies
    fun getCollectMoneyState(): IMachineState = stateCollectMoney

    fun getMoneyAmount(): Int = money

    fun resetChosenCoffee() {
        chosenCoffee = null
    }

    fun resetSuppliesState() {
        isWaterSufficient = null
        isMilkSufficient = null
        isBeansSufficient = null
        isCupsSufficient = null
    }

    fun resetMoneyAmount() {
        money = 0
    }

    fun printRemaining() {
        println(
            "\nThe coffee machine has:\n" +
                    "$water ml of water\n" +
                    "$milk ml of milk\n" +
                    "$beans g of coffee beans\n" +
                    "$cups disposable cups\n" +
                    "$$money of money\n"
        )
    }

    fun checkSupplies() {
        isSuppliesSufficient = true

        when {
            water < chosenCoffee!!.water -> {
                isSuppliesSufficient = false
                isWaterSufficient = false
            }

            milk < chosenCoffee!!.milk -> {
                isSuppliesSufficient = false
                isMilkSufficient = false
            }

            beans < chosenCoffee!!.beans -> {
                isSuppliesSufficient = false
                isBeansSufficient = false
            }

            cups < chosenCoffee!!.cups -> {
                isSuppliesSufficient = false
                isCupsSufficient = false
            }
        }
    }

    fun subtractSupplies() {
        water -= chosenCoffee!!.water
        milk -= chosenCoffee!!.milk
        beans -= chosenCoffee!!.beans
        cups -= chosenCoffee!!.cups
        money += chosenCoffee!!.money
    }

    fun addWater(amount: Int) {
        water += amount
    }

    fun addMilk(amount: Int) {
        milk += amount
    }

    fun addBeans(amount: Int) {
        beans += amount
    }

    fun addCups(amount: Int) {
        cups += amount
    }

    fun printOutput() {
        currentState.printOutput()
    }

    fun readInput(): String = currentState.readInput()

    fun processInput(userInput: String) {
        isMachineRunning = currentState.processInput(userInput)
    }
}