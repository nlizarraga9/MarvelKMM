package com.example.marvelkmm.utils

import platform.Foundation.NSDate

actual fun getCurrentTimestamp(): Long {
    return (NSDate().timeIntervalSince1970 * 1000).toLong()
}

actual fun md5(input: String): String = "not implemented"