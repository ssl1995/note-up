package com.ssl.know.java.pattern.建造者设计模式;

/**
 * MyBatis建造者模式示例
 * MyBatis中典型的建造者模式应用：SqlSessionFactoryBuilder、XMLConfigBuilder
 */
public class BuilderPattern {

    /**
     * 产品类：复杂的配置对象
     */
    static class Configuration {
        private String driver;
        private String url;
        private String username;
        private String password;
        private Integer maxActive;
        private Integer maxIdle;

        public void setDriver(String driver) { this.driver = driver; }
        public void setUrl(String url) { this.url = url; }
        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }
        public void setMaxActive(Integer maxActive) { this.maxActive = maxActive; }
        public void setMaxIdle(Integer maxIdle) { this.maxIdle = maxIdle; }

        @Override
        public String toString() {
            return "Configuration{driver='" + driver + "', url='" + url + "', username='" + username + 
                   "', maxActive=" + maxActive + ", maxIdle=" + maxIdle + "}";
        }
    }

    /**
     * 抽象建造者
     */
    interface ConfigurationBuilder {
        void buildDataSource();
        void buildPool();
        Configuration getResult();
    }

    /**
     * 具体建造者：XML配置建造者
     */
    static class XmlConfigurationBuilder implements ConfigurationBuilder {
        private Configuration configuration = new Configuration();

        @Override
        public void buildDataSource() {
            // 从XML解析数据源配置
            configuration.setDriver("com.mysql.cj.jdbc.Driver");
            configuration.setUrl("jdbc:mysql://localhost:3306/mydb");
            configuration.setUsername("root");
            configuration.setPassword("123456");
            System.out.println("XML建造者：解析数据源配置");
        }

        @Override
        public void buildPool() {
            // 从XML解析连接池配置
            configuration.setMaxActive(20);
            configuration.setMaxIdle(5);
            System.out.println("XML建造者：解析连接池配置");
        }

        @Override
        public Configuration getResult() {
            return configuration;
        }
    }

    /**
     * 具体建造者：Java配置建造者
     */
    static class JavaConfigurationBuilder implements ConfigurationBuilder {
        private Configuration configuration = new Configuration();

        @Override
        public void buildDataSource() {
            // 从Java配置读取
            configuration.setDriver("com.mysql.cj.jdbc.Driver");
            configuration.setUrl("jdbc:mysql://localhost:3306/mydb");
            configuration.setUsername("admin");
            configuration.setPassword("admin123");
            System.out.println("Java建造者：读取数据源配置");
        }

        @Override
        public void buildPool() {
            configuration.setMaxActive(50);
            configuration.setMaxIdle(10);
            System.out.println("Java建造者：读取连接池配置");
        }

        @Override
        public Configuration getResult() {
            return configuration;
        }
    }

    /**
     * 指挥者：控制构建过程
     */
    static class ConfigurationDirector {
        public Configuration construct(ConfigurationBuilder builder) {
            builder.buildDataSource();
            builder.buildPool();
            return builder.getResult();
        }
    }

    public static void main(String[] args) {
        ConfigurationDirector director = new ConfigurationDirector();

        // 使用XML建造者
        System.out.println("=== 使用XML配置 ===");
        ConfigurationBuilder xmlBuilder = new XmlConfigurationBuilder();
        Configuration xmlConfig = director.construct(xmlBuilder);
        System.out.println(xmlConfig);

        System.out.println();

        // 使用Java建造者
        System.out.println("=== 使用Java配置 ===");
        ConfigurationBuilder javaBuilder = new JavaConfigurationBuilder();
        Configuration javaConfig = director.construct(javaBuilder);
        System.out.println(javaConfig);
    }
}
