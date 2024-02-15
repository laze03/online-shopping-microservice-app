package com.microserviceapp.productservice.service;

import com.microserviceapp.productservice.dto.ProductRequest;
import com.microserviceapp.productservice.dto.ProductResponse;
import com.microserviceapp.productservice.model.Product;
import com.microserviceapp.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private ProductResponse mapToProductResponse (Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }


    public void createProduct(ProductRequest productRequest){
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice()).build();
            productRepository.save(product);
            log.info("product {} is saved", product.getId());
        }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }
}
