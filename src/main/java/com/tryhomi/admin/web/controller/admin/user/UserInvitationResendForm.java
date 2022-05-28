

package com.tryhomi.admin.web.controller.admin.user;

import com.tryhomi.admin.model.UserInvitationResendRequest;

import java.io.Serializable;

public class UserInvitationResendForm implements Serializable {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserInvitationResendRequest buildUserInvitationResendRequest() {
		UserInvitationResendRequest.Builder builder = new UserInvitationResendRequest.Builder();
		return builder
				.token(token)
				.build();
	}
}
