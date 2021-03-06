package com.sun.yong.dataservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.sun.yong.common.entity.model.Friend;
import com.sun.yong.common.entity.model.Message;
import com.sun.yong.common.entity.model.UserInfo;
import com.sun.yong.common.exception.DateServiceException;
import com.sun.yong.dataservice.IDataServiceSpringJDBC;
import com.sun.yong.dataservice.SQLConstant;
import com.sun.yong.dataservice.mapper.FriendRowMapper;
import com.sun.yong.dataservice.mapper.UserInfoRowMapper;

public class DataServiceSpringJDBCImpl implements IDataServiceSpringJDBC {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String getDateBaseLineID() {
		return UUID.randomUUID().toString();
	}
	
	public UserInfo getUserInfoByUserName(String username) throws DateServiceException {
		UserInfo userInfo = null;
		List<UserInfo> userInfoList = 
				jdbcTemplate.query(SQLConstant.SQL_GET_USERINFO_BY_USERNAME, 
						new Object[]{username}, new UserInfoRowMapper());
		if (!CollectionUtils.isEmpty(userInfoList)) {
			userInfo = userInfoList.get(0);
		}
		return userInfo;
	}
	
	@Override
	public UserInfo getUserInfoByMemberId(String memberId) throws DateServiceException {
		UserInfo userInfo = null;
		List<UserInfo> userInfoList = 
				jdbcTemplate.query(SQLConstant.SQL_GET_USERINFO_BY_MEMBERID, 
						new Object[]{memberId}, new UserInfoRowMapper());
		if (!CollectionUtils.isEmpty(userInfoList)) {
			userInfo = userInfoList.get(0);
		}
		return userInfo;
	}

	@Override
	public List<Friend> getFriendByMemberId(String memberId) throws DateServiceException {
		List<Friend> friendList = 
				jdbcTemplate.query(SQLConstant.SQL_GET_FRIEND_BY_MEMBERID, 
						new Object[]{memberId}, new FriendRowMapper());
		if (!CollectionUtils.isEmpty(friendList)) {
			
		}
		return friendList;
	}
	@Override
	public void insertUser(UserInfo userInfo) throws DateServiceException {
		Object[] o = new Object[] {
		};
		int n = jdbcTemplate.update(SQLConstant.SQL_INSERT_MESSAGE, o);
		if (n <= 0) {
			throw new DateServiceException("n <= 0", "insert fail");
		}
	}
	
	@Override
	public void insertMessage(List<Message> messageList) throws DateServiceException {
		List<Object[]> oList = new ArrayList<Object[]>();
		Object[] o = null;
		for (Message message: messageList) {
			o = new Object[] {getDateBaseLineID(),
					message.getFromID(), message.getToID(), message.getContent(), 
					message.isSend(),    message.getDateTime()
			};
			oList.add(o);
		}
		
		int[] n = jdbcTemplate.batchUpdate(SQLConstant.SQL_INSERT_MESSAGE, oList);
		if (null == n || n.length <= 0) {
			throw new DateServiceException("n <= 0", "insert fail");
		}
	}

	@Override
	public void isSendMessage(List<Message> messageList) throws DateServiceException {
		List<Object[]> oList = new ArrayList<Object[]>();
		Object[] o = null;
		for (Message message: messageList) {
			o = new Object[] {getDateBaseLineID(),
					message.getFromID(), message.getToID(), message.getContent(), 
					message.isSend(),    message.getDateTime()
			};
			oList.add(o);
		}
		int[] n = jdbcTemplate.batchUpdate(SQLConstant.SQL_IS_SEND_MESSAGE, oList);
		if (null == n || n.length <= 0) {
			throw new DateServiceException("n <= 0", "update fail");
		}
	}

	@Override
	public UserInfo getUser(String memberId) throws DateServiceException {
		UserInfo userInfo = null;
		List<UserInfo> userInfoList = 
				jdbcTemplate.query(SQLConstant.SQL_GET_USERINFO_BY_USERNAME, 
						new Object[]{memberId}, new UserInfoRowMapper());
		if (!CollectionUtils.isEmpty(userInfoList)) {
			userInfo = userInfoList.get(0);
		}
		return userInfo;
	}

}
