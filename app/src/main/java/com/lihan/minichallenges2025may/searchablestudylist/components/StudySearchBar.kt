package com.lihan.minichallenges2025may.searchablestudylist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges2025may.R
import com.lihan.minichallenges2025may.ui.montserrat
import com.lihan.minichallenges2025may.ui.theme.HigherSurface
import com.lihan.minichallenges2025may.ui.theme.Icon
import com.lihan.minichallenges2025may.ui.theme.MiniChallenges2025MayTheme
import com.lihan.minichallenges2025may.ui.theme.Primary


@Composable
fun StudySearchBar(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search by topic or subject",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = montserrat,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        color = MaterialTheme.colorScheme.Icon,
        textAlign = TextAlign.Left
    ),
    shape: RoundedCornerShape = RoundedCornerShape(28.dp),
    modifier: Modifier = Modifier
){
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val borderColor = remember(text){
        if (text.isEmpty()){
            Color.Transparent
        }else{
            Primary
        }
    }

    val backgroundColor  = if (text.isEmpty()){
        MaterialTheme.colorScheme.HigherSurface
    }else{
        Color.White
    }

    BasicTextField(
        modifier = modifier
            .clip(shape)
            .border(1.dp, color = borderColor,shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
        ,
        value = text,
        onValueChange = onValueChange,
        decorationBox = { inner ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.5.dp),
                horizontalArrangement = Arrangement.spacedBy(12.5.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.study_search),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.Icon.copy(alpha = 0.5f)
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    if (text.isEmpty()){
                        Text(
                            text = placeholder,
                            style = textStyle
                        )
                    }
                    inner()
                }
            }
        },
        textStyle = textStyle,
        maxLines = 1,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboard?.hide()
                focusManager.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}


@Composable
@Preview(showBackground = true)
private fun StudySearchBarPreview(){
    MiniChallenges2025MayTheme {
        StudySearchBar(
            text = "",
            onValueChange = {

            }
        )
    }
}