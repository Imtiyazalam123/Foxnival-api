package com.foxnival.mapper;

import com.foxnival.dto.SubscribeDetailDto;
import com.foxnival.entity.PaymentDetails;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentDetailsMapper {

    PaymentDetailsMapper INSTANCE = Mappers.getMapper(PaymentDetailsMapper.class);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    @Mapping(source = "paymentId", target = "paymentId")
    @Mapping(source = "paymentSignature", target = "paymentSignature")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentDetails subscribeDetailDtoToPaymentDetails(SubscribeDetailDto subscribeDetailDto);
}
