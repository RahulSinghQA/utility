package org.evs.Vtiger.marketingCampaigns;

import org.evs.Vtiger.Util.Util;

public class CampaignsLandingPage {
	public Util utl;
	public CampaignsLandingPage(Util objutil) {
		utl=objutil;
	}
	
public void searchButton() {
	utl.sendkey("//input[@name='search_text']", "xpath", "CAM6");
	}
	
	
public void searchNow() {
	utl.click("//input[@value=' Search Now ']", "xpath");
}
public void quit() {
	utl.click("//input[@value='Delete']", "xpath");
	
}

}
	
	
	
	
	
	
	


