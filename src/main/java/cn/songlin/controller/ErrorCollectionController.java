package cn.songlin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.annotation.Monitor;
import cn.songlin.entity.TtErrCollection;
import cn.songlin.service.ErrorCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("errCollection")
@Api("错误收集")
@SuppressWarnings(value = { "all" })
public class ErrorCollectionController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorCollectionController.class);

	@Autowired
	private ErrorCollectionService collService;

	@PostMapping("/saveOrUpdate")
	@Monitor
	@ApiOperation(value = "新增bug或更新bug")
	public ResponseEntity<Void> saveOrUpdate(@RequestBody TtErrCollection errColl) {
		collService.saveOrUpdate(errColl);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
