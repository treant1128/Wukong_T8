package com.wukong.t4.entitys;

public class UserPreference {
	private boolean iPhone_3G;
	private boolean iPhone_2G;
	private boolean android_3G;
	private boolean android_2G;
	private boolean active_3G;
	private boolean active_2G;
	private boolean inactive_3G;
	private boolean inactive_2G;
	private boolean silent_3G;
	private boolean silent_2G;

	public UserPreference() {
		super();
	}

	public UserPreference(boolean iPhone_3G, boolean iPhone_2G,
			boolean android_3g, boolean android_2g, boolean active_3g,
			boolean active_2g, boolean inactive_3g, boolean inactive_2g,
			boolean silent_3g, boolean silent_2g) {
		super();
		this.iPhone_3G = iPhone_3G;
		this.iPhone_2G = iPhone_2G;
		android_3G = android_3g;
		android_2G = android_2g;
		active_3G = active_3g;
		active_2G = active_2g;
		inactive_3G = inactive_3g;
		inactive_2G = inactive_2g;
		silent_3G = silent_3g;
		silent_2G = silent_2g;
	}

	public boolean isiPhone_3G() {
		return iPhone_3G;
	}

	public void setiPhone_3G(boolean iPhone_3G) {
		this.iPhone_3G = iPhone_3G;
	}

	public boolean isiPhone_2G() {
		return iPhone_2G;
	}

	public void setiPhone_2G(boolean iPhone_2G) {
		this.iPhone_2G = iPhone_2G;
	}

	public boolean isAndroid_3G() {
		return android_3G;
	}

	public void setAndroid_3G(boolean android_3g) {
		android_3G = android_3g;
	}

	public boolean isAndroid_2G() {
		return android_2G;
	}

	public void setAndroid_2G(boolean android_2g) {
		android_2G = android_2g;
	}

	public boolean isActive_3G() {
		return active_3G;
	}

	public void setActive_3G(boolean active_3g) {
		active_3G = active_3g;
	}

	public boolean isActive_2G() {
		return active_2G;
	}

	public void setActive_2G(boolean active_2g) {
		active_2G = active_2g;
	}

	public boolean isInactive_3G() {
		return inactive_3G;
	}

	public void setInactive_3G(boolean inactive_3g) {
		inactive_3G = inactive_3g;
	}

	public boolean isInactive_2G() {
		return inactive_2G;
	}

	public void setInactive_2G(boolean inactive_2g) {
		inactive_2G = inactive_2g;
	}

	public boolean isSilent_3G() {
		return silent_3G;
	}

	public void setSilent_3G(boolean silent_3g) {
		silent_3G = silent_3g;
	}

	public boolean isSilent_2G() {
		return silent_2G;
	}

	public void setSilent_2G(boolean silent_2g) {
		silent_2G = silent_2g;
	}

}
