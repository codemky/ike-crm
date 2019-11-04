package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.ExcelUtil;
import com.ike.pojo.*;
import com.ike.pojo.vo.*;
import com.ike.file.FileService;
import com.ike.service.ProductClassService;
import com.ike.service.ProductFileService;
import com.ike.service.ProductService;
import com.ike.service.SaleUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 11:29
 * @Version 1.0
 */
@RestController
@Api(description = "产品模块")
@RequestMapping("/product/")
@Transactional
public class ProductController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ProductFileService productFileService;
    @Autowired
    private ProductClassService productClassService;
    @Autowired
    private SaleUnitService saleUnitService;

    @ApiOperation(value = "查找产品信息")
    @GetMapping("listById")
    public Result listById(User user, @RequestParam(value = "id") Long id) {
        if(id == null){
            return Result.error(CodeMsg.PROD_FAILURE);
        }
        ProductMsgVO productMsgVO = new ProductMsgVO();
        productMsgVO.setProductExtVO(productService.listById(id));
        productMsgVO.setProductPicFile(productFileService.listByProId(id, 1));
        productMsgVO.setProductTextFile(productFileService.listByProId(id, 2));
        return Result.success(productMsgVO);
    }

    @ApiOperation(value = "查找产品详情信息")
    @GetMapping("listDetailById")
    public Result listDetailById(User user, @RequestParam(value = "id") Long id) {
        if(id == null){
            return Result.error(CodeMsg.PROD_FAILURE);
        }
        ProductDetailVO productDetailVO = new ProductDetailVO();
        ProductExtVO productExtVO = productService.listById(id);
        ProductVO productVO = productService.listDetailById(id);
        List<ProductFile> productFiles = productFileService.listByProId(id,1);
        productDetailVO.setProductExtVO(productExtVO);
        productDetailVO.setProductVO(productVO);
        productDetailVO.setProductFile(productFiles);
        return Result.success(productDetailVO);
    }

    @ApiOperation(value = "分页显示所有产品信息")
    @GetMapping("listAll")
    public Result listAll(User user, @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
        Page<ProductVO> page = new Page<>(pageNum,pageSize);
        IPage<ProductVO> productVOIPage = productService.listAll(page);

        return Result.success(productVOIPage);
    }

    @ApiOperation(value = "分页显示产品成交记录")
    @GetMapping("listTradedProducts")
    public Result listTradedProducts(User user, @RequestParam(value = "id") Long id,
                                     @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
        if(id == null){
            return Result.error(CodeMsg.PROD_FAILURE);
        }
        Page<TradedProductVO> page = new Page<>(pageNum,pageSize);
        IPage<TradedProductVO> tradedProductVOIPage = productService.listTradedProducts(page,id);
        return Result.success(tradedProductVOIPage);
    }

    @ApiOperation(value = "产品销量统计")
    @GetMapping("listSaleStatistic")
    public Result listSaleStatistic(User user, @RequestParam(value = "id") Long id,
                                    @RequestParam(value = "type", required = true) String type,
                                    @RequestParam(value = "year", required = false) Integer year,
                                    @RequestParam(value = "month", required = false) Integer month){

        switch (type) {
            case "year":
                break;
            case "month":
                if (null == year) return Result.error(new CodeMsg("统计月份，年份不能为空"));
                break;
            case "day":
                if (null == month || null == year) return Result.error(new CodeMsg("统计日数据，年月不能为空"));
                break;
            default:
                return Result.error(new CodeMsg("统计类型只能是年月日"));
        }
        List<ProStatisticVO> proStatisticVO = productService.listByYMD(id,type,year,month);

        return Result.success(proStatisticVO);
    }

    @ApiOperation(value = "产品搜索功能")
    @GetMapping("searchProduct")
    public Result searchProduct(User user, @RequestParam(value = "beginCreateTime", required = false) String BeginCreateTime,
                                @RequestParam(value = "endCreateTime", required = false) String EndCreateTime,
                                @RequestParam(value = "orderNumMin",required = false) Integer orderNumMin,
                                @RequestParam(value = "orderNumMax",required = false) Integer orderNumMax,
                                @RequestParam(value = "orderTimesMin",required = false) Integer orderTimesMin,
                                @RequestParam(value = "orderTimesMax",required = false) Integer orderTimesMax,
                                @RequestParam(value = "orderCountMin",required = false) Double orderCountMin,
                                @RequestParam(value = "orderCountMax",required = false) Double orderCountMax,
                                @RequestParam(value = "orderProfitMin",required = false) Double orderProfitMin,
                                @RequestParam(value = "orderProfitMax",required = false) Double orderProfitMax,
                                @RequestParam(value = "createUserName",required = false) String createUserName,
                                @RequestParam(value = "productClassId",required = false) Long productClassId  ,
                                @RequestParam(value = "productName",required = false) String productName,
                                @RequestParam(value = "salePriceMin",required = false) Double salePriceMin,
                                @RequestParam(value = "salePriceMax",required = false) Double salePriceMax,
                                @RequestParam(value = "costMin",required = false) Double costMin,
                                @RequestParam(value = "costMax",required = false) Double costMax,
                                @RequestParam(value = "saleUnitId",required = false) Long saleUnitId,
                                @RequestParam(value = "onSale",required = false) Byte onSale,
                                @RequestParam(value = "beginTtm", required = false) String beginTtm,
                                @RequestParam(value = "endTtm", required = false) String endTtm,
                                @RequestParam(value = "isSold", required = false) Integer isSold,
                                @RequestParam(value = "sortName", required = false) String sortName,
                                @RequestParam(value = "sortType", required = false) String sortType,
                                @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){

        ProductSearchVO searchVO = ProductSearchVO.getProperties(request,new ProductSearchVO());
        Page<ProductVO> page = new Page<>(pageNum,pageSize);
        IPage<ProductVO> productVOIPage = productService.listAllBySelect(page,searchVO);
        return Result.success(productVOIPage);
    }

    @ApiOperation(value = "添加产品图片")
    @PostMapping(value = "createPicture", consumes = "multipart/form-data")
    public Result createProductPicture(User user,
                                       @ApiParam(value = "产品图片", name = "files", required = true)
                                       @RequestParam("files") MultipartFile[] files,
                                       @ApiParam(value = "图片文件路径", name = "host", required = true)
                                       @RequestParam(value = "host", defaultValue = "productPicture") String host) {
        if (files == null) {
            return Result.error(CodeMsg.PROD_FILE_FAILURE);
        }

        List<String> lists = fileService.batchUploadFileToHost(files, host);
        return Result.success(lists);
    }

    @ApiOperation(value = "添加产品文档")
    @PostMapping(value = "createText", consumes = "multipart/form-data")
    public Result createText(User user, @ApiParam(value = "产品文档", name = "files", required = true)
                             @RequestParam("files") MultipartFile[] files,
                             @ApiParam(value = "文档文件路径", name = "host", required = true)
                             @RequestParam(value = "host", defaultValue = "productText") String host) {
        if(files == null){
            return Result.error(CodeMsg.PROD_FILE_FAILURE);
        }

        List<String> lists = fileService.batchUploadFileToHost(files,host);
        return Result.success(lists);
    }

    @ApiOperation(value = "添加新产品")
    @PostMapping("create")
    public Result createProduct(User user, @RequestBody(required = false) Product product,
                                @RequestParam(value = "lists", required = false) List<String> lists) {
        if(product == null){
            return Result.error(CodeMsg.PROD_FAILURE);
        }
        product.setCreateUserId(user.getId());
        product.setCreateTime(LocalDateTime.now());
        product.setDeleted(false);
        productService.save(product);
        if (lists != null)
            productFileService.save(product.getId(), lists);
        return Result.success(true);
    }

    @ApiOperation("更新产品信息")
    @PostMapping("update")
    public Result Update(@RequestBody ProductExtVO productList, User user) {
        if (productList == null) {
            return Result.error(CodeMsg.PROD_FAILURE);
        }
        productList.setModifyTime(LocalDateTime.now());
        productList.setModifyUserId(user.getId());
        if (productService.update(productList)) {
            if (productList.getPiclists() != null)
                productFileService.update(productList.getId(), productList.getPiclists(), 1);
            if (productList.getTextlists() != null)
                productFileService.update(productList.getId(), productList.getTextlists(), 2);
            return Result.success(true);
        }
        return Result.error(CodeMsg.PROD_UPDATE_FAILURE);
    }

//    @ApiOperation("更新产品销售状态信息")
//    @PutMapping("updateOnSale")
//    public Result UpdateOnSale(@RequestParam("id")Long id,
//                               @RequestParam("onSale")Integer onSale, User user){
//
//        productService.update()
//
//        return Result.error(CodeMsg.PROD_UPDATE_FAILURE);
//    }

    @ApiOperation("删除产品信息")
    @DeleteMapping("delete")
    public Result Delete(User user, @RequestBody List<Long> ids) {
        if (ids == null) {
            return Result.error(CodeMsg.PROD_FAILURE);
        }
        if (!productService.delete(ids)) {
            return Result.error(new CodeMsg("删除产品失败"));
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @ApiOperation(value = "将Product的Excel文件导入数据库")
    @PostMapping("ImportExcel")
    public Result ImportExcel(User user, MultipartFile file) {
        if (null == file)
            return Result.error(new CodeMsg("文件读取错误，无效的文件!"));

        List<Object> lists = ExcelUtil.readExcel(file, new ProductExtVO());

        if (null == lists || lists.size() == 0) return Result.error(new CodeMsg("文件数据为空!"));

        int size = lists.size();
        List<ProductExtVO> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ProductExtVO product = (ProductExtVO) lists.get(i);
            //判断产品名称+型号是否重复
            String productName = product.getProductName();
            String productType = product.getProductType();
            if (productType == null)
                product.setProductType("");
            if (productService.selectByNameAndType(productName, product.getProductType()).size() != 0) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：产品名称类型重复"));
            }
            //判断销售单价是否合法
            if (product.getSalePrice() == null) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：产品销售单价不能为空"));
            }
            //判断成本是否合法
            if (product.getCost() == null) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：产品成本不能为空"));
            }
            //判断单位是否存在
            List<SaleUnit> saleUnit = saleUnitService.listByName(product.getSaleUnitName());
            if (saleUnit.size() == 0)
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：单位不存在"));
            product.setSaleUnitId(saleUnit.get(0).getId());
            //判断销售状态是否合法
            if (product.getOnSaleName().equals("上架"))
                product.setOnSale((byte) 1);
            else if (product.getOnSaleName().equals("下架"))
                product.setOnSale((byte) 0);
            else
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：销售状态不合法"));
            //判断产品类型是否存在
            List<ProductClass> productClass = productClassService.listByName(product.getProductClassName());
            if (productClass.size() == 0)
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：产品类型不存在"));
            product.setProductClassId(productClass.get(0).getId());
            //判断上市时间是否合法
            if (product.getStrTtm() == null)
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错，出错原因：上市时间不能为空"));
            //介绍不判断
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(product.getStrTtm(), date);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
            product.setTtm(localDateTime);
            product.setCreateUserId(user.getId());
            product.setCreateTime(LocalDateTime.now());
            products.add(product);
        }
        //批量插入数据库
        productService.insertList(products);
        return Result.success(CodeMsg.SUCCESS);
    }

    @ApiOperation(value = "导出产品Excel")
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        String fileName = "产品信息";
        String sheetName = "产品";
        List<ProductExtVO> excelList = productService.getExcelList();
        for (ProductExtVO productExtVO : excelList) {
            int i = productExtVO.getOnSale();
            if (i == 1) {
                productExtVO.setOnSaleName("上架");
            } else if (i == 0) {
                productExtVO.setOnSaleName("下架");
            } else {
                return;
            }
        }
        ExcelUtil.writeExcel(response, excelList, fileName, sheetName, new ProductExtVO());
    }

    @GetMapping("/downloadExcelTemplate")
    @ApiOperation(value = "下载用户信息导入模板m需要在浏览器中访问测试(测试通过)")
    public ResponseEntity<byte[]> downloadExcelTemplate() throws Exception {
        try {
            File file = ResourceUtils.getFile("classpath:excelTemplate/产品信息导入模板.xls");
            String fileName = file.getName();
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("读取模板文件出错或不存在!");
        }
    }

}