package com.example.demo.controller.rest;

import com.example.demo.model.TaxiOffice;
import com.example.demo.service.taxiOffice.impls.TaxiOfficeServiceImpl;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TaxiOfficeRestControllerTest {
	@Autowired
	TaxiOfficeServiceImpl taxiOfficeService;
	@Autowired
	MockMvc mockMvc;

	@Test
	void getServices() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/taxioffice/get/all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("megaTaxi")));
	}
	@Test
	void checkGetByIdByJsonKeys() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/taxioffice/get/608abc4e3fceec0af3849f36"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("name")))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("phoneNumber")))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("ownerName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("licenseNumber")));
	}

	void checkGetByIdByDataValid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/taxioffice/get/608abc4e3fceec0af3849f36"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("megaTaxi")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.is("+380965632456")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.ownerName", Matchers.is("Oleg")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.licenseNumber", Matchers.is("09876543210")));
	}
	@Test
	void create() throws Exception {
		String json = "{\"licenseNumber\": \"XXXX-XXXX-XXXX-XXXX\",\n" +
				"  \"name\": \"test\",\n" +
				"  \"ownerName\": \"Marty McFly\",\n"
				+ "  \"phoneNumber\": \"+380-XX-XXX-XXXX\"\n" + "}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/taxioffice/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("name")))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("phoneNumber")))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("ownerName")))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasKey("licenseNumber")))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	void update() throws Exception {
		TaxiOffice taxiOfficee = taxiOfficeService.getAll().stream().filter(item -> item.getName().equals("test")).findFirst().orElse(null);
		String ownerName = "tests";
		String json = "{"
				+ "\"id\": \"" + taxiOfficee.getId() + "\",\n" +
				"        \"name\": \"test\",\n"
				+ "        \"phoneNumber\": \"+380-XX-XXX-XXXX\",\n" +
				"        \"ownerName\": \"" + ownerName + "\",\n"
				+ "        \"licenseNumber\": \"XXXX-XXXX-XXXX-XXXX\"\n" +
				"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/taxioffice/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.ownerName", Matchers.is(ownerName)));
	}
	@Test
	void delete() throws Exception {
		TaxiOffice taxiOfficee = taxiOfficeService.getAll().stream().filter(item -> item.getName().equals("test")).findFirst().orElse(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/taxioffice/delete/" + taxiOfficee.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}