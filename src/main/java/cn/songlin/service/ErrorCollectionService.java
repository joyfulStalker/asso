package cn.songlin.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.songlin.dto.base.ResponsePageResult;
import cn.songlin.dto.errColl.ErrCollectionDto;
import cn.songlin.dto.errColl.ErrListQueryDto;
import cn.songlin.entity.TtErrCollection;
import cn.songlin.mapper.TtErrCollectionMapper;
import cn.songlin.utils.MyStringUtils;

@Service
@Transactional
public class ErrorCollectionService {

	private static final Logger logger = LoggerFactory.getLogger(ErrorCollectionService.class);

	@Autowired
	private TtErrCollectionMapper errMapper;

	public void saveOrUpdate(TtErrCollection errColl) {

		if (errColl.getId() == null) {
			errColl.setCreateTime(new Date());
			errMapper.insert(errColl);
		} else {
			errColl.setUpdateTime(new Date());
			if (errColl.getErrSolution() != null) {
				errColl.getSolveTime();//
			}
			errMapper.updateByPrimaryKeySelective(errColl);
		}
	}

	public ResponsePageResult errlist(ErrListQueryDto queryDto) {

		PageHelper.startPage(queryDto.getPage(), queryDto.getRows());

		// 处理模糊查询
		queryDto.setErrCauseBy(MyStringUtils.dealLikeStr(queryDto.getErrCauseBy()));
		queryDto.setErrDescription(MyStringUtils.dealLikeStr(queryDto.getErrDescription()));
		queryDto.setErrSolution(MyStringUtils.dealLikeStr(queryDto.getErrSolution()));

		List<ErrCollectionDto> list = errMapper.errList(queryDto);

		PageInfo<ErrCollectionDto> page = new PageInfo<>(list);

		return new ResponsePageResult(page.getList(), page.getTotal());
	}

	public TtErrCollection detail(Long errCollId) {
		return errMapper.selectByPrimaryKey(errCollId);
	}

}
