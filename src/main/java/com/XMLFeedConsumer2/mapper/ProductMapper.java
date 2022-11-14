package com.XMLFeedConsumer2.mapper;

import com.XMLFeedConsumer2.dto.ProductDescriptionDTO;
import com.XMLFeedConsumer2.model.Description;
import com.XMLFeedConsumer2.model.Product;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;

//@Mapper
public interface ProductMapper {
    ProductDescriptionDTO modelToDTO(Product product, Description description);
}
