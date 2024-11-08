package com.example.book_service_mono.service.impl.paymentImpl;



import com.example.book_service_mono.domain.cartDomain.Cart;
import com.example.book_service_mono.domain.paymentDomain.Payment;
import com.example.book_service_mono.dto.cartDto.CartDto;
import com.example.book_service_mono.dto.mapping.paymentMapping.PaymentMapper;
import com.example.book_service_mono.dto.paymentDto.PaymentCreationDto;
import com.example.book_service_mono.dto.paymentDto.PaymentDto;
import com.example.book_service_mono.exception.NotFoundException;
import com.example.book_service_mono.exception.PaymentCreationException;
import com.example.book_service_mono.repository.paymentRepository.PaymentRepository;
import com.example.book_service_mono.service.emailService.EmailService;
import com.example.book_service_mono.service.impl.bookImpl.BookServiceImpl;
import com.example.book_service_mono.service.impl.cartImpl.CartServiceImpl;
import com.example.book_service_mono.service.paymentService.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.book_service_mono.enumerator.Error.NO_PAYMENT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CartServiceImpl cartApiClient;
    private final BookServiceImpl bookApiClient;
    private final EmailService notificationApiClient;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> fetchAllPayments() {
        log.info("Fetching all payments");
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) {
            log.error("No payment found");
            throw new NotFoundException(NO_PAYMENT_FOUND.getErrorMessage());
        }
        return paymentMapper.paymentListToPaymentListDto(payments);
    }

    @Override
    public Payment findPaymentById(String id) {
        log.info("Fetching payment by id");
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PAYMENT_FOUND.getErrorMessage()));
    }

    @Override
    public PaymentDto addPayment(PaymentCreationDto paymentCreationDto) {
        paymentRepository.findTopByCartId(paymentCreationDto.getCartId()).ifPresent(_ -> {
            throw new NotFoundException("Pagamento já existe com o cartId ".concat(paymentCreationDto.getCartId()));
        });
        Float amount = 0F;
        try {
            final Cart cartDto = cartApiClient.findCartById(paymentCreationDto.getCartId());
            for (String bookId : cartDto.getBooksIds()) {
                bookApiClient.findBookById(bookId);
                amount += bookApiClient.findBookById(bookId).getPrice();
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Pagamento não pôde ser concluído, pois: " + e.getMessage());
        } catch (Exception e) {
            throw new PaymentCreationException(e.getMessage());
        }
        Payment payment = paymentMapper.paymentCreationDtoToPayment(paymentCreationDto);
        payment.setAmount(amount);
        paymentRepository.save(payment);
        log.info("Pagamento concluído com sucesso para o carrinho com Id {}", payment.getId());
        notificationApiClient.enviarEmail(paymentCreationDto.getUserEmail());
        return paymentMapper.paymentToPaymentDto(payment);
    }

    @Override
    public void deletePayment(String id) {
        final Payment payment = this.findPaymentById(id);
        paymentRepository.delete(payment);
        log.info("Payment {} deleted.", payment.getId());
    }

    @Override
    public PaymentDto findPaymentByCartId(String cartId) {
        Payment payment = paymentRepository.findTopByCartId(cartId)
                .orElseThrow(() -> new NotFoundException(NO_PAYMENT_FOUND.getErrorMessage()));
        return paymentMapper.paymentToPaymentDto(payment);
    }
}
