package upca.example.calculator

import kotlin.math.sqrt


enum class Operation(val op: String){
    SUM("+"),
    SUB("-"),
    MUL("×"),
    DIV("÷"),
    SQRT("√"),
    PERCENTAGE("%"),
    SIGNAL("±");

    companion object {
        fun getOperation(op: String): Operation {
            return when (op) {
                "+" -> SUM
                "-" -> SUB
                "×" -> MUL
                "÷" -> DIV
                "√" -> SQRT
                "%" -> PERCENTAGE
                "±" -> SIGNAL
                else -> SUM
            }
        }
    }

}

class CalculatorBrain {

    var operation : Operation? = null
    var accumulator : Double = 0.0

    fun doOperation(current: Double) {
        accumulator = when(operation){
            Operation.SUM -> accumulator + current
            Operation.SUB -> accumulator - current
            Operation.MUL -> accumulator * current
            Operation.DIV -> accumulator / current
            Operation.SQRT -> sqrt( current)
            Operation.PERCENTAGE -> current/100
            Operation.SIGNAL -> -current
            null -> current
        }
    }
}