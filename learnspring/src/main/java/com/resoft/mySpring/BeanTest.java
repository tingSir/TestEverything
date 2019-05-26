package com.resoft.mySpring;
public class BeanTest implements Test{
		public void  TestMethod(){
			System.out.println("private��һ��spring");
		}
		public void  ttt(){
			System.out.println("public��һ��spring");
		}
		protected void name() {
			System.out.println("protected��һ��spring");
		}
}
