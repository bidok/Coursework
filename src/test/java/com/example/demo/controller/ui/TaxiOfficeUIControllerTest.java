package com.example.demo.controller.ui;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class TaxiOfficeUIControllerTest {

	@Autowired
	TaxiOfficeServiceImpl taxiOfficeService;
	@Autowired
	MockMvc mockMvc;

	@Test
	void showAll() throws Exception {
		List<TaxiOffice> list = taxiOfficeService.getAll();
		mockMvc.perform(MockMvcRequestBuilders.get("/ui/taxioffice/get/all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("taxiOffices"))
				.andExpect(MockMvcResultMatchers.model().attribute("taxiOffices", list));
	}

	@Test
	void getById() throws Exception {
		String id = "608abc4e3fceec0af3849f36";
		TaxiOffice list = taxiOfficeService.getById(id);
		mockMvc.perform(MockMvcRequestBuilders.get("/ui/taxioffice/get/" + id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("taxiOffice"))
				.andExpect(MockMvcResultMatchers.model().attribute("taxiOffices", list));
	}


	@Test
	void create() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/ui/taxioffice/create")
		.param("name", "test")
		.param("phoneNumber", "test")
		.param("ownerName", "test")
		.param("licenseNumber", "test"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}
	@Test
	void update() throws Exception {
		String id = taxiOfficeService.getAll().stream().filter(item -> item.getName().equals("test")).findFirst().orElse(null).getId();
		mockMvc.perform(MockMvcRequestBuilders.post("/ui/taxioffice/update/" + id)
				.param("name", "test")
				.param("phoneNumber", "test")
				.param("ownerName", "test")
				.param("licenseNumber", "test"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
	}

	@Test
	void delete() throws Exception {
		TaxiOffice taxiOfficee = taxiOfficeService.getAll().stream().filter(item -> item.getName().equals("test")).findFirst().orElse(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/ui/taxioffice/delete/" + taxiOfficee.getId()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection());


	}

}
