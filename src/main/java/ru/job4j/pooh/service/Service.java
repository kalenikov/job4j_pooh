package ru.job4j.pooh.service;

import ru.job4j.pooh.Request;
import ru.job4j.pooh.Response;

public interface Service {
    Response process(Request request);
}