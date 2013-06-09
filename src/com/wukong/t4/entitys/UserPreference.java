package com.wukong.t4.entitys;

public class UserPreference {
	private boolean iPhone_3G;
	private boolean iPhone_2G;
	private boolean android_3G;
	private boolean android_2G;
	private boolean ��Ծ_3G;
	private boolean ��Ծ_2G;
	private boolean �ǻ�Ծ_3G;
	private boolean �ǻ�Ծ_2G;
	private boolean ��Ĭ_3G;
	private boolean ��Ĭ_2G;

	public UserPreference() {
		super();
	}

	public UserPreference(boolean iPhone_3G, boolean iPhone_2G,
			boolean android_3g, boolean android_2g, boolean ��Ծ_3g,
			boolean ��Ծ_2g, boolean �ǻ�Ծ_3g, boolean �ǻ�Ծ_2g, boolean ��Ĭ_3g,
			boolean ��Ĭ_2g) {
		super();
		this.iPhone_3G = iPhone_3G;
		this.iPhone_2G = iPhone_2G;
		android_3G = android_3g;
		android_2G = android_2g;
		��Ծ_3G = ��Ծ_3g;
		��Ծ_2G = ��Ծ_2g;
		�ǻ�Ծ_3G = �ǻ�Ծ_3g;
		�ǻ�Ծ_2G = �ǻ�Ծ_2g;
		��Ĭ_3G = ��Ĭ_3g;
		��Ĭ_2G = ��Ĭ_2g;
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

	public boolean is��Ծ_3G() {
		return ��Ծ_3G;
	}

	public void set��Ծ_3G(boolean ��Ծ_3g) {
		��Ծ_3G = ��Ծ_3g;
	}

	public boolean is��Ծ_2G() {
		return ��Ծ_2G;
	}

	public void set��Ծ_2G(boolean ��Ծ_2g) {
		��Ծ_2G = ��Ծ_2g;
	}

	public boolean is�ǻ�Ծ_3G() {
		return �ǻ�Ծ_3G;
	}

	public void set�ǻ�Ծ_3G(boolean �ǻ�Ծ_3g) {
		�ǻ�Ծ_3G = �ǻ�Ծ_3g;
	}

	public boolean is�ǻ�Ծ_2G() {
		return �ǻ�Ծ_2G;
	}

	public void set�ǻ�Ծ_2G(boolean �ǻ�Ծ_2g) {
		�ǻ�Ծ_2G = �ǻ�Ծ_2g;
	}

	public boolean is��Ĭ_3G() {
		return ��Ĭ_3G;
	}

	public void set��Ĭ_3G(boolean ��Ĭ_3g) {
		��Ĭ_3G = ��Ĭ_3g;
	}

	public boolean is��Ĭ_2G() {
		return ��Ĭ_2G;
	}

	public void set��Ĭ_2G(boolean ��Ĭ_2g) {
		��Ĭ_2G = ��Ĭ_2g;
	}

}
