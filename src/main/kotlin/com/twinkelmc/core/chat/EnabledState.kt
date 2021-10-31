package com.twinkelmc.core.chat

data class EnabledState(
    val bool: Boolean
) {
    fun toLang(lang: String): String {
        return when(lang) {
            "nl" -> if (bool) "Ingeschakeld" else "Uitgeschakeld"
            "de" -> if (bool) "LOL" else "LOL OFF"
            else -> if (bool) "Enabled" else "Disabled"
        }
    }
}