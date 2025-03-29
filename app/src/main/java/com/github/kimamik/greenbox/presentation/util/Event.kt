package com.github.kimamik.greenbox.presentation.util

data class Event<T>(
    private val data: T?,
    private val timestamp: Long = System.currentTimeMillis()
) {
    private var consumed = false
    fun consume(consumer: (T) -> Unit) {
        if (!consumed) {
            consumed = true
            data?.let(consumer)
        }
    }
}