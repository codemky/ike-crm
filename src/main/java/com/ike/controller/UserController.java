package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.ExcelUtil;
import com.ike.pojo.Department;
import com.ike.pojo.Position;
import com.ike.pojo.User;
import com.ike.pojo.vo.UserVo;
import com.ike.service.DepartmentService;
import com.ike.service.PositionService;
import com.ike.service.UserService;
import com.sun.tools.javac.jvm.Code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Api(value = "用户控制器", description = "用户信息模块(已添加权限配置,需要登陆[Kyre 123456]才能测试)")
@RestController
@RequestMapping("/json/user")
@RequiresAuthentication
@RequiresPermissions(value = {"manage_emp:read"})
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;

    @GetMapping("/getDetail")
    @ApiOperation(value = "根据id获取用户信息(测试通过)", notes = "根据id获取用户信息")
    public Result<Object> detail(@RequestParam Long id) {
        UserVo userVo = userService.selectById(id);
        return userVo == null ? Result.error(CodeMsg.ERROR) : Result.success(userVo);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增一条用户记录,用户名、邮箱必须唯一(测试通过)", notes = "新增一条用户记录")
    public Result<Object> create(User user) {
        String email = user.getEmail();
        String str_email = "";
        if (email.startsWith("www.")) {
            str_email = email.substring(4);
            user.setEmail(str_email);
        }

        int insert = userService.insert(user);
        return insert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @GetMapping("/checkUserName")
    @ApiOperation(value = "验证用户名是否被占用(测试通过)", notes = "验证用户名")
    public Result<Object> checkUserName(@RequestParam String userName) {
        Optional<User> nameOptional = userService.findUserByName(userName);
        if (!nameOptional.isPresent()) {
            return Result.error(CodeMsg.USERNAME_BEEN_USED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @GetMapping("/checkEmail")
    @ApiOperation(value = "验证邮箱是否被注册(测试通过)", notes = "验证邮箱")
    public Result<Object> checkEmail(@RequestParam String email) {
        Optional<User> emailOptional = userService.findUserByEmail(email);
        if (!emailOptional.isPresent()) {
            return Result.error(CodeMsg.EMAIL_BEEN_USED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新一条用户记录(测试通过)", notes = "更新一条用户记录")
    public Result<Object> update(User user,@RequestBody User userVo) {
        int update = userService.update(userVo);
        return update > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据Id删除一条用户记录(测试通过)", notes = "根据Id删除一条用户记录")
    public Result<Object> delete(@RequestBody Long id) {
        int delete = userService.deleteById(id);
        return delete > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部用户信息(测试通过)", notes = "获取用户列表")
    public Result listAll() {
        List<UserVo> userVos = userService.selectAll();
        return userVos == null ? Result.error(CodeMsg.ERROR) : Result.success(userVos);
    }

    @GetMapping("/search")
    @ApiOperation(value = "根据用户名搜索,获取用户信息(测试通过)", notes = "根据用户名搜索获取用户信息")
    public Result<Object> search(@RequestParam String name) {
        Optional<List<User>> optional = userService.findUserLikeName(name);
        return optional.isPresent() ? Result.success(optional.get()) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "将User的Excel表数据导入到数据库(测试通过)", notes = "批量导入")
    @PostMapping("/import")
    public Result<Object> importExcel(MultipartFile file) throws Exception {
        if (null == file)
            return Result.error(new CodeMsg("文件读取错误, 无效的文件!"));

        List<Object> list = ExcelUtil.readExcel(file, new UserVo());
        System.out.println(list);
        if (null == list || list.size() == 0)
            return Result.error(new CodeMsg("文件数据为空!"));

        List<UserVo> importList = new LinkedList<>();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            UserVo userVo = (UserVo) list.get(i);
            String username = userVo.getUsername();
            if (null == username) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 用户名不能为空"));
            }

            Optional<User> user = userService.findUserByName(username);
            if (user.isPresent()) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 用户名已被占用"));
            }

            String name = userVo.getName();
            if (null == name) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 姓名不能为空"));
            }

            String genderStr = userVo.getGenderStr();
            System.out.println(genderStr.equals("男") + "==" + genderStr.equals("女"));
            if ((genderStr.equals("男") && genderStr.equals("女")) || null == genderStr) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 性别不合法或为空"));
            }

            if (genderStr.equals("男")) {
                userVo.setGender((byte) 1);
            }

            if (genderStr.equals("女")) {
                userVo.setGender((byte) 0);
            }

            String departmentName = userVo.getDepartmentName();
            Department department = departmentService.selectByDepartmentName(departmentName);
            if (null == department) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 部门不存在"));
            }
            userVo.setDepartmentId(department.getId());

            String positionName = userVo.getPositionName();
            Position position = positionService.selectByPositionName(positionName);
            if (null == position) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 职位不存在"));
            }
            userVo.setPositionId(position.getId());

            String phone = userVo.getPhone();
            if (null == phone) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 手机号码不能为空"));
            }

            String phoneRegex = "^1[3|4|5|8][0-9]\\d{8}$";
            boolean phoneIsMatche = phone.matches(phoneRegex);
            if (!phoneIsMatche) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 手机号码不合法"));
            }

            String email = userVo.getEmail();
            if (null == email) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 邮箱不能为空"));
            }

            Optional<User> userByEmail = userService.findUserByEmail(email);
            if (userByEmail.isPresent()) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 邮箱已被注册"));
            }

            String emailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            boolean emailIsMatche = email.matches(emailRegex);
            if (!emailIsMatche) {
                return Result.error(new CodeMsg("文件第" + (i + 1) + "行出错,出错原因: 邮箱不合法"));
            }

            if (email.startsWith("www.")) {
                userVo.setEmail(email.substring(4));
            }


            userVo.setStatus((byte) 0); //账号状态
            String salt = UUID.randomUUID().toString().substring(0, 8);
            userVo.setSalt(salt);  //加密盐值
            Md5Hash password = new Md5Hash("123456", salt, 1); //初始化密码为123456
            userVo.setPassword(password.toString());

            System.out.println("userVo:  " + userVo);
            importList.add(userVo);
        }
        System.out.println("importList:  " + importList);
        userService.importExcel(importList);
        return Result.success(CodeMsg.SUCCESS);
    }

    @ApiOperation(value = "将全部用户信息导出到Excel表,需要在浏览器中测试(测试通过)")
    @GetMapping("/exportExcel")
    public void excel(HttpServletResponse response) {
        String fileName = "用户信息";
        String sheetName = "Sheet1";
        List<UserVo> exportList = userService.getExportList();
        int size = exportList.size();
        for (int i = 0; i < size; i++) {
            UserVo userVo = exportList.get(i);
            if (userVo.getGender() == 0) {
                exportList.get(i).setGenderStr("女");
            } else {
                exportList.get(i).setGenderStr("男");
            }
        }
        ExcelUtil.writeExcel(response, exportList, fileName, sheetName, new UserVo());
    }

    @GetMapping("/downloadExcelTemplate")
    @ApiOperation(value = "下载用户信息导入模板m需要在浏览器中访问测试(测试通过)")
    public ResponseEntity<byte[]> downloadExcelTemplate() throws Exception {
        try {
            File file = ResourceUtils.getFile("classpath:excelTemplate/用户信息导入模板.xlsx");
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
