package com.example.fastfood.Model;

public class Product {
    private String tv_name_product;
    private String img_product;
    private String product_type;

    public String getTv_describe_product() {
        return tv_describe_product;
    }

    public void setTv_describe_product(String tv_describe_product) {
        this.tv_describe_product = tv_describe_product;
    }

    private String tv_describe_product;
    private int tv_price_product;
    public Product(){

    }
    public Product(String tv_name_product, String img_product, String product_type, String tv_describe_product, int tv_price_product) {
        this.tv_name_product = tv_name_product;
        this.tv_describe_product = tv_describe_product;
        this.img_product = img_product;
        this.product_type = product_type;
        this.tv_price_product = tv_price_product;
    }

    public String getTv_name_product() {
        return tv_name_product;
    }

    public void setTv_name_product(String tv_name_product) {
        this.tv_name_product = tv_name_product;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getTv_price_product() {
        return tv_price_product;
    }

    public void setTv_price_product(int tv_price_product) {
        this.tv_price_product = tv_price_product;
    }
}
