package cn.songlin.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.songlin.common.dto.base.ResponsePageResult;
import cn.songlin.common.exception.AssoException;
import cn.songlin.common.utils.MyStringUtils;
import cn.songlin.dto.errColl.ErrCollectionAddDto;
import cn.songlin.dto.errColl.ErrCollectionDetailDto;
import cn.songlin.dto.errColl.ErrCollectionDto;
import cn.songlin.dto.errColl.ErrListQueryDto;
import cn.songlin.dto.errColl.ErrReferDto;
import cn.songlin.entity.TtErrCollection;
import cn.songlin.entity.TtErrRefer;
import cn.songlin.mapper.TtErrCollectionMapper;
import cn.songlin.mapper.TtErrReferMapper;

@Service
@Transactional
@SuppressWarnings(value = { "all" })
public class ErrorCollectionService {

	private static final Logger logger = LoggerFactory.getLogger(ErrorCollectionService.class);

	@Autowired
	private TtErrCollectionMapper errMapper;

	@Autowired
	private TtErrReferMapper referMapper;

	public void saveOrUpdate(ErrCollectionAddDto dto) {

		TtErrCollection errColl = new TtErrCollection();
		BeanUtils.copyProperties(dto, errColl);// 复制数据

		if (StringUtils.isEmpty(errColl.getErrDescription())) {
			throw AssoException.NO_ERR_DESCRIPTION;
		}
		if (StringUtils.isEmpty(errColl.getCategory())) {
			throw AssoException.NO_ERR_CATEGORY;
		}
		if (errColl.getErrSolution() != null) {
			errColl.setSolveTime(new Date());
		}
		// 执行错误收集插入
		if (errColl.getId() == null) {
			errColl.setCreateTime(new Date());
			errColl.setStatus((byte) 1);
			errMapper.insert(errColl);
		} else {
			errColl.setUpdateTime(new Date());
			errMapper.updateByPrimaryKeySelective(errColl);
		}
		// 判断是否存在引用
		List<ErrReferDto> refers = dto.getRefers();
		if (null != refers && refers.size() > 0) {
			for (ErrReferDto errReferDto : refers) {
				if (StringUtils.isEmpty(errReferDto.getSourceDesc())) {
					throw AssoException.NO_REFER_SOURCEDESC;
				}
				TtErrRefer errRefer = new TtErrRefer();
				BeanUtils.copyProperties(errReferDto, errRefer);
				errRefer.setErrId(errColl.getId());
				if (null == errRefer.getId()) {// 执行插入
					errRefer.setCreateTime(new Date());
					referMapper.insert(errRefer);
				} else {// 执行更新
					errRefer.setUpdateTime(new Date());
					referMapper.updateByPrimaryKeySelective(errRefer);
				}
			}
		}
	}

	public ResponsePageResult errlist(ErrListQueryDto queryDto) {

		//分页查询在执行查询之前会执行一下SELECT count(0) FROM table
		PageHelper.startPage(queryDto.getPage(), queryDto.getRows());

		// 处理模糊查询
		queryDto.setErrCauseBy(MyStringUtils.dealLikeStr(queryDto.getErrCauseBy()));
		queryDto.setErrDescription(MyStringUtils.dealLikeStr(queryDto.getErrDescription()));
		queryDto.setErrSolution(MyStringUtils.dealLikeStr(queryDto.getErrSolution()));

		List<ErrCollectionDto> list = errMapper.errList(queryDto);

		PageInfo<ErrCollectionDto> page = new PageInfo<>(list);

		return new ResponsePageResult(page.getList(), page.getTotal());
	}

	public ErrCollectionDetailDto detail(Long errCollId) {
		TtErrCollection errCollection = errMapper.selectByPrimaryKey(errCollId);
		ErrCollectionDetailDto detailDto = null;
		
		if (null != errCollection) {
			detailDto = new ErrCollectionDetailDto();
			BeanUtils.copyProperties(errCollection, detailDto);
			//查询参考数据
			TtErrRefer refRecord = new TtErrRefer();
			refRecord.setErrId(errCollId);
			List<TtErrRefer> refers = referMapper.select(refRecord );
			detailDto.setRefers(refers);
		}
		return detailDto;
	}

	public void errDel(long errCollId) {
		errMapper.errDel(errCollId);
	}

}
