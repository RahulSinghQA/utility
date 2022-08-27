package org.evs.Vtiger.loginpage;

import org.evs.Vtiger.Util.Util;

public class Loginpage {
	public Util utl;
	public Loginpage(Util utlobj) {
		utl=utlobj;
	}
	
	public void validlogin() {
		utl.sendkey("user_name","name","admin");
		utl.sendkey("//input[@name='user_password']", "xpath", "admin");
		//utl.sendkey("//select[@name=\"login_theme\"]", "locater" );
		utl.click("//input[@name='Login']","xpath");
	
	}

}
