package com.Star.dubbo.Impl;

import java.util.List;

import javax.annotation.Resource;

import com.Star.mapper.TbUserMapper;
import com.Star.pojo.TbUser;
import com.Star.pojo.TbUserExample;
import com.star.dubbo.service.TbUserDubboService;

public class TbUserDubboServiceImpl implements TbUserDubboService {

	@Resource
	private TbUserMapper tbUserMapper;
	
	
	@Override
	public TbUser selbynp(TbUser user) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> selectByExample = tbUserMapper.selectByExample(example);
		
		if (selectByExample!=null&&selectByExample.size()>0) {
			
			return selectByExample.get(0);
			
			
		}
		return null;
	}


	@Override
	public boolean checkbyName(String username) {

		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		 List<TbUser> selectByExample = tbUserMapper.selectByExample(example);
		 if(selectByExample!=null&&selectByExample.size()>0) {
			 return true;
		 }
		return false;
	}


	@Override
	public boolean checkbyphone(String phone) {
	
		TbUserExample example = new TbUserExample();
		example.createCriteria().andPhoneEqualTo(phone);
		 List<TbUser> selectByExample = tbUserMapper.selectByExample(example);
		 if(selectByExample!=null&&selectByExample.size()>0) {
			 return true;
		 }
		return false;
	}


	@Override
	public int insertUser(TbUser user) {
		return tbUserMapper.insertSelective(user);
		
	}

}
