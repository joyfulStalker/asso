package cn.songlin.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.songlin.entity.TtErrCollection;
import cn.songlin.mapper.TtErrCollectionMapper;

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

}
