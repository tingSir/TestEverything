package com.xiangxue.concurrent.ch3;

import java.util.concurrent.atomic.AtomicReference;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *类说明：演示引用类型的原子操作类
 */
public class UseAtomicReference {

	static AtomicReference<UserInfo> userRef = new AtomicReference<UserInfo>();

    public static void main(String[] args) {
        UserInfo user = new UserInfo("Mark", 15);//要修改的实体的实例
        userRef.set(user);

        UserInfo updateUser = new UserInfo("Bill", 17);//要变化的新实例
        userRef.set(updateUser);
        userRef.set(user);

        //如果userRef里面值等于user则复制给updateUser
        boolean b = userRef.compareAndSet(user, updateUser);
        System.out.println(b);
        System.out.println(userRef);
        System.out.println(user);
    }

    //定义一个实体类
    static class UserInfo {
        private String name;
        private int age;
        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
