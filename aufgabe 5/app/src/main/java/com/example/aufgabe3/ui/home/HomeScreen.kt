package com.example.aufgabe3.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aufgabe3.model.BookingEntry
import com.example.aufgabe3.ui.theme.Beere
import com.example.aufgabe3.ui.theme.Pink80
import com.example.aufgabe3.ui.theme.Pinki
import com.example.aufgabe3.ui.theme.Rouge
import com.example.aufgabe3.viewmodel.SharedViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val bookingsEntries by sharedViewModel.bookingsEntries.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Entries by Lino Hotels" ,color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Rouge
                )
                )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add")
            },
                containerColor = Rouge)
            {
                Icon(Icons.Default.Add, contentDescription = "Add booking", tint = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Pink80)
        ) {
            if(bookingsEntries.isEmpty()){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Pink80)
                ){
                    Text("No Entries. Let's make some :)", modifier = Modifier.align(Alignment.Center), color = Color.White
                    )
                }

            }
            else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(bookingsEntries) { bookingEntry ->
                        BookingEntryItem(
                            booking = bookingEntry,
                            onDeleteClick = { sharedViewModel.deleteBookingEntry(bookingEntry) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookingEntryItem(
    booking: BookingEntry,
    onDeleteClick: () -> Unit
) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(Pinki)
                .padding(8.dp)

        ) {
            Column(
                modifier = Modifier.weight(1f)

            ) {
                Text(
                    text = booking.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${booking.arrivalDate.format(dateFormatter)} - ${booking.depatureDate.format(dateFormatter)}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, tint = Color.Red, contentDescription = "Delete booking")
            }
        }
    }
}
