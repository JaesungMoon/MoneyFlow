package com.prof18.moneyflow.features.home.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.prof18.moneyflow.ui.style.AppMargins
import domain.entities.BalanceRecap

@Composable
fun HomeRecap(
    balanceRecap: BalanceRecap
) {

    // TODO: fix string handling

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(AppMargins.regular)
    ) {

        Text(
            text = "Total Balance",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.gravity(Alignment.CenterHorizontally),
        )

        Row(
            modifier = Modifier.gravity(Alignment.CenterHorizontally),
        ) {
            Text(
                text = "€",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.gravity(Alignment.CenterVertically),
            )

            Spacer(Modifier.preferredWidth(AppMargins.small))

            Text(
                text = balanceRecap.totalBalance.toString(),
                style = MaterialTheme.typography.h3
            )
        }

        Spacer(Modifier.preferredHeight(AppMargins.medium))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column() {
                Text(text = "-${balanceRecap.monthlyExpenses} €", style = MaterialTheme.typography.h5)
                Text(text = "Expense", style = MaterialTheme.typography.subtitle2)
            }
            Column() {
                Text(text = "+${balanceRecap.monthlyIncome} €", style = MaterialTheme.typography.h5)
                Text(
                    text = "Income",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.gravity(Alignment.End),
                )
            }
        }

    }

}

// TODO: restore preview
//
//@Preview
//@Composable
//fun HomeRecapPreview() {
//    MoneyFlowTheme {
//        HomeRecap()
//    }
//}
//
//@Preview
//@Composable
//fun HomeRecapDarkPreview() {
//    MoneyFlowTheme(darkTheme = true) {
//        HomeRecap()
//    }
//}