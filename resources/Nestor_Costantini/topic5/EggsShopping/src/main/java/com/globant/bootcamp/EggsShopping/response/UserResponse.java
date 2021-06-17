package com.globant.bootcamp.EggsShopping.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

    Long id;

	private String username;
	
	private String nickname;
	
	private String password;
	
	private Boolean enabled;

}
