package com.blackpaws.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blackpaws.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.01) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyleLarge = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 36.sp,
        color = Color.Blue
    )
    val customTextStyleSmolBlue = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp,
        color = Color.Blue
    )
    val customTextStyleSmolBlack = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp,
        color = Color.Black
    )
    val customTextStyleSmolWhite = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp,
        color = Color.White
    )

    fun convertUnits() {
        // ?: - elvis operator - It is a shorthand notation to provide a default value
        // when dealing with nullable expressions.

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.00
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
//        Text(text = "Unit Converter",
//            style = MaterialTheme.typography.headlineLarge,
//        )

        Text(text = "Unit Converter",
            style = customTextStyleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text(text = "Enter value", style = customTextStyleSmolBlack) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
//            val context = LocalContext.current
//            Button(onClick = {
//                Toast.makeText(context,"Thanks for clicking",Toast.LENGTH_LONG)
//                .show()
//            })
//            {
//                Text(text = "Click Me!")
//            }

            //Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit, style = customTextStyleSmolWhite)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(id = R.string.arrow_content_description)
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters", style = customTextStyleSmolBlue) },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters", style = customTextStyleSmolBlue) },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet", style = customTextStyleSmolBlue) },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters", style = customTextStyleSmolBlue) },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit, style = customTextStyleSmolWhite)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(id = R.string.arrow_content_description)
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters", style = customTextStyleSmolBlue) },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters", style = customTextStyleSmolBlue) },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet", style = customTextStyleSmolBlue) },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters", style = customTextStyleSmolBlue) },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Result: $outputValue $outputUnit",
            style = customTextStyleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}


/*
TextField(value = , onValueChange = )
- This is a material design component in Jetpack Compose.
- It provides a basic text input field that is typically used for single-line input.
- It can be used to enter and display text within a user interface.
- The TextField function requires specifying the value and onValueChange parameters to
handle the current value of the text field and the callback for when the value changes.
*/

/*
BasicTextField(value = ) {
}
- This is a basic text input component in Jetpack Compose.
- It provides a simple, unstyled text input field.
- BasicTextField is designed to be customizable, allowing you to apply your own styling and
behavior as needed.
- Similar to TextField, you need to specify the value parameter to represent the current
value of the text field.
- Unlike TextField, BasicTextField does not have a built-in onValueChange callback. You can
add your own TextEditingController or a custom callback to handle value changes.
*/

/*
OutlinedTextField(value = , onValueChange = )
- This is also a material design component in Jetpack Compose.
- It provides a text input field with an outlined border and label.
- It is typically used for single-line input and offers visual cues to guide the user.
- The OutlinedTextField function requires specifying the value and onValueChange parameters to handle the current value of the text field and the callback for when the value changes.
*/