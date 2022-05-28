

package com.tryhomi.admin.web.controller.admin.user;



import com.tryhomi.admin.model.UserDeleteRequest;

import javax.validation.constraints.NotNull;

public class UserDeleteForm {

	@NotNull
	private Long id;

	private boolean confirmed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public UserDeleteRequest buildUserDeleteRequest() {
		UserDeleteRequest.Builder builder = new UserDeleteRequest.Builder();
		return builder
				.id(id)
				.build();
	}
}
