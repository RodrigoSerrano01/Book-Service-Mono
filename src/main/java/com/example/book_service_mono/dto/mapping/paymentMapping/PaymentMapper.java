package com.example.book_service_mono.dto.mapping.paymentMapping;


import com.example.book_service_mono.domain.paymentDomain.Payment;
import com.example.book_service_mono.dto.paymentDto.PaymentCreationDto;
import com.example.book_service_mono.dto.paymentDto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper interface for converting between domain objects and DTOs using MapStruct.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentMapper {

    Payment paymentCreationDtoToPayment(PaymentCreationDto paymentCreationDto);

    PaymentDto paymentToPaymentDto(Payment payment);

    List<PaymentDto> paymentListToPaymentListDto(List<Payment> payments);
}
