package com.example.book_service_mono.service.impl.cartImpl;



import com.example.book_service_mono.domain.bookDomain.Book;
import com.example.book_service_mono.domain.cartDomain.Cart;
import com.example.book_service_mono.dto.bookDto.BookDto;
import com.example.book_service_mono.dto.cartDto.CartBookListDto;
import com.example.book_service_mono.dto.cartDto.CartCreationDto;
import com.example.book_service_mono.dto.cartDto.CartDto;
import com.example.book_service_mono.dto.mapping.cartMapping.CartMapper;
import com.example.book_service_mono.exception.CartAlreadyExists;
import com.example.book_service_mono.exception.CartCreationException;
import com.example.book_service_mono.exception.NotFoundException;
import com.example.book_service_mono.repository.cartRepository.CartRepository;
import com.example.book_service_mono.service.cartService.CartService;
import com.example.book_service_mono.service.impl.bookImpl.BookServiceImpl;
import com.example.book_service_mono.service.impl.userImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.book_service_mono.enumerator.Error.CART_ALREADY_EXISTS;
import static com.example.book_service_mono.enumerator.Error.NO_CART_FOUND_BY_ID;


@Slf4j //  Annotation that generates a logger for the class.
@Service // Indicates that this class is a service component in Spring.
@RequiredArgsConstructor // Annotation that generates a constructor with all final fields.
public class CartServiceImpl implements CartService {

    private static final String SEARCHING_CART_BY_USER_ID = "Searching cart by user id {}";
    private static final int MIN_ALLOWED_BOOKS = 1;

    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final UserServiceImpl userServiceImpl;
    private final BookServiceImpl bookServiceImpl;

    @Override
    public Cart findCartById(String id) {
        log.info("Searching cart by id {}", id);
        return cartRepository.findCartById(id)
                .orElseThrow(() -> new NotFoundException(NO_CART_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public CartDto addCart(CartCreationDto cartCreationDto) {
        log.info(SEARCHING_CART_BY_USER_ID, cartCreationDto.getUserId());
        cartRepository.findTopByUserId(cartCreationDto.getUserId()).ifPresent(_ -> {
            log.error(CART_ALREADY_EXISTS.getErrorDescription());
            throw new CartAlreadyExists(CART_ALREADY_EXISTS.getErrorDescription());
        });
        try {
            userServiceImpl.findUserById(cartCreationDto.getUserId());
            for (String bookId : cartCreationDto.getBooksIds()) {
                final Book book = bookServiceImpl.findBookById(bookId);
                if (book.getQuantity() < MIN_ALLOWED_BOOKS) {
                    throw new CartCreationException("O livro ".concat(book.getTitle()).concat(" não está mais disponível!"));
                }
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Carrinho não pôde ser criado, pois: " + e.getMessage());
        } catch (Exception e) {
            throw new CartCreationException("Carrinho não pôde ser criado, pois: " + e.getMessage());
        }
        Cart cart = cartMapper.cartCreationDtoToCart(cartCreationDto);
        cartRepository.save(cart);
        log.info("Carrinho criado com sucesso para o usuário com Id {}", cartCreationDto.getUserId());

        return cartMapper.cartToCartDto(cart);
    }

    @Override
    public List<CartDto> findAllCarts() {
        final List<Cart> carts = cartRepository.findAll();
        return cartMapper.cartListToCartListDto(carts);
    }

    @Override
    public CartDto addBook(String cartId, CartBookListDto cartBookListDto) {
        final Cart cart = this.findCartById(cartId);
        try {
            for (String bookId : cartBookListDto.getBooksIds()) {
                final Book book = bookServiceImpl.findBookById(bookId);
                if (book.getQuantity() < MIN_ALLOWED_BOOKS) {
                    throw new CartCreationException("O livro ".concat(book.getTitle()).concat(" não está mais disponível!"));
                }
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Livro não pôde ser adicionado, pois: " + e.getMessage());
        } catch (Exception e) {
            throw new CartCreationException(e.getMessage());
        }
        cart.getBooksIds().addAll(cartBookListDto.getBooksIds());
        cartRepository.save(cart);
        return cartMapper.cartToCartDto(cart);
    }

    @Override
    public CartDto removeBook(String cartId, CartBookListDto cartBookListDto) {
        final Cart cart = this.findCartById(cartId);
        cart.getBooksIds().removeAll(cartBookListDto.getBooksIds());
        cartRepository.save(cart);
        return cartMapper.cartToCartDto(cart);
    }

    @Override
    public void deleteCart(String cartId) {
        final Cart cart = this.findCartById(cartId);
        cartRepository.deleteById(cartId);
        log.info("Cart {} deleted.", cart.getId());
    }
}
