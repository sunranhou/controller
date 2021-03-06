package com.sun.yong.business.provider.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.yong.business.provider.IAuthorityProvider;
import com.sun.yong.common.entity.common.ErrorInfo;
import com.sun.yong.common.entity.common.LogFlag;
import com.sun.yong.common.entity.model.Friend;
import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.entity.request.LoginRequest;
import com.sun.yong.common.entity.response.FriendResponse;
import com.sun.yong.common.entity.response.LoginResponse;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;

public class AuthorityProviderImpl implements IAuthorityProvider {

	private IDataServiceSpringJDBC dataService;
	
	public void setDataService(IDataServiceSpringJDBC dataService) {
		this.dataService = dataService;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest, LogFlag logFlag) {
		LoginResponse loginResponse = new LoginResponse();
		try {
			UserInfo userInfo = dataService.getUserInfoByUserName(loginRequest.getUsername());
			if (userInfo == null || !userInfo.getUsername().equals(loginRequest.getUsername())) {
				loginResponse.addError(new ErrorInfo("fail", "username"));
			} else if (!loginRequest.getPassword().equals(userInfo.getPassword())) {
				loginResponse.addError(new ErrorInfo("fail", "password"));
			} else {
				loginResponse.setMemberID(userInfo.getMemberID());
				loginResponse.setUsername(userInfo.getUsername());
				loginResponse.setSurname(userInfo.getSurname());
				loginResponse.setName(userInfo.getName());
				loginResponse.setBirthYear(userInfo.getBirthYear());
				loginResponse.setBirthMonth(userInfo.getBirthMonth());
				loginResponse.setBirthDay(userInfo.getBirthDay());
				loginResponse.setIdentity(null == userInfo.getIdentity() ? "" : userInfo.getIdentity().getValue());
			}
		} catch (Exception e) {
			loginResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return loginResponse;
	}
	
	@Override
	public LoginResponse enroll(LoginRequest loginRequest, LogFlag logFlag) {
		LoginResponse loginResponse = new LoginResponse();
		try {
			UserInfo userInfo = dataService.getUserInfoByUserName(loginRequest.getUsername());
			if (userInfo == null || !userInfo.getUsername().equals(loginRequest.getUsername())) {
				loginResponse.addError(new ErrorInfo("fail", "username"));
			} else if (!loginRequest.getPassword().equals(userInfo.getPassword())) {
				loginResponse.addError(new ErrorInfo("fail", "password"));
			} else {
				loginResponse.setMemberID(userInfo.getMemberID());
				loginResponse.setUsername(userInfo.getUsername());
				loginResponse.setSurname(userInfo.getSurname());
				loginResponse.setName(userInfo.getName());
				loginResponse.setBirthYear(userInfo.getBirthYear());
				loginResponse.setBirthMonth(userInfo.getBirthMonth());
				loginResponse.setBirthDay(userInfo.getBirthDay());
				loginResponse.setIdentity(null == userInfo.getIdentity() ? "" : userInfo.getIdentity().getValue());
			}
		} catch (Exception e) {
			loginResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return loginResponse;
	}

	@Override
	public FriendResponse getFriendList(String memberId, LogFlag logFlag) {
		FriendResponse friendResponse = new FriendResponse();
		try {
			List<Friend> friendList = dataService.getFriendByMemberId(memberId);
			if (null == friendList) {
				friendList = new ArrayList<Friend>();
			}
			friendResponse.setFriendList(friendList);
		} catch (Exception e) {
			friendResponse.addError(new ErrorInfo("system error","provider"));
			System.out.println(e.getMessage());
		}
		return friendResponse;
	}

}
