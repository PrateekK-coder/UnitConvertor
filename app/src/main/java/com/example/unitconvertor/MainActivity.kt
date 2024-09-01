package com.example.unitconvertor

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    UnitConvertor(Modifier,innerPadding)


                }
            }
        }
    }
}

@Composable
fun UnitConvertor(modifier: Modifier=Modifier, innerPadding: PaddingValues) {
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember {mutableStateOf("Meters") }
    var iexpanded by remember {mutableStateOf(false)}
    var oexpanded by remember {mutableStateOf(false)}
    var conversionFactor = remember {mutableStateOf(1.0)}
    var oconversionFactor = remember {mutableStateOf(1.0)}


    fun convert(){
        val inputvalue=inputValue.toDoubleOrNull()?: 0.00
        val result=(inputvalue*conversionFactor.value *100.0 / oconversionFactor.value).roundToInt()/100.0
        outputValue= result.toString()

    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()) {

        Text(text = "Unit Convertor",
            style = MaterialTheme.typography.headlineMedium,
            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = inputValue,
            onValueChange = {///home/kadane/.var/app/com.google.AndroidStudio/config/.android/avd/Pixel_8_Pro_API_33.avd/
                inputValue=it
                convert()},
            placeholder = { Text(text = "Enter the value") }

        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            val context = LocalContext.current
            //Input  Box
            Box{
                Button(onClick = {iexpanded=true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iexpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.value =0.01
                        convert()

                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {  iexpanded=false

                        inputUnit="Meters"
                        conversionFactor.value =1.0
                        convert() })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = {  iexpanded=false
                        inputUnit="Feets"

                        conversionFactor.value =0.3048
                        convert() })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = { iexpanded=false
                        inputUnit="Millimeters"

                        conversionFactor.value =0.001
                        convert() })
                    DropdownMenuItem(text = { Text(text = "Inches") }, onClick = { iexpanded=false
                        inputUnit= "Inches"

                        conversionFactor.value =0.0254
                        convert() })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box{
                Button(onClick = { oexpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = {oexpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oexpanded=false
                        outputUnit="Centimeters"
                        oconversionFactor.value =0.01
                        convert() })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oexpanded=false
                        outputUnit="Meters"
                        oconversionFactor.value =1.00
                        convert()  })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = {
                        oexpanded=false
                        outputUnit="Feets"
                        oconversionFactor.value =0.3048
                        convert() })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        oexpanded=false
                        outputUnit="Millimeters"
                        oconversionFactor.value =0.001
                        convert()  })
                    DropdownMenuItem(text = { Text(text = "Inches") }, onClick = {
                        oexpanded=false
                        outputUnit="Inches"
                        oconversionFactor.value =0.0254
                        convert() })
                }

            }

        }
        Spacer(modifier = Modifier.height(100.dp))

        Text(text = "Result:  $outputValue $outputUnit ",
            style = MaterialTheme.typography.headlineMedium,
            )
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
UnitConvertor(innerPadding = PaddingValues())
}