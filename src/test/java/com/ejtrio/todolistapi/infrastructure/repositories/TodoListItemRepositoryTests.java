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

public class TodoListItemRepositoryTests extends RepositoryTestBase {

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
        given(this.spec)
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/todoListItem.json"))
                .post("/todoListItems");
    }

    @Test
    public void postTodoListItem() throws Exception {
        Response response = given(this.spec)
                .filter(document("postTodoListItems", preprocessResponse(prettyPrint()), documentPostTodoListItems()))
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/todoListItem.json"))
                .post("/todoListItems");

        assertThat(response.getStatusCode()).isEqualTo(201);

        DocumentContext parsedJson = JsonPath.parse(response.asString());
        assertThat((Object) parsedJson.read("itemName")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("itemDescription")).isInstanceOf(String.class);
    }

    @Test
    public void patchTodoListItem() throws Exception {
        Response response = given(this.spec)
                .filter(document("patchTodoListItems", preprocessResponse(prettyPrint()), documentPatchTodoListItems()))
                .contentType(ContentType.JSON)
                .body(JsonHelper.getJsonStringFromFile("json/request/todoListItemUpdate.json"))
                .patch("/todoListItems/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        DocumentContext parsedJson = JsonPath.parse(response.asString());
        assertThat((Object) parsedJson.read("itemName")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("itemDescription")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("completed")).isInstanceOf(Boolean.class);
        assertThat((Object) parsedJson.read("completedAt")).isInstanceOf(String.class);
    }

    @Test
    public void getTodoListItem() {
        Response response = given(this.spec)
                .filter(document("getTodoListItems", preprocessResponse(prettyPrint()), documentGetTodoListItems()))
                .get("/todoListItems/1");

        assertThat(response.getStatusCode()).isEqualTo(200);

        DocumentContext parsedJson = JsonPath.parse(response.asString());
        assertThat((Object) parsedJson.read("itemName")).isInstanceOf(String.class);
        assertThat((Object) parsedJson.read("itemDescription")).isInstanceOf(String.class);
    }

    @Test
    public void deleteTodoListItem() {
        Response response = given(this.spec)
                .filter(document("deleteTodoListItems", preprocessResponse(prettyPrint())))
                .delete("/todoListItems/1");

        assertThat(response.getStatusCode()).isEqualTo(204);
    }
}
