package com.example.aufgabe3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.aufgabe3.model.BookingEntry
import com.example.aufgabe3.ui.home.BookingEntryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.util.Locale

class SharedViewModel: ViewModel() {
    private val _bookingsEntries = MutableStateFlow<List<BookingEntry>>(emptyList())
    val bookingsEntries: StateFlow<List<BookingEntry>> = _bookingsEntries

    fun addBookingEntry(name : String, arrivalDate : LocalDate, departeDate : LocalDate){
        val currentList = _bookingsEntries.value
        val newEntry = BookingEntry(name = name, arrivalDate = arrivalDate, depatureDate = departeDate)
        _bookingsEntries.value = currentList + newEntry
    }

    fun deleteBookingEntry(entry: BookingEntry){
        _bookingsEntries.value = _bookingsEntries.value.filter { it != entry }
    }
}