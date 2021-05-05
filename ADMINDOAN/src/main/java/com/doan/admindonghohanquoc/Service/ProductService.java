package com.doan.admindonghohanquoc.Service;

import com.doan.admindonghohanquoc.Common.MessageConstant;
import com.doan.admindonghohanquoc.Common.Validate;
import com.doan.admindonghohanquoc.Constants.Constants;
import com.doan.admindonghohanquoc.Converter.ProductConverter;
import com.doan.admindonghohanquoc.Model.Entity.*;
import com.doan.admindonghohanquoc.Model.Input.ProductAtributeInput;
import com.doan.admindonghohanquoc.Model.Input.ProductCategoriesInput;
import com.doan.admindonghohanquoc.Model.Input.ProductInput;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.OutPut.ProductOutput;
import com.doan.admindonghohanquoc.Model.OutPut.UserOutput;
import com.doan.admindonghohanquoc.Repository.*;
import com.doan.admindonghohanquoc.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

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
            Page<ProductEntity> page = productRepository.findAll(PageRequest.of(0, 100));
            for (ProductEntity productEntity : page) {
                if(productEntity.getStatus()==1) {
                    productOutputList.add(productConverter.toProductEntity(productEntity));
                }
            }
        } catch (Exception e) {

        }
        return productOutputList;

    }

    public String createProductByAdmin(Model model, ProductInput productInput,MultipartFile[] files) {
        String result = "Themsanpham";
        String error = null;
        ProductEntity product = null;
        try {
            if(Validate.checkInputProduct(productInput))
            {
                List<ProductAtributeEntity>  productAtributeEntities= new ArrayList<>();
                ProductEntity productEntity = productConverter.toProductInput(productInput);
                BrandEntity brandEntity= brandRepository.findById(productInput.getBrandid()).get();
                CategoriesEntity categoriesEntity= categoriesRepository.findById(productInput.getCategoryid()).get();
                List<ProductAtributeInput> productAtributeInputs= productInput.getProductAtributeInputs();
                productEntity.setBrandentity(brandEntity);
                productEntity.setCategory(categoriesEntity);
                // save product to db
                productEntity = productRepository.save(productEntity);
                // set data in product
                productEntity.setProductname(productEntity.getProductname() + "MS" + productEntity.getId());
                productEntity.setPath(Utils.formatStringtoUrl(productEntity.getProductname()));
                // save product to db
                productEntity = productRepository.save(productEntity);
                for (ProductAtributeInput input: productAtributeInputs) {
                    ProductAtributeEntity productAtributeEntity= new ProductAtributeEntity();
                    productAtributeEntity.setProductentity(productEntity);
                    productAtributeEntity.setSizeentity(sizeRepository.findById(input.getSizeid()).get());
                    productAtributeEntity.setColorentity(colorRepository.findById(input.getColorid()).get());
                    productAtributeEntity.setQuantity(input.getQuantity());
                    productAtributeEntities.add(productAtributeEntity);
                }
                productAtributeRepository.saveAll(productAtributeEntities);
                createImagesInProduct(files,productEntity.getId());
                result = "redirect:/sanpham";
            }

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
    public String deleteProductByAdmin(Integer id, Model model) {
        String error = null;
        String result = "";
        try {
            // lay ra product can xoa dua vao id
            ProductEntity productEntity = productRepository.findById(id).get();
            if (ObjectUtils.isEmpty(productEntity)) {
                throw new Exception("Product not exist");
            }
            //xóa logic
            productEntity.setStatus(0);
            productRepository.save(productEntity);
            List<ProductAtributeEntity> productAtributeEntityList= productAtributeRepository.findByProductId(id);
            productAtributeRepository.deleteAll(productAtributeEntityList);
        } catch (Exception e) {
            error = MessageConstant.DELETE_ERROR;
        }
        model.addAttribute("error", error);
        return "redirect:/sanpham";
    }
    public String updateproduct(ProductOutput productOutput,MultipartFile[] files) {
        String error = null;
        String result = "Chinhsuasanpham";
        System.out.println(productOutput);
        ProductEntity productEntity= productRepository.findById(productOutput.getId()).get();

        if(!ObjectUtils.isEmpty(productEntity))
        {
            productEntity.setProductname(productOutput.getProductname());
            productEntity.setDescription(productOutput.getDescription());
            productEntity.setBrandentity(brandRepository.findById(productOutput.getBrandid()).get());
            productEntity.setCategory(categoriesRepository.findById(productOutput.getCategoryid()).get());
            productEntity.setPrice(productOutput.getPrice());
            productEntity.setUpdatedat(new Date());
            // get image of product
            List<ImageEntity> imageEntityList= imagesRepository.findAll();
            imagesRepository.deleteAll(imageEntityList);
            //
            createImagesInProduct(files,productOutput.getId());
            productRepository.save(productEntity);
            result="redirect:/sanpham";
        }
        return result;
    }


}


