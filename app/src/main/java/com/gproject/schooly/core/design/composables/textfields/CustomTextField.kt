package com.gproject.schooly.core.design.composables.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.gproject.schooly.core.design.composables.texts.CustomText
import com.gproject.schooly.core.design.composables.texts.TextSize
import com.gproject.schooly.core.design.theme.Palette
import com.gproject.schooly.core.utils.r

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    label: String = "",
    hint: String = "",
    enabled: Boolean = true,
    isSecure: Boolean = false,
    imeAction: ImeAction = ImeAction.Next
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CustomText(
            label, size = TextSize.S14, color = Palette.character.title85
        )
        OutlinedTextField(

            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Palette.neutral.color5
            ),
            label = {
                CustomText(text = hint, size = TextSize.S14)
            },
            enabled = enabled,

            shape = RoundedCornerShape(8.r()),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isSecure) KeyboardType.Password else KeyboardType.Unspecified,
                imeAction = imeAction
            ),
            visualTransformation = if (isSecure) PasswordVisualTransformation() else VisualTransformation.None
        )
    }

}

@Preview
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(
        onValueChange = {},
        value = "",
        label = "email",
    )
}