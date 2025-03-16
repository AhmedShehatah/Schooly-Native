package com.gproject.schooly.core.design.composables.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gproject.schooly.core.design.theme.Palette

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier, length: Int = 6,
    onValueChange: (String) -> Unit,
) {
    val otpValue = remember { mutableStateOf("") }

    BasicTextField(
        value = otpValue.value,
        onValueChange = { newValue ->
            if (newValue.length <= length && newValue.all { it.isDigit() }) {
                otpValue.value = newValue
                onValueChange(newValue)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(length) { index ->
                    val char = otpValue.value.getOrNull(index)?.toString() ?: ""
                    val isFocused = otpValue.value.length == index

                    BasicTextField(
                        value = char,
                        onValueChange = {},
                        enabled = false, // Disable direct editing of individual boxes
                        modifier = Modifier
                            .size(40.dp)
                            .border(
                                width = 1.dp,
                                color = if (isFocused) Palette.primary.color6 else Palette.neutral.color6,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp)),
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            Text(
                                text = char,
                                modifier = Modifier
                                    .align(Alignment.Center)

                            )
                        }

                    }
                }
            }

        })
}

@Preview
@Composable
fun OtpInputFieldPreview() {
    OtpInputField(onValueChange = {})
}