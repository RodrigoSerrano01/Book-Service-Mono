package com.example.book_service_mono.service.cartService;


import com.example.book_service_mono.domain.cartDomain.Cart;
import com.example.book_service_mono.dto.cartDto.CartBookListDto;
import com.example.book_service_mono.dto.cartDto.CartCreationDto;
import com.example.book_service_mono.dto.cartDto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    Cart findCartById(String id);

    CartDto addCart(CartCreationDto cartCreationDto);
    List<CartDto> findAllCarts();

    CartDto addBook(String cartId, CartBookListDto cartBookListDto);

    CartDto removeBook(String cartId, CartBookListDto cartBookListDto);

    void deleteCart(String cartId);
}
