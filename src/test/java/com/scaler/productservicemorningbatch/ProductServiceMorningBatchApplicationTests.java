package com.scaler.productservicemorningbatch;

import com.scaler.productservicemorningbatch.models.Category;
import com.scaler.productservicemorningbatch.models.Product;
import com.scaler.productservicemorningbatch.repositories.CategoryRepository;
import com.scaler.productservicemorningbatch.repositories.ProductRepository;
import com.scaler.productservicemorningbatch.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceMorningBatchApplicationTests {
    @Autowired // Dependency Injection
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional//Added this to help to persist another category also
    @Commit
    public void testQueries() {
//        List<ProductWithIdAndTitle> products = productRepository.someRandomQuery();
//
//        for (ProductWithIdAndTitle product : products) {
//            System.out.println(product.getId());
//            System.out.println(product.getTitle());
//        }

        //Step 1: Create new products for database



        Category c=new Category();
        c.setTitle("Test Category");

        Product p=new Product();
        p.setDescription("Test Desc");
        p.setTitle("Test Product");
        p.setPrice(100);
        p.setCategory(c);
        Product savedP=productRepository.save(p);

        Product p1=new Product();
        p1.setDescription("Test Desc");
        p1.setTitle("Test Product");
        p1.setPrice(100);
        p1.setCategory(c);
        Product savedP1=productRepository.save(p1);

        Category savedCategory=savedP1.getCategory();
        System.out.println(savedCategory.getId());

        //Print before deleting category with cascade type=remove
        printAllProducts();
        printAllCategories();



        //Get the top product in the table
        System.out.println("PROJECTIONS DEMO");
        List<Product> allProducts=productRepository.findAll();
        if(!allProducts.isEmpty()){
            for(Product projProd:allProducts){
                //PROJECTIONS are tagged to an interface as below
                ProductWithIdAndTitle product = productRepository.doSomething(projProd.getId());
                System.out.println(product.getId());
                System.out.println(product.getTitle());
            }

        }

        List<Category> allCategories=categoryRepository.findAll();
        if(!allCategories.isEmpty()){
            for(Category category:allCategories){
                System.out.println("EAGER Fetching all products-->"+category.getProducts());
            }
        }





        //CLEANUP ----> deleting the products
        //System.out.println("CLEANUP Deleting all products");
        //productRepository.deleteAll();
        // deleting the category
        //categoryRepository.deleteAll();
        //System.out.println("Deleted all categories");

    }

    public void printAllCategories(){
        List<Category> categories=categoryRepository.findAll();
        System.out.println("Existing list of categories ");
        for(Category category:categories){
            System.out.println(category.getId());
        }
    }

    public void printAllProducts(){
        List<Product> products=productRepository.findAll();
        System.out.println("Existing list of products ");
        for(Product product:products){
            System.out.println(product.getId());
        }
    }

}
