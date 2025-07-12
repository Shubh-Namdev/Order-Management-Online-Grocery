package com.example.buy_buddy_backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.buy_buddy_backend.service.implementation.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.example.buy_buddy_backend.BuyBuddyBackendApplication;
import com.example.buy_buddy_backend.model.Order;
import com.example.buy_buddy_backend.repository.OrderRepository;

@SpringBootTest(classes = {BuyBuddyBackendApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@DirtiesContext
@ActiveProfiles("test")
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkResponseForGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        Order orderDetails = orderService.getOrderById(anyLong());
        assertEquals(1L, orderDetails.getId());

        verify(orderRepository, times(1)).findById(anyLong());
    }

}
