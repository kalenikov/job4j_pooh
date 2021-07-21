package ru.job4j.pooh.service;

import org.junit.Test;
import ru.job4j.pooh.Request;
import ru.job4j.pooh.Response;
import ru.job4j.pooh.utils.HttpCode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TopicServiceTest {

    @Test
    public void getThenPostThenGet() {
        Service qs = new TopicService();
        Response rsl;

        rsl = qs.process(Request.of("GET /topic/t1/1"));
        assertEquals(rsl.status(), HttpCode.BAD_REQUEST);
        assertEquals(rsl.text(), "topic not found: t1");

        rsl = qs.process(Request.of("POST /topic/t1 val1"));
        assertEquals(rsl.text(), "message was added to t1");

        rsl = qs.process(Request.of("GET /topic/t1/1"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertNull(rsl.text());

        rsl = qs.process(Request.of("POST /topic/t1 ONE"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertEquals(rsl.text(), "message was added to t1");

        rsl = qs.process(Request.of("GET /topic/t1/1"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertEquals(rsl.text(), "ONE");

        rsl = qs.process(Request.of("GET /topic/t1/2"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertNull(rsl.text());

        rsl = qs.process(Request.of("POST /topic/t1 TWO"));
        assertEquals(rsl.status(), HttpCode.OK);

        rsl = qs.process(Request.of("GET /topic/t1/1"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertEquals(rsl.text(), "TWO");

        rsl = qs.process(Request.of("GET /topic/t1/2"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertEquals(rsl.text(), "TWO");

        rsl = qs.process(Request.of("GET /topic/t1/2"));
        assertEquals(rsl.status(), HttpCode.OK);
        assertNull(rsl.text());
    }


}