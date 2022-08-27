package org.evs.Vtiger.Myhomepage;

import org.evs.Vtiger.Util.Util;

public class homelandingpage {
	 
		public Util utl;
		public homelandingpage(Util utlobj) {
			utl=utlobj;
		}
	
		
		public void navigateToMyhome() {
			
			utl.mouseHover("//a[text()='My Home Page']", "xpath");
			
		}
		public void navigateToHome() {
			utl.mouseHover("Home", "linkText");
			utl.click("Home", "linkText");
		}
		public void navigateToHomeCalendar() {
			utl.mouseHover("Calendar", "linkText");
			utl.click("Calendar", "linkText");
		}
		public void navigateToMarketing() {
			utl.mouseHover("Marketing", "linkText");
		}
		public void navigateToMarketingCampaigns() {
			utl.mouseHover("Marketing","linkText" );
			utl.click("Campaigns", "linkText");
		}
		public void navigateToMarketingAccounts() {
			utl.mouseHover("Accounts", "linkText");
			utl.click("Accounts", "linkText");
		}
		public void navigatehometosales() {
			utl.mouseHover("//a[text()='Sales']", "xpath");
			
		}
		public void navigatehometosupports() {
			utl.mouseHover("//a[text()='Support']", "xpath");
		}
		public void navigatehometoAnalytics() {
			utl.mouseHover("//a[text()='Analytics']", "xpath");
			
		}
		public void navigatehometoInventry() {
			utl.mouseHover("//a[text()='Inventory']", "xpath");
		}
		public void navigatehometoTools() {
			utl.mouseHover("//a[text()='Tools']", "xpath");
		}
		public void navigatehometoSettings() {
			utl.mouseHover("//a[text()='Settings']", "xpath");
		}

	}


