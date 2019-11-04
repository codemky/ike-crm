package com.ike.controller;

import com.ike.common.result.Result;
import com.ike.file.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wgm
 * @since 2019/6/28
 */
@Api(description = "文件上传")
@CrossOrigin
@RestController
@RequestMapping("/oss/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@ApiOperation(value = "文件上传")
	@PostMapping("upload")
	public Result<String> uploadFile(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam("file") MultipartFile file,
			@ApiParam(name="host", value="文件上传路径", required = false)
			@RequestParam(value = "host", required = false) String host){
		String uploadUrl = fileService.uploadFileToHost(file, host);
		return Result.success(uploadUrl);
	}

	@ApiOperation(value = "批量文件上传")
	@PostMapping("batchUpload")
	public Result batchUploadFile(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam("files") MultipartFile[] files,
			@ApiParam(name="host", value="文件上传路径", required = false)
			@RequestParam(value = "host", required = false) String host){

		List<String> urls = fileService.batchUploadFileToHost(files, host);
		return Result.success(urls);
	}
}
