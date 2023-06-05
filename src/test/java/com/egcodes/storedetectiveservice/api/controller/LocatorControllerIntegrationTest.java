package com.egcodes.storedetectiveservice.api.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.egcodes.storedetectiveservice.TestContextProvider;
import com.egcodes.storedetectiveservice.mapper.StoreMapper;
import com.egcodes.storedetectiveservice.service.store.StoreService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Locator Controller Integration Test")
public class LocatorControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreMapper storeMapper;

    @BeforeEach
    public void setUp() {
        RestAssured.basePath = "/api/v1/stores";
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Test find N nearest stores with given location and number")
    public void testFindNearestNStores() {
        var store = storeService.findAll().get(0);
        var locationDTO = TestContextProvider.createLocationDTO(store);
        int numberOfStores = 3;
        var expectedStoreDTO = storeMapper.toDTO(store);

        given()
            .contentType(ContentType.JSON)
            .param("latitude", locationDTO.getLatitude())
            .param("longitude", locationDTO.getLongitude())
        .when()
            .get("/nearest/{numberOfStores}", numberOfStores)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", equalTo(numberOfStores))
            .body("[0].latitude", equalTo(expectedStoreDTO.getLatitude()))
            .body("[0].longitude", equalTo(expectedStoreDTO.getLongitude()));
    }

    @Test
    @DisplayName("Test find N nearest stores with given location by current time")
    public void testFindNearestNStoresByCurrentTime() {
        var store = storeService.findAll().get(0);
        var locationDTO = TestContextProvider.createLocationDTO(store);
        int numberOfStores = 2;

        given()
            .contentType(ContentType.JSON)
            .param("latitude", locationDTO.getLatitude())
            .param("longitude", locationDTO.getLongitude())
        .when()
            .get("/nearestByCurrentTime/{numberOfStores}", numberOfStores)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", equalTo(numberOfStores));
    }

}