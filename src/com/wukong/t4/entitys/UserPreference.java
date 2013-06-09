package com.wukong.t4.entitys;

public class UserPreference {
	private boolean iPhone_3G;
	private boolean iPhone_2G;
	private boolean android_3G;
	private boolean android_2G;
	private boolean 活跃_3G;
	private boolean 活跃_2G;
	private boolean 非活跃_3G;
	private boolean 非活跃_2G;
	private boolean 沉默_3G;
	private boolean 沉默_2G;

	public UserPreference() {
		super();
	}

	public UserPreference(boolean iPhone_3G, boolean iPhone_2G,
			boolean android_3g, boolean android_2g, boolean 活跃_3g,
			boolean 活跃_2g, boolean 非活跃_3g, boolean 非活跃_2g, boolean 沉默_3g,
			boolean 沉默_2g) {
		super();
		this.iPhone_3G = iPhone_3G;
		this.iPhone_2G = iPhone_2G;
		android_3G = android_3g;
		android_2G = android_2g;
		活跃_3G = 活跃_3g;
		活跃_2G = 活跃_2g;
		非活跃_3G = 非活跃_3g;
		非活跃_2G = 非活跃_2g;
		沉默_3G = 沉默_3g;
		沉默_2G = 沉默_2g;
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

	public boolean is活跃_3G() {
		return 活跃_3G;
	}

	public void set活跃_3G(boolean 活跃_3g) {
		活跃_3G = 活跃_3g;
	}

	public boolean is活跃_2G() {
		return 活跃_2G;
	}

	public void set活跃_2G(boolean 活跃_2g) {
		活跃_2G = 活跃_2g;
	}

	public boolean is非活跃_3G() {
		return 非活跃_3G;
	}

	public void set非活跃_3G(boolean 非活跃_3g) {
		非活跃_3G = 非活跃_3g;
	}

	public boolean is非活跃_2G() {
		return 非活跃_2G;
	}

	public void set非活跃_2G(boolean 非活跃_2g) {
		非活跃_2G = 非活跃_2g;
	}

	public boolean is沉默_3G() {
		return 沉默_3G;
	}

	public void set沉默_3G(boolean 沉默_3g) {
		沉默_3G = 沉默_3g;
	}

	public boolean is沉默_2G() {
		return 沉默_2G;
	}

	public void set沉默_2G(boolean 沉默_2g) {
		沉默_2G = 沉默_2g;
	}

}
