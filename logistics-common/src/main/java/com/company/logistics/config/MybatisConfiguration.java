package com.company.logistics.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wzj
 * @date 2021/4/18 15:26
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfiguration implements EnvironmentAware {
    private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);
    /**
     * 事务超时时间
     */
    private static final int TX_METHOD_TIMEOUT = 5;
    /**
     * AOP 事务拦截service
     */
    private static final String AOP_POINTCUT_EXPRESSION =
            "execution (* com.company.*..service.*.*(..))";
    /**
     * mysql driveClassName
     */
    private String driveClassName;
    /**
     * 数据库连接
     */
    private String url;
    /**
     * 数据库用户
     */
    private String userName;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * mapper location
     */
    private String xmlLocation;
    /**
     * typeAliasesPackage
     */
    private String typeAliasesPackage;
    /**
     * typeHandlersPackage
     */
    private String typeHandlersPackage;

    /**
     * 最大连接池数量
     */
    private String maxActive;
    /**
     * 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
     */
    private String initialSize;
    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，
     * 并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    private String maxWait;
    /**
     * 最小连接池数量
     */
    private String minIdle;
    /**
     * 有两个含义：
     * 1) Destroy线程会检测连接的间隔时间
     * 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private String timeBetweenEvictionRunsMillis;
    /**
     *
     */
    private String minEvictableIdleTimeMillis;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句。
     * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
     */
    private String validationQuery;
    /**
     * 建议配置为true，不影响性能，并且保证安全性。
     * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     */
    private String testWhileIdle;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private String testOnBorrow;
    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
     */
    private String testOnReturn;
    /**
     * 是否缓存preparedStatement，也就是PSCache。
     * PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
     */
    private String poolPreparedStatements;
    /**
     * 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
     * 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
     */
    private String maxOpenPreparedStatements;


    /**
     * 配置 DataSource
     *
     * @return
     */
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(StringUtils.isNotBlank(driveClassName) ? driveClassName : "com.mysql.jdbc.Driver");
        druidDataSource.setMaxActive(StringUtils.isNotBlank(maxActive) ? Integer.parseInt(maxActive) : 10);
        druidDataSource.setInitialSize(StringUtils.isNotBlank(initialSize) ? Integer.parseInt(initialSize) : 1);
        druidDataSource.setMaxWait(StringUtils.isNotBlank(maxWait) ? Integer.parseInt(maxWait) : 60000);
        druidDataSource.setMinIdle(StringUtils.isNotBlank(minIdle) ? Integer.parseInt(minIdle) : 3);
        druidDataSource.setTimeBetweenEvictionRunsMillis(StringUtils.isNotBlank(timeBetweenEvictionRunsMillis) ?
                Integer.parseInt(timeBetweenEvictionRunsMillis) : 60000);
        druidDataSource.setMinEvictableIdleTimeMillis(StringUtils.isNotBlank(minEvictableIdleTimeMillis) ?
                Integer.parseInt(minEvictableIdleTimeMillis) : 300000);
        druidDataSource.setValidationQuery(StringUtils.isNotBlank(validationQuery) ? validationQuery : "select 'x'");
        druidDataSource.setTestWhileIdle(StringUtils.isNotBlank(testWhileIdle) ? Boolean.parseBoolean(testWhileIdle) : true);
        druidDataSource.setTestOnBorrow(StringUtils.isNotBlank(testOnBorrow) ? Boolean.parseBoolean(testOnBorrow) : false);
        druidDataSource.setTestOnReturn(StringUtils.isNotBlank(testOnReturn) ? Boolean.parseBoolean(testOnReturn) : false);
        druidDataSource.setPoolPreparedStatements(StringUtils.isNotBlank(poolPreparedStatements) ? Boolean.parseBoolean(poolPreparedStatements) : true);
        druidDataSource.setMaxOpenPreparedStatements(StringUtils.isNotBlank(maxOpenPreparedStatements) ? Integer.parseInt(maxOpenPreparedStatements) : 20);

        return druidDataSource;
    }

    /**
     * SqlSessionFactory
     *
     * @param dataSource
     * @return
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        if (StringUtils.isNotBlank(typeAliasesPackage)) {
            bean.setTypeAliasesPackage(typeAliasesPackage);
        }
        if (StringUtils.isNotEmpty(typeHandlersPackage)) {
            bean.setTypeHandlersPackage(typeHandlersPackage);
        }
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = null;

        Interceptor[] plugins = new Interceptor[]{pageHelper};
        bean.setPlugins(plugins);
        try {
            mapperLocations = resolver.getResources(xmlLocation);
            bean.setMapperLocations(mapperLocations);
//            try {
//                new MapperRefresh(mapperLocations, bean.getObject().getConfiguration()).run();
//            } catch (Exception e) {
//                logger.info("Mapper热部署启动失败");
//                e.printStackTrace();
//            }
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 事务配置
     *
     * @param transactionManager
     * @return
     */
    @Bean
    public TransactionInterceptor transactionAdvice(DataSourceTransactionManager transactionManager) {
        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("fetch*", readOnlyTx);

        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        source.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * 事务 AOP 切面
     *
     * @param transactionAdvice
     * @return
     */
//    @Bean
//    public Advisor txAdviceAdvisor(TransactionInterceptor transactionAdvice) {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//        return new DefaultPointcutAdvisor(pointcut, transactionAdvice);
//    }

    /**
     * 获取配置properties
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.url = environment.getProperty("spring.datasource.url");
        this.userName = environment.getProperty("spring.datasource.username");
        this.password = environment.getProperty("spring.datasource.password");
        this.driveClassName = environment.getProperty("spring.datasource.driver-class-name");
        this.maxActive = environment.getProperty("spring.datasource.maxActive");
        this.initialSize = environment.getProperty("spring.datasource.initialSize");
        this.maxWait = environment.getProperty("spring.datasource.maxWait");
        this.minIdle = environment.getProperty("spring.datasource.minIdle");
        this.timeBetweenEvictionRunsMillis = environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis");
        this.minEvictableIdleTimeMillis = environment.getProperty("spring.datasource.minEvictableIdleTimeMillis");
        this.validationQuery = environment.getProperty("spring.datasource.validationQuery");
        this.testWhileIdle = environment.getProperty("spring.datasource.testWhileIdle");
        this.testOnBorrow = environment.getProperty("spring.datasource.testOnBorrow");
        this.testOnReturn = environment.getProperty("spring.datasource.testOnReturn");
        this.poolPreparedStatements = environment.getProperty("spring.datasource.poolPreparedStatements");
        this.maxOpenPreparedStatements = environment.getProperty("spring.datasource.maxOpenPreparedStatements");
        this.typeAliasesPackage = environment.getProperty("mybatis-plus.type-aliases-package");
        this.xmlLocation = environment.getProperty("mybatis-plus.xmlLocation");
        this.typeHandlersPackage = environment.getProperty("mybatis-plus.type-handlers-package");
    }
}
