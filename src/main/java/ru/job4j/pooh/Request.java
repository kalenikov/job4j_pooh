package ru.job4j.pooh;

public class Request {
    private final String method;
    private final String mode;
    private final String queueName;
    private final String value;
    private final String id;

    private Request(String method, String mode, String queueName, String value, String id) {
        this.method = method;
        this.mode = mode;
        this.queueName = queueName;
        this.value = value;
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public String getMode() {
        return mode;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    /**
     * POST/xx/q1 val1
     * GET/queue/q1
     * GET/topic/t1/1
     */
    public static Request of(String content) {
        String[] ar = content.split("\r\n")[0].split("/", 3);
        String method = ar[0].trim();
        String mode = ar[1];
        String queueName = ar[2];
        String value = null;
        String id = null;
        if ("POST".equals(method)) {
            queueName = ar[2].split("\\s+")[0];
            value = ar[2].split("\\s+")[1];
        }
        if ("GET".equals(method)) {
            if (ar[2].split("/").length == 2) {
                queueName = ar[2].split("/")[0];
                id = ar[2].split("/")[1];
            };
        }


        return new

                Request(method, mode, queueName, value, id);
    }


}