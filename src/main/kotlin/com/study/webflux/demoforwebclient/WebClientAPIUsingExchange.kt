package com.study.webflux.demoforwebclient

import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 *packageName    : com.study.webflux.demoforwebclient
 * fileName       : WebClientAPI
 * author         : LEE KYUHEON
 * date           : 2024-01-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-21        LEE KYUHEON       최초 생성
 */
class WebClientAPIUsingExchange {

    //create web client instance
    //private val webClient:WebClient = WebClient.create()
    private val webClient:WebClient = WebClient.create("http://localhost:8080/v1")

    //prepare request

    //read response

    fun getAllEmployees(): Flux<Employee>{
        return webClient.get()
            .uri("/employees")
            .exchangeToFlux{
                it.bodyToFlux(Employee::class.java)
            }
            .doOnNext { println("Inside getAllEmployees $it") }
    }

    fun saveEmployees(): Mono<Employee> {
        return webClient.post()
            .uri("/employees")
            .body(BodyInserters.fromValue(Employee("123", "Smith", "IT")))
            .exchangeToMono{
                it.bodyToMono(Employee::class.java)
            }
            .doOnNext { println("Inside saveEmployees $it") }
    }

    fun updateEmployees(): Mono<Employee> {
        return webClient.put()
            .uri("/update")
            .body(BodyInserters.fromValue(Employee("123", "Smith", "CSE")))
            .exchangeToMono{
                it.bodyToMono(Employee::class.java)
            }
            .doOnNext { println("Inside updateEmployees $it") }
    }

    fun deleteEmployees(id:String): Mono<Employee> {
        return webClient
            .delete()
            .uri("/employees/{id}", id)
            .exchangeToMono{
                it.bodyToMono(Employee::class.java)
            }
            .doOnNext { println("Inside deleteEmployees $it") } //delete 요청은 mono<void> return 해서 null 이 보일거임
    }
}