package org.maochen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring.xml");

        Account account = (Account) appContext.getBean("account");

        account.print();

        System.out.println(account.getName());

        //        account.throwException();
    }
}