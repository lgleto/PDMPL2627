package upca.example.calculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import upca.example.calculator.ui.theme.CalculatorTheme


@Composable
fun CalcButton(
    modifier: Modifier = Modifier,
    label : String = "0",
    isOperation : Boolean = false,
    onButtonPressed : (String)->Unit
){
    Button(
        onClick = {
            onButtonPressed(label)
        },
        modifier = modifier.padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOperation)
                MaterialTheme.colorScheme.secondary
            else
                MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview(){
    CalculatorTheme {
        CalcButton(){}
    }
}