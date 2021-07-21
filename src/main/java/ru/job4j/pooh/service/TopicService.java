package ru.job4j.pooh.service;

import ru.job4j.pooh.Request;
import ru.job4j.pooh.Response;
import ru.job4j.pooh.utils.HttpCode;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static ru.job4j.pooh.utils.HttpMethods.GET;
import static ru.job4j.pooh.utils.HttpMethods.POST;

public class TopicService implements Service {
    private final ConcurrentHashMap<
            String,
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> map = new ConcurrentHashMap<>();

    @Override
    public Response process(Request req) {
        return switch (req.getMethod()) {
            case GET -> get(req);
            case POST -> post(req);
            default -> new Response("no handle", HttpCode.BAD_REQUEST);
        };
    }

    private Response get(Request req) {
        var topic = map.get(req.getQueueName());
        if (topic == null) {
            return new Response("topic not found: " + req.getQueueName(), HttpCode.BAD_REQUEST);
        }
        topic.putIfAbsent(req.getId(), new ConcurrentLinkedQueue<>());
        return new Response(topic.get(req.getId()).poll(), HttpCode.OK);
    }

    private Response post(Request req) {
        map.putIfAbsent(req.getQueueName(), new ConcurrentHashMap<>());
        for (var queue : map.get(req.getQueueName()).values()) {
            queue.add(req.getValue());
        }
        return new Response("message was added to " + req.getQueueName(), HttpCode.OK);
    }
}