package com.qxd.acessaedu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform