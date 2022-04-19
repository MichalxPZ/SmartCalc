import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.calculator_presentation.CalculatorScreenViewModel
import com.poznan.put.michalxpz.calculator_presentation.components.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.UiEvent

@Composable
fun CalculatorScreen(
    navigate: (UiEvent) -> Unit,
    startSettingsActivity: () -> Unit,
    round: Int,
    viewModel: CalculatorScreenViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state
    val animationTime = 600
    var showSettings by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel) {
        viewModel.setCalcoulatorRound(round = round)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
        ) {
        AnimatedVisibility(
            showSettings,
            enter = slideInVertically(
                initialOffsetY = { -600 }, // small slide 300px
                animationSpec = tween(
                    durationMillis = animationTime,
                    easing = LinearEasing // interpolator
                )
            ),
            exit = slideOutVertically(
                targetOffsetY = { -600 },
                animationSpec = tween(
                    durationMillis = animationTime,
                    easing = LinearEasing
                )
            )
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .clip(RoundedCornerShape(bottomStart = 150.dp, bottomEnd = 150.dp))
                .background(Color.Cyan)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    coroutineScope.launch {
                        showSettings = true
                        delay(600)
                        startSettingsActivity()
                        delay(600)
                        showSettings = false
                    }
                }
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "",
                    modifier = Modifier.scale(2f)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {

            ResultPanel(state.input, state.result, state.errorMessage)
            CalcNumberPanel(viewModel)
        }
    }
}
@Preview("calculator")
@Composable
fun CalcPreview() {
    CalculatorScreen(
        {},
        {},
        2,
        hiltViewModel<CalculatorScreenViewModel>()
    )
}