package com.coocit.db;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
public class MyBatisPlusGenerator {
    private static String DataBaseName = "cloud_account";
    private static String Url = "jdbc:mysql://47.99.139.126:3306/" + DataBaseName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    private static String DriverName = "com.mysql.cj.jdbc.Driver";
    private static String Username = "root";
    private static String Password = "Root0000";
    private static String projectPath = "E:\\workspace\\Java_workspace\\cloud-short-link\\cloud-account";

    public static void main(String[] args) {
        // 1、代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig()
                .setAuthor("Coocit")
                // 定义生成的实体类中日期类型
                .setDateType(DateType.ONLY_DATE)
                // 重新生成时文件是否覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)
                // 输出路径
                .setOutputDir(projectPath + "/src/main/java")
                // 去掉Service接口的首字母I
                .setServiceName("%sService")
                // 实体类结尾名称
                .setEntityName("%sDO")
                // 生成基本的resultMap
                .setBaseResultMap(true)
                // 不使用 AR模式
                .setActiveRecord(false)
                // 生成基本的SQL片段
                .setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig()
//                .setDbQuery()               // 数据库信息查询类
//                .setSchemaName()
//                .setTypeConvert()
                .setDbType(DbType.MYSQL)
                .setDriverName(DriverName)
                .setPassword(Password)
                .setUrl(Url)
                .setUsername(Username);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig()
                .setParent("com.coocit")
                .setController("controller")
                .setEntity("model")
                .setMapper("mapper")
                .setService("service")
                .setModuleName("")
//                .setPathInfo()              // 路径配置信息
                .setServiceImpl("service.impl")
                .setXml("mapper.xml");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig()
                // url中驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                // 数据库表映射到实体的命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                // 使用lombok
                .setEntityLombokModel(true)
                // restful api风格控制器
                .setRestControllerStyle(true)
                // 生成实体时去掉表前缀
                //.setTablePrefix("tb_")
                // 公共父类 , 你自己的父类控制器,没有就不用设置!
//                .setSuperControllerClass()
                // 自定义继承的Entity类全称，带包名
//                .setSuperEntityClass()
                // 写于父类中的公共字段
//                .setSuperEntityColumns()
                // 生成的表，支持多表一起生成
                .setInclude(
                        "account",
                        "traffic",
                        "traffic_task"
                );

        mpg.setStrategy(strategy);

        // 6、添加模板引擎
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 7、执行
        mpg.execute();
    }
}
