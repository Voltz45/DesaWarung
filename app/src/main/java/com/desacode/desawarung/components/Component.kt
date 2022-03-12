package com.desacode.desawarung.components

import android.text.style.AlignmentSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.desacode.desawarung.R
import com.desacode.desawarung.navigation.WarungBottomNavItems
import com.desacode.desawarung.navigation.WarungScreens
import com.desacode.desawarung.ui.theme.*

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
fun Greeting(
    greeting: String,
    caption: String,
    userName: Boolean = false,
    textStyleGreeting: TextStyle = MaterialTheme.typography.h3,
    textStyleCaption: TextStyle = MaterialTheme.typography.body1
) {
    Text(
        buildAnnotatedString {
            withStyle(ParagraphStyle(lineHeight = if (userName) 25.sp else 30.sp)) {
                withStyle(
                    SpanStyle(
                        fontFamily = Poppins,
                        fontSize = if (userName) 30.sp else 42.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(greeting + "\n")
                }

                withStyle(
                    SpanStyle(
                        fontFamily = Poppins,
                        fontSize = 16.sp
                    )
                ) {
                    append(caption)
                }
            }
        }
    )
//    Column {
//        Text(
//            text = greeting,
//            style = textStyleGreeting
//        )
//        Text(
//            text = caption,
//            style = textStyleCaption
//        )
//    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    state: MutableState<String>,
    typeField: String = "textField",
    placeHolder: String,
    leadingIcon: ImageVector = Icons.Default.Email,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    when (typeField) {
        "textField" -> {
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
                        imageVector = leadingIcon,
                        contentDescription = "Email_icon"
                    )
                },
                keyboardActions = onAction,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                )
            )
        }
        "outlinedField" -> {
            OutlinedTextField(
                value = state.value,
                singleLine = isSingleLine,
                enabled = enabled,
                textStyle = TextStyle(
                    fontFamily = Poppins,
                    fontSize = 14.sp
                ),
                modifier = Modifier.height(56.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = BlueDesa,
                    cursorColor = CursorColor,
                    backgroundColor = Color.Transparent
                ),
                onValueChange = { state.value = it },
                placeholder = { Text(text = placeHolder, fontFamily = Poppins, fontSize = 14.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = "Email_icon"
                    )
                },
                keyboardActions = onAction,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                )
            )
        }
        "customField" -> {
            CustomTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = IconColor.copy(0.8f)
                    )
                },
                placeholderText = "Test",
                onAction = onAction
            )
        }
    }
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
fun SearchInput(
    modifier: Modifier = Modifier,
    state: MutableState<String>,
    typeField: String = "textField",
    placeHolder: String = "Email",
    leadingIcon: ImageVector = Icons.Default.Email,
    enabled: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        state = state,
        typeField = typeField,
        placeHolder = placeHolder,
        leadingIcon = leadingIcon,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    val icon = if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    val colorIcon = if (visible) BlueDesa else DefaultIconColor
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(imageVector = icon, contentDescription = "pass", tint = colorIcon)
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
        trailingIcon = {
            PasswordVisibility(passwordVisibility = passwordVisibility)
        },
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkState.value,
            onCheckedChange = { checkState.value = it },
            modifier = Modifier.padding(end = 5.dp),
            colors = CheckboxDefaults.colors(BlueDesa)
        )
        Text(
            text = caption,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 5.dp)
        )
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

@Composable
fun WarungTopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    isHomeScreen: Boolean = true
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isHomeScreen) {
            Greeting(
                greeting = "Hi, James",
                caption = "superadmin",
                userName = true,
                textStyleGreeting = MaterialTheme.typography.h4
            )

            IconButton(onClick = { navController.navigate(WarungScreens.NotificationScreen.name) }) {
                BadgedBox(badge = { Badge(backgroundColor = BlueDesa) { Text(text = "999") } }) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "Notification_icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun WarungBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    navItems: List<WarungBottomNavItems>,
    currentRoute: String?
) {
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        ),
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        BottomNavigation(
            modifier = modifier
                .height(100.dp)
                .fillMaxWidth(),
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            navItems.forEach { navItem ->
                if (navItem.icon != 0) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = navItem.icon),
                                contentDescription = navItem.title,
                                modifier = modifier.size(30.dp),
                            )
                        },
                        label = {
                            Text(
                                text = navItem.title,
                                fontWeight = if (currentRoute == navItem.route) FontWeight.ExtraBold else FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        },
                        selectedContentColor = BlueDesa,
                        unselectedContentColor = IconColor.copy(0.8f),
                        enabled = navItem.enabled,
                        onClick = { navController.navigate(navItem.route) },
                        selected = currentRoute == navItem.route
                    )
                } else {
                    //Empty
                    BottomNavigationItem(
                        selected = false,
                        enabled = navItem.enabled,
                        icon = {},
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = 14.sp,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    var text by rememberSaveable { mutableStateOf("") }

    BasicTextField(
        modifier = modifier
            .background(
                MaterialTheme.colors.surface,
                MaterialTheme.shapes.small,
            ),
        value = text,
        onValueChange = {
            text = it
        },
        keyboardActions = onAction,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = fontSize,
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .border(2.dp, Color.LightGray, shape = RoundedCornerShape(4.dp))
                    .padding(10.dp)
                    .fillMaxWidth(.8f)
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    modifier = modifier.padding(start = 5.dp),
                ) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }

                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Composable
fun FabButton(navController: NavController) {
    FloatingActionButton(
        backgroundColor = BlueDesa,
        onClick = { navController.navigate(WarungScreens.CameraViewScreen.name) }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.scanner_icon),
            contentDescription = "scan_icon",
            tint = Color.White,
            modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun DropDownMenu(filterState: MutableState<String>, expandedState: MutableState<Boolean>) {
    BasicTextField(
        modifier = Modifier
            .background(
                MaterialTheme.colors.surface,
                MaterialTheme.shapes.small,
            ),
        readOnly = true,
        value = filterState.value,
        onValueChange = {
            filterState.value = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    expandedState.value = !expandedState.value
                }
            ) {
                Box {
                    if (filterState.value.isEmpty())
                        Text(
                            "Category",
                            style = LocalTextStyle.current.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    innerTextField()
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = ""
                )
            }
        }
    )

    DropdownMenu(
        expanded = expandedState.value,
        modifier = Modifier.requiredSizeIn(maxHeight = 200.dp),
        onDismissRequest = { expandedState.value = !expandedState.value }) {
        for (i in 1..2) {
            DropdownMenuItem(
                onClick = {
                    expandedState.value = !expandedState.value
                }
            ) {
                Text(
                    text = "Orange Juice",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SearchBar(searchState: MutableState<String>, keyboardController: FocusManager) {
    SearchInput(
        state = searchState,
        enabled = true,
        placeHolder = "Search",
        typeField = "customField",
        leadingIcon = Icons.Default.Search,
        onAction = KeyboardActions {
            keyboardController.clearFocus()
        }
    )

    Icon(
        imageVector = Icons.Default.Sort,
        contentDescription = "Sort_icon",
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        tint = IconColor.copy(0.8f)
    )

    Icon(
        painter = painterResource(id = R.drawable.filter_icon),
        contentDescription = "Filter_icon",
        tint = IconColor.copy(0.8f)
    )
}

@Composable
fun ProductCard(divideWidth: Dp) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(divideWidth)
            .border(2.dp, BorderColor, RoundedCornerShape(10.dp)),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.example_product),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                )
            }

            Text(
                text = "Orange Juice",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 5.dp)
            )

            Text(
                text = "Rp 15000",
                color = Color(0xff40BFFF),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 5.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
            ) {
                Text(text = "24 Pcs", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Red.copy(0.6f))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(
                            //Disable Ripple Effect
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { }
                )
            }
        }
    }
}