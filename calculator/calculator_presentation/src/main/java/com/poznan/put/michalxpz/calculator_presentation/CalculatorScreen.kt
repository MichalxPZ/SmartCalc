import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.calculator_presentation.CalculatorScreenViewModel
import com.poznan.put.michalxpz.calculator_presentation.components.*
import com.poznan.put.michalxpz.core_ui.LocalSpacing
import util.UiEvent

@Composable
fun CalculatorScreen(
    navigate: (UiEvent) -> Unit,
    viewModel: CalculatorScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        ResultPanel(state.input, state.result)
        CalcNumberPanel(viewModel)
    }
}

@Preview("calculator")
@Composable
fun CalcPreview() {
    CalculatorScreen({}, hiltViewModel<CalculatorScreenViewModel>())
}