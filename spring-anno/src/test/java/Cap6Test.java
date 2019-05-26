import com.enjoy.cap6.bean.Monkey;
import com.enjoy.cap6.config.Cap6MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*
主要讲SpringBean定义的n种方法
 */
public class Cap6Test {
	@Test
	public void test01(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap6MainConfig.class);

		System.out.println("IOC容器创建完成........");


		Monkey bean1 = (Monkey)app.getBean("jamesFactoryBean");
		Object bean2 = app.getBean("jamesFactoryBean");//取Money
		System.out.println("bean的类型="+bean1.getClass());
		System.out.println(bean1 == bean2);
		System.out.println(app.getBean("com.enjoy.cap6.bean.Dog"));

		Object bean3 = app.getBean("&jamesFactoryBean");//取Money

//		String[] beanDefinitionNames = app.getBeanDefinitionNames();
//		for(String name:beanDefinitionNames){
//			System.out.println(name);
//		}





	}
}
