package io.kami.demo.verticle;

import io.kami.demo.config.VertxConfiguration;
import io.kami.demo.service.article.ArticleService;
import io.kami.demo.service.example.ExampleService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiServer extends AbstractVerticle {

  @Autowired
  VertxConfiguration vertxConfiguration;

  @Autowired
  ExampleService exampleService;

  @Autowired
  ArticleService articleService;

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.get("/test").handler(this::handleGetExample);
    router.get("/articles").handler(this::handleGetArticles);
    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }

  private void handleGetArticles(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    try {
      JsonObject json = new JsonObject();

      // Make parameters first
      JsonObject parameters = new JsonObject();
      parameters.put("articles", new JsonArray(articleService.getArticles()));

      // Put meta and parameters
      json.put("meta", generateMetaData("SO", "article.selectOne", "ihihpiqhwegpihpqwiheihg", 0));
      json.put("parameters", parameters);

      sendResponse(response, json.encodePrettily());
    } catch (Exception e) {
      e.printStackTrace();
      sendError(500, response, "에러 메세지 입력 ㅜㅜ");
    }
  }

  private void handleGetExample(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    try {
      JsonObject json = new JsonObject();

      // Make parameters first
      JsonObject parameters = new JsonObject();
      parameters.put("time", exampleService.getCurrentDataTime());

      // Put meta and parameters
      json.put("meta", generateMetaData("T", "test", "ihihpiqhwegpihpqwiheihg", 0));
      json.put("parameters", parameters);

      sendResponse(response, json.encodePrettily());
    } catch (Exception e) {
      e.printStackTrace();
      sendError(500, response, "에유 익센셥이야..");
    }
  }

  // 메타 생성
  private JsonObject generateMetaData(String reqType, String apiName, String uuid, int client) {
    return new JsonObject()
        .put("reqType", reqType)
        .put("apiName", apiName)
        .put("uuid", uuid)
        .put("client", client);
  }

  // 응답 메소드
  private void sendResponse(HttpServerResponse response, String jsonData) {
    response.putHeader("content-type", "application/json; charset=utf-8").end(jsonData);
  }

  // 에러 메소드
  private void sendError(int statusCode, HttpServerResponse response, String message) {
    String errorMessage = String.format("{\"error\": \"%s\"}", message);
    response.putHeader("content-type", "application/json; charset=utf-8").setStatusCode(statusCode).end(errorMessage);
  }

}
