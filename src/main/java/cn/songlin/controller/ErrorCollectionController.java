package cn.songlin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.songlin.annotation.Monitor;
import cn.songlin.dto.base.ResponseBeanResult;
import cn.songlin.dto.base.ResponsePageResult;
import cn.songlin.dto.errColl.ErrCollectionAddDto;
import cn.songlin.dto.errColl.ErrCollectionDetailDto;
import cn.songlin.dto.errColl.ErrListQueryDto;
import cn.songlin.service.ErrorCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("errCollection")
@Api("错误收集")
@ResponseBody
@SuppressWarnings(value = { "all" })
public class ErrorCollectionController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorCollectionController.class);

	@Autowired
	private ErrorCollectionService collService;

	@PostMapping("/errDel")
	@Monitor
	@ApiOperation(value = "删除")
	public ResponseBeanResult<Void> errDel(@RequestParam long errCollId) {
		collService.errDel(errCollId);
		return new ResponseBeanResult();
	}
	
	@PostMapping("/errList")
	@Monitor
	@ApiOperation(value = "错误列表")
	public ResponsePageResult errList(@RequestBody ErrListQueryDto queryDto) {
		ResponsePageResult responsePageResult = collService.errlist(queryDto);
		return responsePageResult;
	}

	@PostMapping("/saveOrUpdate")
	@Monitor
	@ApiOperation(value = "新增bug或更新bug")
	public ResponseBeanResult saveOrUpdate(@RequestBody ErrCollectionAddDto dto) {
		collService.saveOrUpdate(dto);
		return new ResponseBeanResult();
	}
	
	@PostMapping("/detail")
	@Monitor
	@ApiOperation(value = "新增bug或更新bug")
	public ResponseBeanResult<ErrCollectionDetailDto> detail(@RequestParam long errCollId) {
		ErrCollectionDetailDto data = collService.detail(errCollId);
		return new ResponseBeanResult(data);
	}
}
