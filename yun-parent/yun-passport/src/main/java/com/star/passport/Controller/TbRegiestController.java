package com.star.passport.Controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Star.pojo.TbUser;
import com.star.commons.pojo.EgoResult;
import com.star.commons.utils.PhoneFormatCheckUtils;

import com.star.passport.service.TbRegisterService;
import com.star.passport.service.TbUserService;

@RestController
public class TbRegiestController {

	@Resource
	private TbUserService tbUserServiceImpl;
	@Resource
	private TbRegisterService tbRegisterServiceImpl;

	/**
	 * 效验用户名
	 * 
	 * @param regName
	 * @return
	 */
	@RequestMapping("user/check")

	public EgoResult checkName(String username) {
		EgoResult ego = null;
		try {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
			ego = tbUserServiceImpl.check(username);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ego;

	}

	/**
	 * 效验手机号
	 * 
	 * @param number
	 * @return
	 */
	@RequestMapping("user/checknum/{number}/2")

	public EgoResult checkPhone(@PathVariable String number) {

		EgoResult egoResult = tbUserServiceImpl.checkbyphone(number);

		return egoResult;

	}

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping("user/sendCode.do")

	public EgoResult sendSMS(String phone) {
		EgoResult egoResult = new EgoResult();

		if (PhoneFormatCheckUtils.isPhoneLegal(phone)) {
			egoResult.setMsg("手机号格式不正确");
		}
		try {
			tbRegisterServiceImpl.createSmsCode(phone);
			egoResult.setStatus(200);

		} catch (Exception e) {
			egoResult.setMsg("手机dasongshibai");
			e.printStackTrace();
		}
		return egoResult;

	}

	/**
	 * username:123 password:100 pwdRepeat:100 phone:74107410 yan:74
	 * 添加新用户
	 * @return
	 */
	@RequestMapping("/user/register")
	public EgoResult adduser(TbUser tbuser, String yan) {
		EgoResult ego = new EgoResult();
		//检验验证码是否一致
		boolean code = tbRegisterServiceImpl.checkSmsCode(tbuser.getPhone(), yan);

		if (!code) {
			ego.setMsg("验证码错误");
			return ego;
		}
		int inseradd = tbRegisterServiceImpl.inseradd(tbuser);
		if (inseradd > 0) {
			ego.setStatus(200);
			return ego;
		} else {
			ego.setMsg("注册失败");
			return ego;
		}

	}
	@RequestMapping("/user/anjs")
	public EgoResult add() {
		EgoResult ego = new EgoResult();
		String msg="{\"name\":\"甄兆星\",\"age\":17}";
		ego.setMsg(msg);
		return ego;
		

	}
}
