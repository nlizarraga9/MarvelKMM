package com.example.marvelkmm.utils

import java.security.MessageDigest

actual fun getCurrentTimestamp(): Long = System.currentTimeMillis()

actual fun md5(input: String): String {
    val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}