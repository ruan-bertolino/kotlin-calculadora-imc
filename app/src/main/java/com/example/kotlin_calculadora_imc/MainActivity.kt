package com.example.kotlin_calculadora_imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

object ImcLimites {
    const val ABAIXO_PESO = 18.5
    const val PESO_NORMAL_MAX = 24.9
    const val SOBREPESO_MAX = 29.9
}

fun calcularIMC(peso: Double, altura: Double): Double {
    return peso / (altura * altura)
}

fun getImcClassification(imc: Double): String {
    return when {
        imc < ImcLimites.ABAIXO_PESO -> "Abaixo do peso"
        imc <= ImcLimites.PESO_NORMAL_MAX -> "Peso normal"
        imc <= ImcLimites.SOBREPESO_MAX -> "Sobrepeso"
        else -> "Obesidade"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                CalculadoraImcLayout()
            }
        }
    }
}

@Composable
fun CalculadoraImcLayout() {
    var weightInput by remember { mutableStateOf("") }
    var heightInput by remember { mutableStateOf("") }
    var roundResult by remember { mutableStateOf(false) }

    var imcResult by remember { mutableStateOf<Double?>(null) }
    var classification by remember { mutableStateOf("") }

    val onCalculateClicked = {
        val weight = weightInput.replace(',', '.').toDoubleOrNull() ?: 0.0
        val height = heightInput.replace(',', '.').toDoubleOrNull() ?: 0.0

        if (weight > 0.0 && height > 0.0) {
            val imc = calcularIMC(weight, height)
            imcResult = imc
            classification = getImcClassification(imc)
        } else {
            imcResult = null
            classification = "Preencha os campos corretamente com valores maiores que zero."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calcular IMC",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        EditNumberField(
            label = "Peso (kg)",
            value = weightInput,
            onValueChange = { weightInput = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField(
            label = "Altura (m)",
            value = heightInput,
            onValueChange = { heightInput = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Arredondar resultado")
            Switch(
                checked = roundResult,
                onCheckedChange = { roundResult = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onCalculateClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier = Modifier.height(32.dp))

        val formattedImc = if (imcResult != null) {
            if (roundResult) {
                DecimalFormat("#,##0").format(imcResult)
            } else {
                DecimalFormat("#,##0.00").format(imcResult)
            }
        } else {
            "--"
        }

        Text(
            text = "IMC: $formattedImc",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = classification,
            style = MaterialTheme.typography.bodyLarge,
            color = if (imcResult != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun EditNumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}