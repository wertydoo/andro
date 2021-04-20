package com.brianwilens.andro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class AndroApplication

fun main(args: Array<String>) {
    runApplication<AndroApplication>(*args)
}
