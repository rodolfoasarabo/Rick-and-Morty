package com.example.rickandmorty.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CharacterView(
    modifier: Modifier = Modifier,
    characterUiModel: CharacterUiModel,
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    Column(
        modifier = modifier
            .width(72.dp)
            .padding(8.dp)
            .clickable {
                onCharacterClick(characterUiModel)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(72.dp)
                .height(72.dp)
                .clip(CircleShape),
            placeHolder = painterResource(id = R.drawable.rickmorty),
            imageModel = characterUiModel.image
        )
        Text(
            text = characterUiModel.name,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}