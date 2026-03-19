package com.philiprehberger.flattenjson

import kotlin.test.*

class FlattenTest {
    @Test fun `nested maps`() {
        val input = mapOf("a" to mapOf("b" to 1, "c" to 2))
        val flat = flatten(input)
        assertEquals(1, flat["a.b"])
        assertEquals(2, flat["a.c"])
    }
    @Test fun `lists`() {
        val input = mapOf("items" to listOf("a", "b"))
        val flat = flatten(input)
        assertEquals("a", flat["items[0]"])
        assertEquals("b", flat["items[1]"])
    }
    @Test fun `nulls preserved`() {
        val flat = flatten(mapOf("a" to null))
        assertTrue(flat.containsKey("a"))
        assertNull(flat["a"])
    }
    @Test fun `custom separator`() {
        val flat = flatten(mapOf("a" to mapOf("b" to 1)), separator = "/")
        assertEquals(1, flat["a/b"])
    }
    @Test fun `prefix`() {
        val flat = flatten(mapOf("a" to 1), prefix = "cfg")
        assertEquals(1, flat["cfg.a"])
    }
    @Test fun `round trip`() {
        val input = mapOf("a" to mapOf("b" to 1, "c" to mapOf("d" to 2)))
        assertEquals(input, unflatten(flatten(input)))
    }
    @Test fun `empty map`() = assertEquals(emptyMap(), flatten(emptyMap()))
}
