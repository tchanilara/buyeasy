package org.larissa.buyeasy.service;

import org.larissa.buyeasy.database.dao.ProductDAO;
import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.formbean.CreateProductFormBean;
import org.larissa.buyeasy.formbean.RegisterUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDao;

    public Product addProduct(CreateProductFormBean form){
        Product product = new Product();

        product.setCode(form.getCode());
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImageUrl());


        return productDao.save(product);
    }
}
