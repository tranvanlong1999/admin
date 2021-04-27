package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Constants.Constants;
import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.*;
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

    public String createProductByAdmin(Model model, ProductInput productInput,MultipartFile[] files) {
        String result = "Themsanpham";
        String error = null;
        ProductEntity product = null;
        System.out.println(productInput);
        try {
            ProductEntity productEntity = productConverter.toProductInput(productInput);
            BrandEntity brandEntity= brandRepository.findById(productInput.getBrandid()).get();
            CategoriesEntity categoriesEntity= categoriesRepository.findById(productInput.getCategoryid()).get();
            productEntity.setBrandentity(brandEntity);
            productEntity.setCategory(categoriesEntity);
            // save product to db
            productEntity = productRepository.save(productEntity);
            // set data in product
            productEntity.setProductname(productEntity.getProductname() + "MS" + productEntity.getId());
            productEntity.setPath(Utils.formatStringtoUrl(productEntity.getProductname()));
            // save product to db
            productEntity = productRepository.save(productEntity);
            createImagesInProduct(files,productEntity.getId());
            result = "redirect:/sanpham";
        } catch (Exception e) {

        }
        return result;
    }

    public void createImagesInProduct(MultipartFile[] files, Integer productId) {
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


