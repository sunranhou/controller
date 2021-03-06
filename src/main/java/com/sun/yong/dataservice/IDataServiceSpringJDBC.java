package com.sun.yong.dataservice;

import java.util.List;

import com.sun.yong.common.entity.model.Friend;
import com.sun.yong.common.entity.model.Message;
import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;

public interface IDataServiceSpringJDBC {

	UserInfo getUserInfoByUserName(String username) throws DateServiceException;
	
	UserInfo getUserInfoByMemberId(String memberId) throws DateServiceException;
	
	List<Friend> getFriendByMemberId(String memberId) throws DateServiceException;
	
	UserInfo getUser(String memberId) throws DateServiceException;
	
	void insertUser(UserInfo userInfo) throws DateServiceException;
	
	void insertMessage(List<Message> messageList) throws DateServiceException;
	
	void isSendMessage(List<Message> messageList) throws DateServiceException;
}
