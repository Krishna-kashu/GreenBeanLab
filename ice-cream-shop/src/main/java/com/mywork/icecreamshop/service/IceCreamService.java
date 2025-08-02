package com.mywork.icecreamshop.service;

import com.mywork.icecreamshop.dto.OrderDTO;

public interface IceCreamService {
         double totalPrice(OrderDTO orderDTO);
        boolean validate(OrderDTO orderDTO);
    }