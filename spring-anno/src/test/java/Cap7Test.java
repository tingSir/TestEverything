import com.enjoy.cap7.bean.Person;
import com.enjoy.cap7.config.Cap7MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap7Test {
	@Test
	public void test01(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap7MainConfigOfLifeCycle.class);
		System.out.println("IOC容器创建完成........");
		app.close();
	}

	@Test
	public void test02(){
		try {
			AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap7MainConfigOfLifeCycle.class);
            Person person = (Person)app.getBean("person");
            person.async();
            System.out.println("over");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
