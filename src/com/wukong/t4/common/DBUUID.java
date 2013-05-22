package com.wukong.t4.common;


public class DBUUID {
	  public static String getID() {
	    UUIDHexGenerator uuidHex = new UUIDHexGenerator();
	    return uuidHex.generate().toString();
	  }

	  public static void main(String[] args) {
//	    for (int i = 0; i < 107; i++) {
	      System.out.println(getID());
//	    }
	  }
	}
