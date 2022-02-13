package com.desacode.desawarung.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desacode.desawarung.R
import com.desacode.desawarung.ui.theme.BlueDesa
import com.desacode.desawarung.ui.theme.CursorColor

@Composable
fun WarungLogo() {
    Image(
        painterResource(id = R.drawable.desa_logo),
        contentDescription = "Logo Icon",
        modifier = Modifier
            .size(50.dp),
    )
}

@Composable
fun Greeting(greeting: String, caption: String) {
    Column {
        Text(
            text = greeting,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = caption,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    state: MutableState<String>,
    placeHolder: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        value = state.value,
        singleLine = isSingleLine,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = BlueDesa,
            cursorColor = CursorColor,
            backgroundColor = Color.Transparent
        ),
        onValueChange = { state.value = it },
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email_icon"
            )
        },
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction)
    )
}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    placeHolder: String = "Email",
    enabled: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        state = emailState,
        placeHolder = placeHolder,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(imageVector = Icons.Default.Clear, contentDescription = "pass")
    }
}

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    passwordState: MutableState<String>,
    placeHolder: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    val visualTransformation =
        if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()

    TextField(
        value = passwordState.value,
        singleLine = true,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = BlueDesa,
            cursorColor = CursorColor,
            backgroundColor = Color.Transparent
        ),
        onValueChange = { passwordState.value = it },
        visualTransformation = visualTransformation,
        placeholder = { Text(text = placeHolder) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lock_icon") },
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
    )
}

@Composable
fun CheckBox(
    checkState: MutableState<Boolean>,
    caption: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 15.dp)
    ) {
        Checkbox(
            checked = checkState.value,
            onCheckedChange = { checkState.value = it },
            modifier = Modifier.padding(end = 5.dp),
            colors = CheckboxDefaults.colors(BlueDesa)
        )
        Text(text = caption, style = MaterialTheme.typography.caption)
    }
}

@Composable
fun SignButton(
    label: String,
    showIcon: Boolean = false,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onAction,
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(BlueDesa)
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label, modifier = Modifier.padding(end = 5.dp, bottom = 2.dp),
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                if (showIcon) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "right",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}
