git adpackage com.poznan.put.michalxpz.calculator_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.poznan.put.michalxpz.calculator_presentation.components.CalcButton
import com.poznan.put.michalxpz.core_ui.LocalSpacing

@Composable
fun CalculatorScreen() {
    val spacing = LocalSpacing.current

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            CalcButton(
                text = "1",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(spacing.mediumSmall))
            CalcButton(
                text = "2",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(spacing.mediumSmall))
            CalcButton(
                text = "3",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.width(spacing.mediumSmall))

        Row() {
            CalcButton(
                text = "4",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(spacing.mediumSmall))
            CalcButton(
                text = "5",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(spacing.mediumSmall))
            CalcButton(
                text = "6",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.width(spacing.mediumSmall))

        Row() {
            CalcButton(
                text = "7",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(spacing.mediumSmall))
            CalcButton(
                text = "8",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(spacing.mediumSmall))
            CalcButton(
                text = "9",
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview("calculator")
@Composable
fun CalcPreview() {
    CalculatorScreen()
}