package com.xiangxue.jvm.ch03.deencrpt;

public class DemoRun {

    public static void main(String[] args) throws Exception {
        CustomClassLoader demoCustomClassLoader
        	= new CustomClassLoader("My ClassLoader");
        demoCustomClassLoader.setBasePath("A:\\workspaceYL\\TestEverything\\vip-xiangxue\\target\\classes\\");
        Class<?> clazz =demoCustomClassLoader.findClass("com.xiangxue.jvm.ch03.deencrpt.DemoUser");
//        Class<?> clazz =demoCustomClassLoader.findClass("DemoUser");
        System.out.println(clazz.getClassLoader());
        Object o = clazz.newInstance();
        System.out.println(o.equals(new DemoUser()));
        System.out.println(clazz.equals(DemoUser.class));
        //new User(xxx,yyyy,ddd);//
        Class.forName("com.xiangxue.jvm.ch03.deencrpt.DemoUser",false,demoCustomClassLoader);
        DemoUser.class.getClassLoader();
    }
}
