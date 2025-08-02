package com.mywork.icecreamshop.service;

import com.mywork.icecreamshop.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IceCreamServiceImpl implements IceCreamService {

    private static final Map<String, Double> flavourPrice = new HashMap<>();

    static {
        flavourPrice.put("Vanilla", 45.0);
        flavourPrice.put("Chocolate", 60.0);
        flavourPrice.put("Strawberry", 50.0);
        flavourPrice.put("BlackCurrent", 70.0);
        flavourPrice.put("Pineapple", 45.0);
    }

    private static final List<String> validCoupons = Arrays.asList(
            "456ICED", "ABC98H", "ARTY0392H", "FREE093"
    );

    @Override
    public double totalPrice(OrderDTO orderDTO) {
        double total = flavourPrice.get(orderDTO.getFlavour()) * orderDTO.getQuantity();

        if (orderDTO.isAddOns()) {
            total += 20;
        }

        if (orderDTO.isTakeAway()) {
            total += 5;
        }

        if (orderDTO.getCoupon() != null && validCoupons.contains(orderDTO.getCoupon())) {
            total *= 0.9; // Apply 10% discount
        }

        return total;
    }

    @Override
    public boolean validate(OrderDTO orderDTO) {
        if (orderDTO.getFlavour() == null || !flavourPrice.containsKey(orderDTO.getFlavour())) {
            return false;
        }

        if (orderDTO.getQuantity() <= 0) {
            return false;
        }

        String coupon = orderDTO.getCoupon();
        if (coupon != null && !coupon.trim().isEmpty()) {
            if (!validCoupons.contains(coupon)) {
                return false;
            }
        }

        return true;
    }
}
