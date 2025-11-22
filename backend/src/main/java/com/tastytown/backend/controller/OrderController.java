package com.tastytown.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.tastytown.backend.constants.OrderStatus;
import com.tastytown.backend.dto.BillingInfoDTO;
import com.tastytown.backend.dto.OrderDTO;
import com.tastytown.backend.Helper.UserServiceHelper;
import com.tastytown.backend.service.IOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final IOrderService iOrderService;
    private final UserServiceHelper userServiceHelper;

    // Place Order
    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(Authentication authentication,
            @RequestBody BillingInfoDTO billingInfo) {
        String email = authentication.getName(); // comes from JWT subject
        var user = userServiceHelper.getUserByEmail(email);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iOrderService.placeOrder(billingInfo, user.getUserId()));
    }

    // Get All Orders (for admin)
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(iOrderService.getAllOrders());
    }

    // Get Orders by Logged-in User
    @GetMapping("/user")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(Authentication authentication) {
        String email = authentication.getName();
        var user = userServiceHelper.getUserByEmail(email);

        return ResponseEntity.ok(iOrderService.getOrdersByUsers(user.getUserId()));
    }

    // Update Order Status (for admin use case)
    @PutMapping("/{orderCode}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable String orderCode,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(iOrderService.updateOrderStatus(orderCode, status));
    }
}
