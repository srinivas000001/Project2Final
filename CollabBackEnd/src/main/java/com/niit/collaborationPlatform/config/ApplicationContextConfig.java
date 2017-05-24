package com.niit.collaborationPlatform.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationPlatform.model.Blog;
import com.niit.collaborationPlatform.model.Chat;
import com.niit.collaborationPlatform.model.Event;
import com.niit.collaborationPlatform.model.Forum;
import com.niit.collaborationPlatform.model.Friend;
import com.niit.collaborationPlatform.model.Job;
import com.niit.collaborationPlatform.model.JobApplication;
import com.niit.collaborationPlatform.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Autowired
	@Bean(name = "dataSource")
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("srinu");
		dataSource.setPassword("srinu");
		return dataSource;
	}

	private Properties getHibernateProperties()

	{
		Properties properties = new Properties();
		
		properties.put("hibernate.show_sql", "true");
				
		properties.put("hibernate.format_sql", "true");
				
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
				
		properties.put("hibernate.hbm2ddl.auto", "update");
				
		return properties;
			
}

	@Autowired
	@Bean(name="sessionfactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder =new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		
		
        return sessionBuilder.buildSessionFactory();
		
	}
	@Autowired
	@Bean("name=transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}

	/*@Autowired
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}*/
	