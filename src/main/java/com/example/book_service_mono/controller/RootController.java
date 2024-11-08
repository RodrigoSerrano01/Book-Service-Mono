package com.example.book_service_mono.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Abstract class that centralizes the path prefix "/book-service/v1"
 * for controller classes that extend RootController.
 */
@RequestMapping(path = "/book-service-mono/v1")
public abstract class RootController {
}
