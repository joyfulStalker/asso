package cn.songlin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.songlin.common.dto.LocalUser;
import cn.songlin.dto.user.UserLoginDto;
import cn.songlin.entity.UserAccount;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserAccountMapper extends BaseMapper<UserAccount> {

	/**
	 * 检查用户名是否存在
	 * 
	 * @author liusonglin
	 * @date 2018年7月30日
	 * @return
	 */

	int checkUserName(@Param("name") String name);

	/**
	 * 用户登录 支持昵称，用户名，电话登录
	 * 
	 * @author liusonglin
	 * @date 2018年7月31日
	 * @param userLoginDto
	 * @return
	 */

	LocalUser login(@Param("userLoginDto") UserLoginDto userLoginDto);

	
	/**
	* 根据搜索词进行查询
	* @author liusonglin
	* @date 2018年11月20日
	* @param searchKey
	* @return
	*/
	List<UserAccount> queryList(@Param("searchKey") String searchKey);
}