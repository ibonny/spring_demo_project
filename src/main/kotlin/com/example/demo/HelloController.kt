package com.example.demo

import org.springframework.stereotype.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.ui.Model

import java.util.concurrent.atomic.AtomicLong


data class Greeting(val id: Long, val content: String)

data class ReturnVal(val firstName: String, val lastName: String)


@Controller
class GreetingController {
    val counter = AtomicLong()

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("name", "Ian Bonnycastle")

        return "index"
    }

    @GetMapping("/greeting")
    @ResponseBody
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/snarfblat")
    @ResponseBody
    fun snarfblat() = ReturnVal("Ian", "Bonnycastle")
}