package com.github.kimamik.greenbox.domain.common


sealed interface GBResult<out S, out E> {
    data class Success<out S, E>(val data: S) : GBResult<S, E>
    data class Error<S, out E>(val data: E) : GBResult<S, E>
}

inline fun <S, E> GBResult<S, E>.valueOr(alternative: (E) -> S): S {
    return when (this) {
        is GBResult.Error -> alternative(data)
        is GBResult.Success -> data
    }
}
