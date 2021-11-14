/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.tecnocampus.courseproject.application;

import cat.tecnocampus.courseproject.application.daos.ProductDAO;
import cat.tecnocampus.courseproject.application.dtos.ProductDTO;
import cat.tecnocampus.courseproject.application.dtos.productApi.ProductResponseList;
import cat.tecnocampus.courseproject.application.dtos.productApi.ProductResponseUnit;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public class ProductController {

    private final RestTemplate restTemplate;
    private ProductDAO productDao;
    private static final String apiUrl = "https://productapi-1635325374837.azurewebsites.net";

    public ProductController(RestTemplate restTemplate, ProductDAO productDao) {
        this.restTemplate = restTemplate;
        this.productDao = productDao;
    }

    /*
    @Scheduled(cron = "0 0 ? * MON")
    public double getNewPrice() {
        double newPrice = 0;
        List<ProductDTO> products = productDao.getAll();
        var price = restTemplate.getForObject(apiUrl + "/api/v1/products/price", ProductDTO.class);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(price.getId())) {
                newPrice = price.getPrice();
            }
        }
        return newPrice;
    }*/
    @Scheduled(cron = "0 0 ? * MON")
    public void updateNewPrice() {
        double newPrice = 0;
        List<ProductDTO> products = productDao.getAll();

        ProductResponseList response = restTemplate.getForObject(apiUrl, ProductResponseList.class);
        List<ProductResponseUnit> newProducts = response.getProducts();

        for (ProductResponseUnit pr : newProducts) {
            productDao.updatePrice(pr.getId(), pr.getPrice());
        }

    }

}
