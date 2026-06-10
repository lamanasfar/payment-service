//package com.delivery.paymentservice.mapper;
//
//import com.delivery.paymentservice.dto.CourierBalanceDto;
//import com.delivery.paymentservice.entity.CourierBalanceEntity;
//
//public class CourierBalanceMapper {
//
//    public static CourierBalanceDto toDto(CourierBalanceEntity entity) {
//        return CourierBalanceDto.builder()
//                .id(entity.getId())
//                .courierId(entity.getCourierId())
//                .balance(entity.getBalance())
//                .turnover(entity.getTurnover())
//                .build();
//    }
//
//    public static CourierBalanceEntity toEntity(CourierBalanceDto dto) {
//        return CourierBalanceEntity.builder()
//                .id(dto.getId())
//                .courierId(dto.getCourierId())
//                .balance(dto.getBalance())
//                .turnover(dto.getTurnover())
//                .build();
//    }
//}