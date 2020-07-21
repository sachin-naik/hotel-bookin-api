package com.hotelbookingapi.demo.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.hotelbookingapi.demo.converter.BookingEntityToBookingResponseConvertor;
import com.hotelbookingapi.demo.converter.BookingRequestToBookingEntityConverter;
import com.hotelbookingapi.demo.converter.RoomEntityToRoomResponseConverter;

@Configuration
public class ConversionConfig {
	private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<Converter>();
        converters.add(new BookingEntityToBookingResponseConvertor());
        converters.add(new BookingRequestToBookingEntityConverter());
        converters.add(new RoomEntityToRoomResponseConverter());

        return converters;
    }

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();

        return bean.getObject();
    }
}
