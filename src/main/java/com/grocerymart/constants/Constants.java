package com.grocerymart.constants;

public class Constants {
	
	public enum Role {
		ADMIN("ADMIN"),
		USER("USER");
		
		public String value;
		
		private Role(String value) {
			this.value=value;
		}
		
		public String getValue() {
			return this.value;
		}

	}

}
