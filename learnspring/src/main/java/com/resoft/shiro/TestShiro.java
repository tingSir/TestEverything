package com.resoft.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Assert;

public class TestShiro {
	@Test
	public void testHelloworld() {
	    //1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager  
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
	    //2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils   
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	    //3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	    try {
	        //4����¼���������֤
	        subject.login(token);
	    } catch (AuthenticationException e) {
	        //5�������֤ʧ��
	    }
	    Assert.assertEquals(true, subject.isAuthenticated()); //�����û��Ѿ���¼
	    //6���˳�
	    subject.logout();
	}
	
	
	private static final transient Logger log = LoggerFactory.getLogger(TestShiro.class);
	@Test
    public void  mainTestShiro() {
        log.info("My First Apache Shiro Application");

        //1. �����SecurityManager��org.apache.shiro.mgt.SecurityManager��������java.lang.SecurityManager
        // ���������ļ�
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.���������ļ������ҷ���һЩSecurityMangerʵ��
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

        //3.����SecurityManager����̬�ڴ���������ģʽ
        SecurityUtils.setSecurityManager(securityManager);


        // ��ȫ����
        Subject currentUser = SecurityUtils.getSubject();

        // ��Ӧ�õĵ�ǰ�Ự����������
        Session session =  currentUser.getSession();
        session.setAttribute("key","value");

        //��ǰ���ǵ��û����������û������ǳ��Խ��е�¼��
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("aihe", "aihe");

            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);

            //���Խ��е�¼�û��������¼ʧ���ˣ����ǽ���һЩ����

            try{
                currentUser.login(token);

                //�����ǻ��¼�û�֮��
                log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");


                // �鿴�û��Ƿ���ָ���Ľ�ɫ
                if ( currentUser.hasRole( "client" ) ) {
                    log.info("Look is in your role" );
                } else {
                    log.info( "....." );
                }

                // �鿴�û��Ƿ���ĳ��Ȩ��
                if ( currentUser.isPermitted( "look:desk" ) ) {
                    log.info("You can look.  Use it wisely.");
                } else {
                    log.info("Sorry, you can't look.");
                }

                if ( currentUser.isPermitted( "winnebago:drive:eagle5" ) ) {
                    log.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
                            "Here are the keys - have fun!");
                } else {
                    log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
                }

                //�ǳ�

                currentUser.logout();

            }
            catch ( UnknownAccountException uae ) {
                //�˻������ڵĲ���
            } catch ( IncorrectCredentialsException ice ) {
                //���벻��ȷ
            } catch ( LockedAccountException lae ) {
                //�û���������
            } catch ( AuthenticationException ae ) {
                //�޷��жϵ�����
            }

        }


        System.exit(0);
    }

}
