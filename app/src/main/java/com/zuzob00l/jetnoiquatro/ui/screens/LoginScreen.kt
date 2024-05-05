package com.zuzob00l.jetnoiquatro.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.zuzob00l.jetnoiquatro.R
import com.zuzob00l.jetnoiquatro.ui.theme.Default50
import com.zuzob00l.jetnoiquatro.ui.theme.Green800
import com.zuzob00l.jetnoiquatro.ui.theme.Neutral900

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(

    onClickLogin: (/*String, String*/) -> Unit = {},
    onClickGoogle: () -> Unit = {}
)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)

    {
        val logo_image = painterResource(id = R.drawable.login_logo)

       Icon(
           modifier = Modifier
               .sizeIn(minWidth = 100.dp, minHeight = 100.dp)
               .padding(bottom = 30.dp),
           painter = logo_image,
           tint = Color.Unspecified,
           contentDescription = "logo image")
        
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            text = "Zaloguj się do\r\nswojego konta")

        EmailTextField(
            text = email,
            onValueChange = { /*str -> email = str*/},
            textLabel = "Email")

        PasswordTextField(
            text = password,
            textLabel = "Password",
            onValueChange = {} )

        Box(
            modifier = Modifier
               // .padding(end = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
            )
        {
            Text(
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.SemiBold,
                color = Green800,
                text = "Zapomniałeś hasla?") }

            OutlinedButton(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(Green800),
                onClick = { onClickLogin(/*email, password*/) })
            {
                Text(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    text = "Zaloguj się")
            }
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10),
                onClick = { onClickGoogle })
            {
                val google_icon = ImageBitmap.imageResource(R.drawable.ic_google)

                Row(horizontalArrangement = Arrangement.Center) {

                Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(25.dp, 25.dp),
                    tint = Color.Unspecified,
                    bitmap = google_icon,
                    contentDescription = null)

                Text(
                    color = Color.Black,
                    text = "Zaloguj sie z Google")
                }
            }

            Box(
                modifier = Modifier.padding(top = 30.dp),
                contentAlignment = Alignment.BottomCenter) {
                Row() {
                    Text(
                        color = Color.Black,
                        text = "Nie masz konta?")

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        color = Green800,
                        fontWeight = FontWeight.SemiBold,
                        text = "Rejestracja")
                }
            }
    }
}
@ExperimentalMaterial3Api
@Composable
fun EmailTextField(
    text: String,
    onValueChange: (String) -> Unit,
    textLabel: String)
{
     val emailIcon = Icons.Filled.Email

    Column() {

        Text(
            modifier = Modifier.padding(start = 16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            text = "Adres email")

        TextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
            value = text,
            onValueChange = onValueChange,
            label = { Text(text = textLabel) },
            leadingIcon = { Icon(imageVector = emailIcon, contentDescription = "email icon")})
    }
}
@ExperimentalMaterial3Api
@Composable
fun PasswordTextField(
    text: String,
    textLabel: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            text = "Password")

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = text,
            onValueChange = onValueChange,
            label = { Text(text = textLabel) },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
            visualTransformation = if(passwordVisible) { VisualTransformation.None }
                                   else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if(passwordVisible) Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible })
            {
                Icon(
                imageVector = image,
                contentDescription = null)}
            }
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
