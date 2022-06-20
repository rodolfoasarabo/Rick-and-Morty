package com.example.rickandmorty.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BottomSheetCharacterContent(
    modifier: Modifier = Modifier,
    character: CharacterUiModel?
) {
    Column(modifier = modifier.wrapContentHeight()) {
        Spacer(modifier = Modifier.height(1.dp))
        character?.let { characterSafe ->
            with(characterSafe) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        GlideImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(72.dp)
                                .height(72.dp)
                                .clip(CircleShape),
                            placeHolder = painterResource(id = R.drawable.rickmorty),
                            imageModel = image
                        )
                        Column(
                            modifier = Modifier
                                .height(72.dp)
                                .padding(start = 8.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = name,
                                maxLines = 2,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val color = when (status) {
                                    "Alive" -> Color.Green
                                    "Dead" -> Color.Red
                                    else -> Color.Gray
                                }
                                Image(
                                    modifier = Modifier.size(12.dp),
                                    colorFilter = ColorFilter.tint(color),
                                    painter = painterResource(id = R.drawable.dot),
                                    contentDescription = "",
                                )
                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    fontSize = 14.sp,
                                    text = "$status · $species"
                                )
                            }
                        }
                    }
                    Divider()
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            fontSize = 14.sp,
                            color = Color.Gray,
                            text = "Last known location:"
                        )
                        Text(
                            fontSize = 14.sp,
                            text = location.name
                        )
                    }
                }
            }
        }
    }
}