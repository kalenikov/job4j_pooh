package ru.job4j.pooh.service;

import org.junit.Test;
import ru.job4j.pooh.Request;
import ru.job4j.pooh.utils.HttpCode;

import static org.junit.Assert.*;

public class QueueServiceTest {

    @Test
    public void post() {
        Service qs = new QueueService();
        Request req = Request.of("POST /queue/q1 val1");
        var rsl = qs.process(req);
        assertEquals(rsl.status(), HttpCode.OK);
        assertEquals(rsl.text(), "message was added to q1");
    }

    @Test
    public void getWhenNoQueue() {
        QueueService qs = new QueueService();
        Request req = Request.of("GET /queue/q1");
        var rsl = qs.process(req);
        assertEquals(rsl.status(), HttpCode.BAD_REQUEST);
    }

    @Test
    public void get() {
        QueueService qs = new QueueService();
        qs.process(Request.of("POST /queue/q1 val1"));
        Request req = Request.of("GET /queue/q1");
        var rsl = qs.process(req);
        assertEquals(HttpCode.OK, rsl.status());
        assertEquals("val1", rsl.text());
    }

    @Test
    public void getRepeatedly() {
        QueueService qs = new QueueService();
        qs.process(Request.of("POST /queue/q1 val1"));
        Request req = Request.of("GET /queue/q1");
        var rsl = qs.process(req);
        assertEquals(rsl.text(), "val1");
        rsl = qs.process(req);
        assertNull(rsl.text());
    }
}