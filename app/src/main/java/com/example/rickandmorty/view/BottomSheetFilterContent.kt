package com.example.rickandmorty.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.rickandmorty.data.model.CharacterGender
import com.example.rickandmorty.data.model.CharacterStatus
import com.example.rickandmorty.data.model.FilterModel
import com.example.rickandmorty.view.uimodel.ComposeMenu

val genderList = CharacterGender.values()
val statusList = CharacterStatus.values()

@Composable
fun BottomSheetFilterContent(
    modifier: Modifier = Modifier,
    onFilterButtonClick: (FilterModel) -> Unit
) {

    var nameState by remember { mutableStateOf("") }
    var showGenderDropdown by remember { mutableStateOf(false) }
    var showStatusDropdown by remember { mutableStateOf(false) }
    var selectedGenderIndex by remember { mutableStateOf(0) }
    var selectedStatusIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .wrapContentHeight()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameState,
            onValueChange = {
                nameState = it
            },
            label = {
                Text(text = "Name")
            }
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
        ) {

            val (genderView, statusView) = createRefs()

            ComposeMenu(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .constrainAs(genderView) {
                        start.linkTo(parent.start)
                        end.linkTo(statusView.start, margin = 4.dp)
                    },
                menuItems = genderList.map { it.value },
                title = "Gender",
                menuExpandedState = showGenderDropdown,
                selectedIndex = selectedGenderIndex,
                updateMenuExpandStatus = { showGenderDropdown = true },
                onDismissMenuView = { showGenderDropdown = false },
                onMenuItemClick = { index ->
                    selectedGenderIndex = index
                    showGenderDropdown = false
                }
            )

            ComposeMenu(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .constrainAs(statusView) {
                        start.linkTo(genderView.end, margin = 4.dp)
                        end.linkTo(parent.end)
                    },
                menuItems = statusList.map { it.value },
                title = "Status",
                menuExpandedState = showStatusDropdown,
                selectedIndex = selectedStatusIndex,
                updateMenuExpandStatus = { showStatusDropdown = true },
                onDismissMenuView = { showStatusDropdown = false },
                onMenuItemClick = { index ->
                    selectedStatusIndex = index
                    showStatusDropdown = false
                }
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val status = CharacterStatus
                    .valueOf(statusList[selectedStatusIndex].name).takeIf { it.value != "Select" }
                val gender = CharacterGender
                    .valueOf(genderList[selectedGenderIndex].name).takeIf { it.value != "Select" }
                onFilterButtonClick(
                    FilterModel(
                        name = nameState,
                        status = status,
                        gender = gender
                    )
                )
            }
        ) {
            Text(text = "Confirm")
        }
    }
}

@Preview
@Composable
fun BottomSheetFilterPreview() {
    BottomSheetFilterContent {}
}