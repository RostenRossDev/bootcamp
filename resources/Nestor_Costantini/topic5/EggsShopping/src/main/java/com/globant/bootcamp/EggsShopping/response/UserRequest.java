package com.globant.bootcamp.EggsShopping.response;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	Long id;

	private String username;
	
	private String nickname;
	
	private String password;
	
	private Boolean enabled;
}
