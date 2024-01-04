package org.larissa.buyeasy.database.dao;

import org.junit.jupiter.api.*;
import org.larissa.buyeasy.database.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDAOTest {

    @Autowired
    ProductDAO productDao;

    @Test
    @Order(1)
    public void createProductTest(){

        //given
        Product product = new Product();

        product.setCode("QWERTY");
        product.setName("Product test1");
        product.setDescription("A product for testing");
        product.setPrice(25.09);
        product.setImageUrl("/pub/images/test.jpg");

        //when
        product =productDao.save(product);

        //then
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals("QWERTY", product.getCode());
        Assertions.assertEquals("Product test1", product.getName());
        Assertions.assertEquals("A product for testing", product.getDescription());
        Assertions.assertEquals("/pub/images/test.jpg", product.getImageUrl());
        Assertions.assertEquals(25.09, product.getPrice());

    }


    @Test
    @Order(2)
    public void findBySearchTest() {
        // given
        String search = "Product test1";

        // when
        List<Product> products = productDao.findBySearch(search);

        // then
        Assertions.assertEquals(1, products.size());

        Product product = products.get(0);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals("QWERTY", product.getCode());
        Assertions.assertEquals("Product test1", product.getName());
        Assertions.assertEquals("A product for testing", product.getDescription());
        Assertions.assertEquals("/pub/images/test.jpg", product.getImageUrl());
        Assertions.assertEquals(25.09, product.getPrice());
    }
    @Test
    @Order(3)
    public  void deleteProductTest(){
        //given
        String name = "Product test1";

        //when
        int deleted = productDao.deleteByName(name);

        //then
        Assertions.assertEquals(1, deleted);

    }

    @Test
    @Order(4)
    public void shouldNotExistTest() {
        // given
        String search = "Product test1";

        // when
        List<Product> products = productDao.findBySearch(search);

        // then
        Assertions.assertEquals(0, products.size());
    }
}
