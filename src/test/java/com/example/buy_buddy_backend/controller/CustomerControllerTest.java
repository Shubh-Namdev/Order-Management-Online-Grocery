package com.example.buy_buddy_backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.buy_buddy_backend.BuyBuddyBackendApplication;
import com.example.buy_buddy_backend.model.Customer;
import com.example.buy_buddy_backend.model.Order;
import com.example.buy_buddy_backend.model.Product;
import com.example.buy_buddy_backend.service.CustomerService;

@SpringBootTest(classes = {BuyBuddyBackendApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
@ActiveProfiles("test")
public class CustomerControllerTest {

    private String path = "/api/customers";

    private MockMvc mvc;

    @InjectMocks
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //mvc = MockMvcBuilders.standaloneSetup(customerController).build();
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void correctResponseForGetCustomers() throws Exception{
        List<Customer> customers = loadCustomerData();
        assertNotNull(customers);

        when(customerService.getAllCustomers()).thenReturn(customers);

        URI uri = UriComponentsBuilder.fromPath(path).build().toUri();
        assertEquals(path, uri.toString());

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                    .get(uri.toString()).accept(MediaType.APPLICATION_JSON)
                    ).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(customerService, times(1)).getAllCustomers();
                                                                        
    }


    private List<Customer> loadCustomerData() {
        Product prod1 = new Product(1L, "shoes", 1342.0);
        Product prod2 = new Product(2L, "jacket", 2453.0);
        Product prod3 = new Product(3L, "t-shirt", 567.0);

        Order order1 = new Order(1L, LocalDateTime.now(), null, new ArrayList<>());
        order1.getProducts().add(prod1);
        order1.getProducts().add(prod3);
        
        Order order2 = new Order(2L, LocalDateTime.now(), null, new ArrayList<>());
        order2.getProducts().add(prod2);
        order2.getProducts().add(prod3);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        Customer customer1 = new Customer(1L, "Ole", "ole@something.com", orders);
        Customer customer2 = new Customer(2L, "ben", "ben@something.com", orders);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        return customers;
    }
}
