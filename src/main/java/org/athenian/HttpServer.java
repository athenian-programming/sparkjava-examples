package org.athenian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class HttpServer {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(HttpServer.class);
        final String envvar = System.getenv("PORT");

        port((envvar == null) ? 8080 : Integer.parseInt(envvar));

        staticFiles.location("/public");

        before((req, res) -> {
            logger.info("request: {} {}", req.ip(), req.pathInfo());
        });

        get("/",
                (req, res) -> {
                    res.type("text/plain");
                    return "You have reached index.html";
                });

        get("/hello-plain",
                (req, res) -> {
                    res.type("text/plain");
                    return "Hello World";
                });

        get("/hello-html",
                (req, res) -> {
                    res.type("text/html");
                    return "<html>" +
                            "  <head>" +
                            "    <title>Hello</title>" +
                            "  </head>" +
                            "  <body>" +
                            "    <h1>Hello World</h1>" +
                            "  </body>" +
                            "</html>";
                });

        get("/params",
                (req, res) -> {
                    res.type("text/plain");
                    return "p1 = " + req.queryParams("p1") + "\n" +
                            "p2 = " + req.queryParams("p2");
                });
    }
}
