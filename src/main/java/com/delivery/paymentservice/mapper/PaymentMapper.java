//package com.delivery.paymentservice.mapper;
//
//import com.delivery.paymentservice.dto.PaymentDto;
//import com.delivery.paymentservice.entity.PaymentEntity;
//
//public class PaymentMapper {
//
//    public static PaymentDto toDto(PaymentEntity entity) {
//        return PaymentDto.builder()
//                .id(entity.getId())
//                .orderId(entity.getOrderId())
//                .courierId(entity.getCourierId())
//                .orderAmount(entity.getOrderAmount())
//                .courierEarning(entity.getCourierEarning())
//                .status(entity.getStatus())
//                .createdAt(entity.getCreatedAt())
//                .completedAt(entity.getCompletedAt())
//                .build();
//    }
//
//    public static PaymentEntity toEntity(PaymentDto dto) {
//        return PaymentEntity.builder()
//                .id(dto.getId())
//                .orderId(dto.getOrderId())
//                .courierId(dto.getCourierId())
//                .orderAmount(dto.getOrderAmount())
//                .courierEarning(dto.getCourierEarning())
//                .status(dto.getStatus())
//                .createdAt(dto.getCreatedAt())
//                .completedAt(dto.getCompletedAt())
//                .build();
//    }
//}