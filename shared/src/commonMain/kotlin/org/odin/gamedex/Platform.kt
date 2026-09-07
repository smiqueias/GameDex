package org.odin.gamedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform