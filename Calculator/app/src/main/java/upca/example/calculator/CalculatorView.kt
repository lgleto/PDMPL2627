package upca.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import upca.example.calculator.ui.theme.CalculatorTheme
import kotlin.collections.plusAssign

@Composable
fun CalculatorView(
    modifier: Modifier = Modifier
){

    var displayText by remember { mutableStateOf("0") }
    var operand by remember { mutableStateOf(0.0) }
    var operation by remember { mutableStateOf<String?>(null) }
    var userIsInTheMiddleOfIntroduction by remember { mutableStateOf(false) }


    val numPressed : (String)-> Unit = { num ->

        if (userIsInTheMiddleOfIntroduction) {
            if (num == ".") {
                if (!displayText.contains(".")) {
                    displayText += num
                }
            } else {
                if (displayText == "0") {
                    displayText = num
                } else {
                    displayText += num
                }
            }
        }else{
            displayText = num
        }

        userIsInTheMiddleOfIntroduction = true

    }

    val opPressed : (String)-> Unit = { op ->


        when(operation){
            "+" -> {
                displayText = "${operand + displayText.toDouble()}"
            }
            "-" -> {}
            "÷" -> {}
            "×" -> {}
            "=" -> {}
            else -> {

            }
        }

        operand = displayText.toDouble()
        operation = op

        userIsInTheMiddleOfIntroduction = false
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = displayText,
            modifier = Modifier.fillMaxWidth().padding(8.dp).weight(1f),
            textAlign = TextAlign.Right,
            fontSize = TextUnit(70.0f, TextUnitType.Sp)
        )
        Row(modifier = Modifier.weight(1f)){
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "C",
                onButtonPressed = opPressed,
                isOperation = true
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "E",
                onButtonPressed = opPressed,
                isOperation = true
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "√",
                onButtonPressed = opPressed,
                isOperation = true
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "%",
                isOperation = true,
                onButtonPressed = opPressed,
            )
        }
        Row(modifier = Modifier.weight(1f)){
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "7",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "8",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "9",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onButtonPressed = opPressed
            )
        }
        Row(modifier = Modifier.weight(1f)){
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "4",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "5",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "6",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onButtonPressed = opPressed
            )
        }
        Row(modifier = Modifier.weight(1f)){
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "1",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "2",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "3",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "÷",
                isOperation = true,
                onButtonPressed = opPressed
            )
        }
        Row(modifier = Modifier.weight(1f)){
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "0",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = ".",
                onButtonPressed = numPressed
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                onButtonPressed = opPressed,
                isOperation = true
            )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "×",
                isOperation = true,
                onButtonPressed = opPressed
            )
        }
    }


}



@Preview(showBackground = true)
@Composable
fun CalculatorViewPreview(){
    CalculatorTheme {
        CalculatorView()
    }
}