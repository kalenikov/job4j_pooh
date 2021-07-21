package ru.job4j.pooh;

public class Response {
    private final String text;
    private final int status;

    public Response(String text, int status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public int status() {
        return status;
    }
}