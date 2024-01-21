package com.study.webflux.demoforwebclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoForWebClientApplication

fun main(args: Array<String>) {
    runApplication<DemoForWebClientApplication>(*args)

    //WebClient 등록된 메서드 호출 가능
    //val api = WebClientAPI()
    val api = WebClientAPIUsingExchange()

    api.getAllEmployees()
        .thenMany(api.saveEmployees())
        .thenMany(api.getAllEmployees())
        .thenMany(api.updateEmployees())
        .thenMany(api.getAllEmployees())
        .thenMany(api.deleteEmployees("123"))
        .thenMany(api.getAllEmployees())
        .subscribe()
}
