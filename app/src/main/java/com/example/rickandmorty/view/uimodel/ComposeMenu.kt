package com.example.rickandmorty.view.uimodel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun MenuPreview() {
    ComposeMenu(
        modifier = Modifier.fillMaxWidth(),
        menuItems = listOf("male", "female"),
        title = "Gender",
        menuExpandedState = false,
        selectedIndex = 0,
        updateMenuExpandStatus = { /*TODO*/ },
        onDismissMenuView = { /*TODO*/ },
        onMenuItemClick = { }
    )
}

@Composable
fun ComposeMenu(
    modifier: Modifier = Modifier,
    menuItems: List<String>,
    title: String,
    menuExpandedState: Boolean,
    selectedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {
                updateMenuExpandStatus()
            }
            .background(Color.Gray.copy(0.3f), RoundedCornerShape(2.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier.height(48.dp)
        ) {
            val (label, value) = createRefs()

            Text(
                text = title,
                modifier = Modifier.constrainAs(label) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                fontSize = 10.sp
            )
            Text(
                text = menuItems[selectedIndex],
                modifier = Modifier.constrainAs(value) {
                    top.linkTo(label.bottom)
                    start.linkTo(label.start)
                }
            )

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() }
            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(
                        onClick = {
                            onMenuItemClick(index)
                        }
                    ) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}