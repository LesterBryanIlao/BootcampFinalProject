## starter-spring-webapp


### Create the schema
```
CREATE TABLE RedditUserDetails(
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    username VARCHAR(20) NOT NULL,
    email VARCHAR (20) NOT NULL,
    user_password VARCHAR (20) NOT NULL 
);

CREATE TABLE RedditUserPosts(
    post_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    post_content VARCHAR(500),
    date_posted DATE NOT NULL,
    up_votes INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES RedditUserDetails(user_id)
);

CREATE TABLE RedditPostComments(
    comment_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_content VARCHAR (500) NOT NULL,
    date_posted DATE NOT NULL,
    FOREIGN KEY (post_id) REFERENCES RedditUserPosts(post_id),
    FOREIGN KEY (user_id) REFERENCES RedditUserDetails(user_id)
);
```

### Update persistence.xml unit name
```
<persistence-unit name="change_this_unit_name">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
</persistence-unit>
```

### Update JpaConfiguration.java
```
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setPersistenceUnitName("change_this_unit_name");
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.bootcamp.webapp.web");
        factoryBean.setJpaProperties(hibernateProperties());
        return factoryBean;
    }
```

### Chrome Driver Download

https://chromedriver.chromium.org/downloads