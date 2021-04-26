package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Constants.Constants;
import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.ImageEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductAtributeEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductCategoriesEntity;
import com.doan.admindonghohanquoc.Model.Entity.ProductEntity;
import com.doan.admindonghohanquoc.Model.Input.ProductAtributeInput;
import com.doan.admindonghohanquoc.Model.Input.ProductCategoriesInput;
import com.doan.admindonghohanquoc.Model.Input.ProductInput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Repository.*;
import com.doan.admindonghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductConverter productConverter;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ProductAtributeRepository productAtributeRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    ProductCategoriesRepository productCategoriesRepository;
    @Autowired
    ImageRepository imagesRepository;

    public List<ProductOutput> getListProduct() {
        List<ProductOutput> productOutputList = new LinkedList<>();
        try {
            Page<ProductEntity> page = productRepository.findAll(PageRequest.of(0, 5));
            for (ProductEntity productEntity : page) {
                productOutputList.add(productConverter.toProductEntity(productEntity));
            }
        } catch (Exception e) {

        }
        return productOutputList;

    }

    public String createProductByAdmin(Model model, ProductInput productInput) {
        String result = "Themsanpham";
        String error = null;
        ProductEntity product = null;
        System.out.println(productInput);
        try {
            List<ProductAtributeEntity> productAtributeEntityList = new ArrayList<>();
            List<ProductCategoriesEntity> productCategoriesEntityList = new ArrayList<>();
            List<ProductAtributeInput> productAtributeInputList = productInput.getProductAtributeInputList();
            List<ProductCategoriesInput> productCategoriesInputList = productInput.getProductCategoriesInputList();
            ProductEntity productEntity = productConverter.toProductInput(productInput);
            // case brand is null
            if (ObjectUtils.isEmpty(productEntity.getBrandentity()))
                productEntity.setBrandentity(brandRepository.findById(Constants.BRAND_DEFAULT).get());
            // save product to db
            productEntity = productRepository.save(productEntity);
            // set data in product
            productEntity.setProductname(productEntity.getProductname() + "MS" + productEntity.getId());
            productEntity.setPath(Utils.formatStringtoUrl(productEntity.getProductname()));
            // save product to db
            productEntity = productRepository.save(productEntity);
            //set data to list product atribtute
            if (productAtributeInputList.size() > 0)
                for (ProductAtributeInput productAtributeInput : productAtributeInputList) {
                    ProductAtributeEntity productAtributeEntity = new ProductAtributeEntity();
                    productAtributeEntity.setProductentity(productEntity);
                    productAtributeEntity.setSizeentity(sizeRepository.findById(productAtributeInput.getSizeid()).get());
                    productAtributeEntity.setColorentity(colorRepository.findById(productAtributeInput.getColorid()).get());
                    productAtributeEntityList.add(productAtributeEntity);
                }
            // save product atribute vao db
            productAtributeRepository.saveAll(productAtributeEntityList);
            System.out.println("aaa");
            // set data to list product categories
            for (ProductCategoriesInput productCategoriesInput : productCategoriesInputList) {
                ProductCategoriesEntity productCategoriesEntity = new ProductCategoriesEntity();
                productCategoriesEntity.setProductEntity(productEntity);
                productCategoriesEntity.setCategoriesEntity(categoriesRepository.findById(productCategoriesInput.getCategoriesid()).get());
                productCategoriesEntityList.add(productCategoriesEntity);
            }
            // save productcategories vao db
            productCategoriesRepository.saveAll(productCategoriesEntityList);

            result = "redirect:/sanpham";
        } catch (Exception e) {

        }
        return result;
    }

    public void createImagesInProduct(MultipartFile[] files, Integer productId) {
        String result = "uploadfile";
        try {
            List<ImageEntity> images = new ArrayList<>();
            int i = 0;
            int length = files.length;
            String fileName;
            // get product by id in db
            ProductEntity product = productRepository.findById(productId).orElseGet(null);
            if (ObjectUtils.isEmpty(product)) {
                throw new Exception();
            }

            for (i = 0; i < length; i++) {
                ImageEntity image = new ImageEntity();
                StringBuilder imagePath = new StringBuilder();
                // get file name in file
                fileName = files[i].getOriginalFilename();

                // set image path
                imagePath.append(UUID.randomUUID().toString());
                imagePath.append(fileName.substring(fileName.length() - 8));

                File convFile = new File("src/main/resources/static/" + imagePath.toString());

                if (convFile.createNewFile()) {
                    FileOutputStream fos = new FileOutputStream(convFile);
                    fos.write(files[i].getBytes());
                    fos.close();
                }

                // set data to list image
                image.setPath(Constants.BASE_IMAGE_URL + imagePath.toString());
                image.setProduct(product);
                images.add(image);
            }
            // delete all image of product
            imagesRepository.deleteAll(imagesRepository.findByProduct(product));

            // save list image in db
            imagesRepository.saveAll(images);

            // update info product
            product.setImage(images.get(0).getPath());

            // save product
            productRepository.save(product);
        } catch (Exception e) {

        }
    }

}


