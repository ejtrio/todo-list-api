package com.ejtrio.todolistapi.infrastructure.repositories;

import com.ejtrio.todolistapi.RepositoryTestBase;
import com.ejtrio.todolistapi.utils.JsonHelper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class TodoListRepositoryTests extends RepositoryTestBase {

    @BeforeEach
    public void loadData() throws Exception {
        given(this.spec)
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/user.json"))
                .post("/users");
        given(this.spec)
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/todoList.json"))
                .post("/todoLists");
    }

    @Test
    public void postTodoList() throws Exception {
        Response response = given(this.spec)
                .filter(document("postTodoLists", preprocessResponse(prettyPrint()), documentPostTodoLists()))
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/todoList.json"))
                .post("/todoLists");

        assertThat(response.getStatusCode()).isEqualTo(201);

        DocumentContext parsedJson = JsonPath.parse(response.asString());
        assertThat((Object) parsedJson.read("listName")).isInstanceOf(String.class);
    }

    @Test
    public void getTodoListProjection() throws Exception {
        given(this.spec)
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/todoListItem.json"))
                .post("/todoListItems");

        Response response = given(this.spec)
                .filter(document("getTodoListsProjection", preprocessResponse(prettyPrint()), documentGetTodoListsProjection()))
                .get("/todoLists/1?projection=todoListDetail");

        assertThat(response.getStatusCode()).isEqualTo(200);

        DocumentContext parsedJson = JsonPath.parse(response.asString());
        assertThat((Object) parsedJson.read("listName")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("user.fullName")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("user.email")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("items.[0].itemName")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("items.[0].itemDescription")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("items.[0].completed")).isInstanceOf(Boolean.class);
    }

    @Test
    public void getTodoList() {
        Response response = given(this.spec)
                .filter(document("getTodoLists", preprocessResponse(prettyPrint()), documentGetTodoLists()))
                .get("/todoLists/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        DocumentContext parsedJson = JsonPath.parse(response.asString());
        assertThat((Object) parsedJson.read("listName")).isInstanceOf(String.class);
    }

    @Test
    public void deleteTodoList() {
        Response response = given(this.spec)
                .filter(document("deleteTodoLists", preprocessResponse(prettyPrint())))
                .delete("/todoLists/1");

        assertThat(response.getStatusCode()).isEqualTo(204);
    }
}
