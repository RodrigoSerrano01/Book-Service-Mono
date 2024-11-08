package com.example.book_service_mono.service.impl.orderImpl;

import com.example.book_service_mono.domain.cartDomain.Cart;
import com.example.book_service_mono.domain.orderDomain.Order;
import com.example.book_service_mono.domain.userDomain.User;
import com.example.book_service_mono.dto.mapping.orderMapping.OrderMapper;
import com.example.book_service_mono.dto.orderDto.OrderCreationDto;
import com.example.book_service_mono.dto.orderDto.OrderDto;
import com.example.book_service_mono.exception.NotFoundException;
import com.example.book_service_mono.exception.OrderCreationException;
import com.example.book_service_mono.repository.orderRepository.OrderRepository;
import com.example.book_service_mono.service.emailService.EmailService;
import com.example.book_service_mono.service.impl.bookImpl.BookServiceImpl;
import com.example.book_service_mono.service.impl.cartImpl.CartServiceImpl;
import com.example.book_service_mono.service.impl.paymentImpl.PaymentServiceImpl;
import com.example.book_service_mono.service.impl.userImpl.UserServiceImpl;

import com.example.book_service_mono.service.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.book_service_mono.enumerator.Error.ORDER_NOT_FOUND;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentServiceImpl paymentServiceImpl;
    private final CartServiceImpl cartApiClient;
    private final EmailService emailService;
    private final BookServiceImpl bookServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Override
    public List<OrderDto> findAllOrders() {
        log.info("Fetching all orders");
        final List<Order> orders = orderRepository.findAll();
        return orderMapper.orderListToOrderListDto(orders);
    }

    @Override
    public Order findOrderById(String id) {
        log.info("Fetching order by id");
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND.getErrorDescription()));
    }

    @Override
    public OrderDto addOrder(OrderCreationDto orderCreationDto) {
        log.info("Adding new order");
        try {
            final String paymentId = paymentServiceImpl.findPaymentById(orderCreationDto.getCartId()).getId();
            final Cart cartDto = cartApiClient.findCartById(orderCreationDto.getCartId());
            final User userDto = userServiceImpl.findUserById(cartDto.getUserId());
            final List<String> booksNames = new ArrayList<>();

            for (String bookId : cartDto.getBooksIds()) {
                final String bookName = bookServiceImpl.findBookById(bookId).getTitle();
                bookServiceImpl.bookPurchase(bookId);
                booksNames.add(bookName);
            }

            final Order order = Order.builder()
                    .paymentId(paymentId)
                    .booksIds(cartDto.getBooksIds())
                    .userId(cartDto.getUserId())
                    .build();
            orderRepository.save(order);
            cartApiClient.deleteCart(cartDto.getId());
            emailService.enviarEmail(userDto.getEmail());
            log.info("Pedido adicionado com sucesso!");
            return orderMapper.orderToOrderDto(order);
        } catch (NotFoundException e) {
            throw new NotFoundException("Pedido não pôde ser concluído, pois: " + e.getMessage());
        } catch (Exception e) {
            throw new OrderCreationException("O pedido não pode ser concluído, pois " + e.getMessage());
        }
    }

    @Override
    public void deleteOrderById(String orderId) {
        this.findOrderById(orderId);
        log.info("Deleting order by id");
        orderRepository.deleteById(orderId);
        log.info("Order deleted");
    }
}
