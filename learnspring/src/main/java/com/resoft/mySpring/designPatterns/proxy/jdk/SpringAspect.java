package com.resoft.mySpring.designPatterns.proxy.jdk;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author longting
 *
 */
public class SpringAspect {
	public void  doBefore() {
		System.out.println("开始"+System.currentTimeMillis());
	}

	public void  doAfter() {
		System.out.println("完成"+System.currentTimeMillis());
	}
	public Object  doAround(ProceedingJoinPoint pdjp) {
		System.out.println("环绕开始"+System.currentTimeMillis());
		Object proceed=null;
		try {
			proceed= pdjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("环绕完成"+System.currentTimeMillis());
		return proceed;
	}
	public void  doAfterReturning() {
		System.out.println("doAfterReturning"+System.currentTimeMillis());
	}
	public void  doAfterThrowing(Throwable tb) {
		System.out.println("doAfterThrowing"+tb.getMessage());
	}
}
