package com.example.book_service_mono.dto.mapping.cartMapping;


import com.example.book_service_mono.domain.cartDomain.Cart;
import com.example.book_service_mono.dto.cartDto.CartCreationDto;
import com.example.book_service_mono.dto.cartDto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CartMapper {

    Cart cartCreationDtoToCart(CartCreationDto cartCreationDto);

    CartDto cartToCartDto(Cart cart);

    List<CartDto> cartListToCartListDto(List<Cart> carts);
}
