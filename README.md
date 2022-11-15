## starter-spring-webapp


### Create the schema
```
CREATE USER sample_db IDENTIFIED BY sample_db;
GRANT CONNECT TO sample_db;
GRANT CONNECT, RESOURCE, DBA TO sample_db;
GRANT CREATE SESSION TO sample_db;
GRANT CREATE TABLE TO sample_db;
GRANT CREATE VIEW TO sample_db;
GRANT CREATE ANY TRIGGER TO sample_db;
GRANT CREATE ANY PROCEDURE TO sample_db;
GRANT CREATE SEQUENCE TO sample_db;
GRANT CREATE SYNONYM TO sample_db;
GRANT UNLIMITED TABLESPACE TO sample_db;
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