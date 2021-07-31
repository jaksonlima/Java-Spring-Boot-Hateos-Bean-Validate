package com.heateos.controllers;

import com.heateos.models.Product;
import com.heateos.repositorys.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ProductController {

    private final Class<ProductController> productControllerClass = ProductController.class;

    private final ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<EntityModel<Product>> createProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(onPrepareSelfLink(productRepository.save(product)));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Product>>> findAllProduct(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(onPrepareSelfLinks(productRepository.findAll(PageRequest.of(page, size))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findByIdProduct(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }

    private EntityModel<Product> onPrepareSelfLink(final Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(productControllerClass).findByIdProduct(product.getId())).withSelfRel());
    }

    private List<EntityModel<Product>> onPrepareSelfLinks(final Page<Product> productsPage) {
        return productsPage.stream()
                .map(this::onPrepareSelfLink)
                .collect(Collectors.toList());
    }

}