

package com.tryhomi.admin.web.controller.admin.user;

import com.tryhomi.admin.model.UserInvitationDeleteRequest;

import javax.validation.constraints.NotNull;

public class UserInvitationDeleteForm {

	@NotNull
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserInvitationDeleteRequest buildUserInvitationDeleteRequest() {
		UserInvitationDeleteRequest.Builder builder = new UserInvitationDeleteRequest.Builder();
		return builder
				.token(token)
				.build();
	}
}
