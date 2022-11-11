package com.ejtrio.todolistapi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public abstract class RepositoryTestBase {

    @LocalServerPort
    int port;

    public RequestSpecification spec;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;

        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    public RequestFieldsSnippet documentPostUsers() {
        return requestFields(
                fieldWithPath("firstName").description("User's First Name"),
                fieldWithPath("lastName").description("User's Last Name"),
                fieldWithPath("email").description("User's Email Address")
        );
    }

    public ResponseFieldsSnippet documentGetUsers() {
        return responseFields(
                fieldWithPath("firstName").description("User's First Name"),
                fieldWithPath("lastName").description("User's Last Name"),
                fieldWithPath("email").description("User's Email Address"),
                fieldWithPath("_links.self.href").description("HATEOAS Self Reference"),
                fieldWithPath("_links.tla:user.href").description("HATEOAS User Reference"),
                fieldWithPath("_links.tla:user.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.tla:todoLists.href").description("HATEOAS Todo Lists Reference"),
                fieldWithPath("_links.tla:todoLists.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.curies.[].href").description("HATEOAS CURIE Reference"),
                fieldWithPath("_links.curies.[].name").description("HATEOAS CURIE Name"),
                fieldWithPath("_links.curies.[].templated").description("HATEOAS Generated Code"));
    }

    public RequestFieldsSnippet documentPostTodoLists() {
        return requestFields(
                fieldWithPath("listName").description("Todo List's Name"),
                fieldWithPath("user").description("Link to User Resource")
        );
    }

    public ResponseFieldsSnippet documentGetTodoLists() {
        return responseFields(
                fieldWithPath("listName").description("Todo List's Name"),
                fieldWithPath("_links.self.href").description("HATEOAS Self Reference"),
                fieldWithPath("_links.tla:todoList.href").description("HATEOAS Todo List Reference"),
                fieldWithPath("_links.tla:todoList.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.tla:user.href").description("HATEOAS User Reference"),
                fieldWithPath("_links.tla:user.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.tla:items.href").description("HATEOAS Items Reference"),
                fieldWithPath("_links.curies.[].href").description("HATEOAS CURIE Reference"),
                fieldWithPath("_links.curies.[].name").description("HATEOAS CURIE Name"),
                fieldWithPath("_links.curies.[].templated").description("HATEOAS Generated Code")
        );
    }

    public ResponseFieldsSnippet documentGetTodoListsProjection() {
        return responseFields(
                fieldWithPath("listName").description("Todo List's Name"),
                fieldWithPath("user.fullName").description("User's Full Name"),
                fieldWithPath("user.email").description("User's Email Address"),
                fieldWithPath("user._links.self.href").description("HATEOAS Self Reference"),
                fieldWithPath("user._links.self.templated").description("HATEOAS Generated Code"),
                fieldWithPath("user._links.tla:todoLists.href").description("HATEOAS Todo List Reference"),
                fieldWithPath("user._links.tla:todoLists.templated").description("HATEOAS Generated Code"),
                fieldWithPath("items.[].itemName").description("Todo List Item's Name"),
                fieldWithPath("items.[].itemDescription").description("Todo List Item's Description"),
                fieldWithPath("items.[].completed").description("Flag indicating if the item is complete"),
                fieldWithPath("items.[].completedAt").description("Timestamp indication when the item was completed"),
                fieldWithPath("_links.self.href").description("HATEOAS Self Reference"),
                fieldWithPath("_links.tla:todoList.href").description("HATEOAS Todo List Reference"),
                fieldWithPath("_links.tla:todoList.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.tla:user.href").description("HATEOAS User Reference"),
                fieldWithPath("_links.tla:user.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.tla:items.href").description("HATEOAS Todo List Items Reference"),
                fieldWithPath("_links.curies.[].href").description("HATEOAS CURIE Reference"),
                fieldWithPath("_links.curies.[].name").description("HATEOAS CURIE Name"),
                fieldWithPath("_links.curies.[].templated").description("HATEOAS Generated Code")
        );
    }

    public RequestFieldsSnippet documentPostTodoListItems() {
        return requestFields(
                fieldWithPath("todoList").description("Link to Todo List Resource"),
                fieldWithPath("itemName").description("Todo List's Name"),
                fieldWithPath("itemDescription").description("Link to User Resource")
        );
    }

    public RequestFieldsSnippet documentPatchTodoListItems() {
        return requestFields(
                fieldWithPath("completed").description("Flag indicating if the item is complete")
        );
    }

    public ResponseFieldsSnippet documentGetTodoListItems() {
        return responseFields(
                fieldWithPath("itemName").description("Todo List Item's Name"),
                fieldWithPath("itemDescription").description("Todo List Item's Description"),
                fieldWithPath("completed").description("Flag indicating if the item is complete"),
                fieldWithPath("completedAt").description("Timestamp indication when the item was completed"),
                fieldWithPath("_links.self.href").description("HATEOAS Self Reference"),
                fieldWithPath("_links.tla:todoListItem.href").description("HATEOAS Todo List Item Reference"),
                fieldWithPath("_links.tla:todoList.href").description("HATEOAS Todo List Reference"),
                fieldWithPath("_links.tla:todoList.templated").description("HATEOAS Generated Code"),
                fieldWithPath("_links.curies.[].href").description("HATEOAS CURIE Reference"),
                fieldWithPath("_links.curies.[].name").description("HATEOAS CURIE Name"),
                fieldWithPath("_links.curies.[].templated").description("HATEOAS Generated Code")
        );
    }
}
