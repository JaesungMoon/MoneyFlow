package com.prof18.moneyflow.features.addtransaction.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.prof18.moneyflow.R
import java.util.*

fun getYearList() = (2015..2023).map { it.toString() }
fun getMonthList() = (1..12).map { it.toString() }
fun getDayList() = (1..31).map { it.toString() }

@Composable
fun DatePickerDialog(
    showDialog: Boolean,
    setDialogVisible: (Boolean) -> Unit,
    onYearSelected: (Int) -> Unit,
    onMonthSelected: (Int) -> Unit,
    onDaySelected: (Int) -> Unit,
    onSave: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                setDialogVisible(false)
            },
            title = {
                Text(text = stringResource(id = R.string.select_date))
            },
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DatePickerItemDropdownMenu(
                        initialText = Calendar.getInstance().get(Calendar.YEAR).toString(),
                        itemList = getYearList(),
                        onItemSelected = {
                            onYearSelected(it.toInt())
                        }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    DatePickerItemDropdownMenu(
                        initialText = (Calendar.getInstance().get(Calendar.MONTH) + 1).toString(),
                        itemList = getMonthList(),
                        onItemSelected = {
                            onMonthSelected(it.toInt())
                        }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    DatePickerItemDropdownMenu(
                        initialText = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString(),
                        itemList = getDayList(),
                        onItemSelected = {
                            onDaySelected(it.toInt())
                        }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    onSave()
                    setDialogVisible(false)
                }) {
                    Text(stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                Button(onClick = {
                    setDialogVisible(false)
                }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }
}

@Composable
private fun DatePickerItemDropdownMenu(
    initialText: String,
    itemList: List<String>,
    onItemSelected: (String) -> Unit,
) {
    val (dropdownText, setDropdownText) = remember { mutableStateOf(initialText) }
    val (isMenuExpanded, setMenuExpanded) = remember { mutableStateOf(false) }

    DropdownMenu(
        toggle = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(onClick = {
                    setMenuExpanded(true)
                })
            ) {
                Text(
                    text = dropdownText,
                    style = MaterialTheme.typography.body1
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    modifier = Modifier.size(16.dp),
                    asset = Icons.Filled.KeyboardArrowDown
                )
            }
        },
        expanded = isMenuExpanded,
        onDismissRequest = {
            setMenuExpanded(false)
        }
    ) {
        itemList.forEach {
            DropdownMenuItem(onClick = {
                setDropdownText(it)
                setMenuExpanded(false)
                onItemSelected(it)
            }) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

