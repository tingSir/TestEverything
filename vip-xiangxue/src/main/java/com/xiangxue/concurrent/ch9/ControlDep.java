package com.xiangxue.concurrent.ch9;

public class ControlDep {
	int a = 0;
	boolean flag = false;

	public void init() {
		a = 1; // 1
		flag = true; // 2
		//.......
	}

	public void use() {
		if (flag) { // 3
			int i = a * a; // 4
		}
		//.......
	}
}
