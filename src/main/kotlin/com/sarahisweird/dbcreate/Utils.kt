package com.sarahisweird.dbcreate

fun List<String>.joinToStringWithAnd(separator: String = ", "): String {
    if (isEmpty()) return ""
    if (count() == 1) return first()

    val copy = toMutableList()
    val last = copy.removeLast()

    return "${copy.joinToString(separator)} and $last"
}