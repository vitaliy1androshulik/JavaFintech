package org.example.seeder;

import lombok.AllArgsConstructor;
import org.example.entities.ProductEntity;
import org.example.entities.ProductImageEntity;
import org.example.repository.ICategoryRepository;
import org.example.repository.IProductImageRepository;
import org.example.repository.IProductRepository;
import org.example.services.FileService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
public class ProductSeeder {

    private IProductRepository productRepository;
    private IProductImageRepository productImageRepository;
    private ICategoryRepository categoryRepository;
    private FileService fileService;

    public void seed() {
        if(productRepository.count() > 0) return;

        var categories = categoryRepository.findAll();
        var random = new Random();

        var product1 = new ProductEntity();
        product1.setName("IPhone 16");
        product1.setDescription("8/256");
        product1.setCreationTime(LocalDateTime.now());
        product1.setPrice(1000.0);
        product1.setCategory(categories.get(random.nextInt(categories.size())));

        productRepository.save(product1);

        var imageName = fileService
                .load("https://estore.ua/media/catalog/product/cache/8/image/650x650/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone-16-pro-finish-select-202409-6-3inch-naturaltitanium.png");
        var img1 = new ProductImageEntity();
        img1.setPriority(1);
        img1.setName(imageName);
        img1.setProduct(product1);
        productImageRepository.save(img1);

        imageName = fileService
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Oryctolagus_cuniculus_Tasmania_2.jpg/719px-Oryctolagus_cuniculus_Tasmania_2.jpg");
        var img11 = new ProductImageEntity();
        img11.setPriority(2);
        img11.setName(imageName);
        img11.setProduct(product1);
        productImageRepository.save(img11);

        var product2 = new ProductEntity();
        product2.setName("Jacket");
        product2.setDescription("very nice");
        product2.setCreationTime(LocalDateTime.now());
        product2.setPrice(150.0);
        product2.setCategory(categories.get(random.nextInt(categories.size())));

        productRepository.save(product2);

        imageName = fileService
                .load("https://parkas.com.ua/wa-data/public/shop/products/34/01/134/images/377/377.970.jpg");
        //product2.setImage(imageName);
        var img2 = new ProductImageEntity();
        img2.setPriority(1);
        img2.setName(imageName);
        img2.setProduct(product2);
        productImageRepository.save(img2);

        var product3 = new ProductEntity();
        product3.setName("C++");
        product3.setDescription("good book");
        product3.setCreationTime(LocalDateTime.now());
        product3.setPrice(20.0);
        product3.setCategory(categories.get(random.nextInt(categories.size())));
        productRepository.save(product3);

        imageName = fileService
                .load("https://images.booksense.com/images/740/539/9783986539740.jpg");
        var img3 = new ProductImageEntity();
        img3.setPriority(1);
        img3.setName(imageName);
        img3.setProduct(product3);
        productImageRepository.save(img3);

    }
}