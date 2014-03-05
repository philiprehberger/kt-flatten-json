package com.philiprehberger.flattenjson

/** Flattens a nested map to dot-notation keys. Lists use `[index]` notation. */
@Suppress("UNCHECKED_CAST")
public fun flatten(
    map: Map<String, Any?>,
    separator: String = ".",
    prefix: String = "",
    maxDepth: Int = Int.MAX_VALUE,
): Map<String, Any?> {
    val result = mutableMapOf<String, Any?>()
    doFlatten(map, prefix, separator, 0, maxDepth, result)
    return result
}

@Suppress("UNCHECKED_CAST")
private fun doFlatten(obj: Any?, path: String, sep: String, depth: Int, maxDepth: Int, result: MutableMap<String, Any?>) {
    when {
        obj is Map<*, *> && depth < maxDepth -> {
            (obj as Map<String, Any?>).forEach { (k, v) ->
                val newPath = if (path.isEmpty()) k else "$path$sep$k"
                doFlatten(v, newPath, sep, depth + 1, maxDepth, result)
            }
        }
        obj is List<*> && depth < maxDepth -> {
            obj.forEachIndexed { i, v ->
                doFlatten(v, "$path[$i]", sep, depth + 1, maxDepth, result)
            }
        }
        else -> result[path] = obj
    }
}

/** Unflattens a dot-notation map back to a nested structure. */
@Suppress("UNCHECKED_CAST")
public fun unflatten(map: Map<String, Any?>, separator: String = "."): Map<String, Any?> {
    val result = mutableMapOf<String, Any?>()
    for ((key, value) in map) {
        val parts = parseKey(key, separator)
        setNested(result, parts, value)
    }
    return convertLists(result) as Map<String, Any?>
}

private fun parseKey(key: String, separator: String): List<String> {
    val parts = mutableListOf<String>()
    for (segment in key.split(separator)) {
        val arrMatch = Regex("^(.+?)\\[(\\d+)\\]$").find(segment)
        if (arrMatch != null) {
            parts.add(arrMatch.groupValues[1])
            parts.add(arrMatch.groupValues[2])
        } else {
            parts.add(segment)
        }
    }
    return parts
}

@Suppress("UNCHECKED_CAST")
private fun setNested(map: MutableMap<String, Any?>, parts: List<String>, value: Any?) {
    var current: MutableMap<String, Any?> = map
    for (i in 0 until parts.size - 1) {
        val part = parts[i]
        current = current.getOrPut(part) { mutableMapOf<String, Any?>() } as MutableMap<String, Any?>
    }
    current[parts.last()] = value
}

@Suppress("UNCHECKED_CAST")
private fun convertLists(obj: Any?): Any? {
    if (obj !is Map<*, *>) return obj
    val map = obj as Map<String, Any?>
    val allNumeric = map.keys.all { it.all { c -> c.isDigit() } }
    return if (allNumeric && map.isNotEmpty()) {
        map.entries.sortedBy { it.key.toInt() }.map { convertLists(it.value) }
    } else {
        map.mapValues { convertLists(it.value) }
    }
}
