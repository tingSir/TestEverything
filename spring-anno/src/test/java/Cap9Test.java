import com.enjoy.cap9.config.Cap9MainConfig;
import com.enjoy.cap9.dao.TestDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap9Test {
	@Test
	public void test01(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap9MainConfig.class);
		//这是获取bean定义，bean还没有生成。
		String[] beanDefinitionNames = app.getBeanDefinitionNames();

		for (String str:beanDefinitionNames){
			System.out.println(str);
		}


		//TestService testService = app.getBean(TestService.class);
		//testService.println();
		//直接从容器中获取TestDao, 和使用Autowired注解来取比较
		TestDao testDao = app.getBean(TestDao.class);
//		TestDao testDao = (TestDao)app.getBean("testDao");
		System.out.println("--"+testDao);

		/*Moon moon = (Moon)app.getBean(Moon.class);
		System.out.println(moon);



		Sun sun = (Sun)app.getBean(Sun.class);
		System.out.println(sun.getMoon());*/
		app.close();





	}
}
