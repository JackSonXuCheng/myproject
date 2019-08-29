package com.myproject.common;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lihuazeng
 * @version Id: MvcCodeUtil.java, v 0.1 2019-7-2 10:44 lihuazeng Exp $$
 */
public class MvcCodeUtil {
    // web依赖包路径
    public static final String APP_WEB = "myproject-console";
    // dao依赖包路径
    public static final String APP_DAO = "myproject-mapper";
    // service依赖包路径
    public static final String APP_SERVICE = "myproject-service";
    // java 文件目录
    public static String PACKAGE_PATH = "com/myproject/";
    public static String PACKAGE_PATH_DOT = "com.myproject.";
    // webapp 管理页面目录（WEB_APP_PATH：项目view层模板的路径，WEB_APP_SUFFIX：view层文件的后缀名）
    public static String WEB_APP_PATH = "templates/";
    public static String WEB_APP_SUFFIX = ".ftl";
    /**
     * 路径 + 文件命名
     */
    public static Map<String, String> mvcPathMap = new HashMap<>();
    public static Map<String, String> mvcFileNameMap = new HashMap<>();
    public static Map<String, String> webappMap = new HashMap<>();

    static {
        mvcPathMap.put("mapper", APP_WEB + "/src/main/resources/mvctemplate/mapper.data");
        mvcPathMap.put("service", APP_WEB + "/src/main/resources/mvctemplate/service.data");
        mvcPathMap.put("service/impl", APP_WEB + "/src/main/resources/mvctemplate/serviceImpl.data");
        mvcPathMap.put("controller", APP_WEB + "/src/main/resources/mvctemplate/control.data");

        mvcFileNameMap.put("mapper", "Mapper.java");
        mvcFileNameMap.put("service", "Service.java");
        mvcFileNameMap.put("service/impl", "ServiceImpl.java");
        mvcFileNameMap.put("controller", "Controller.java");

        webappMap.put("add", APP_WEB + "/src/main/resources/mvctemplate/webappAdd.data");
        webappMap.put("edit", APP_WEB + "/src/main/resources/mvctemplate/webappEdit.data");
        webappMap.put("list", APP_WEB + "/src/main/resources/mvctemplate/webappList.data");
    }

    public static void main(String[] args) {
        addJavaAndWebapp("LDMemberVipRecord", "ldMemberVipRecord", "联动vip升级流水");
    }

    public static void generateJavaFiles(String entity, String chineseName, String webappName) {

        // 首字母小写
        String firstWord = entity.substring(0, 1);
        String entitySmall = entity.replaceFirst(firstWord, firstWord.toLowerCase());

        Set<String> javaPaths = mvcPathMap.keySet();
        try {

            for (String javaPath : javaPaths) {
                // 创建这个文件
                File targetFile = null;
                if ("mapper".equals(javaPath)) {
                    targetFile = new File(APP_DAO + "/src/main/java/" + PACKAGE_PATH + javaPath, entity +
                            mvcFileNameMap.get(javaPath));
                    System.out.println("创建java组建文件" + entity + mvcFileNameMap.get(javaPath) + "结果：" + targetFile
                            .createNewFile());
                }

                if ("service".equals(javaPath) || "service/impl".equals(javaPath)) {
                    targetFile = new File(APP_SERVICE + "/src/main/java/" + PACKAGE_PATH + javaPath, entity +
                            mvcFileNameMap.get(javaPath));
                    System.out.println("创建java组建文件" + entity + mvcFileNameMap.get(javaPath) + "结果：" + targetFile
                            .createNewFile());
                }
                if ("controller".equals(javaPath)) {
                    targetFile = new File(APP_WEB + "/src/main/java/" + PACKAGE_PATH + javaPath, entity +
                            mvcFileNameMap.get(javaPath));
                    System.out.println("创建java组建文件" + entity + mvcFileNameMap.get(javaPath) + "结果：" + targetFile
                            .createNewFile());
                }
                File sourcePath = new File(mvcPathMap.get(javaPath));

                FileReader read = new FileReader(sourcePath);
                BufferedReader br = new BufferedReader(read);
                String content = new String();

                while (br.ready() != false) {
                    content = content + br.readLine();
                    content = content.replaceAll("\\[entity_name\\]", entity);
                    content = content.replaceAll("\\[entity_name_small\\]", entitySmall);
                    content = content.replaceAll("\\[chinese_name\\]", chineseName);
                    content = content.replaceAll("\\[package_path\\]", PACKAGE_PATH_DOT);
                    content = content.replaceAll("\\[webapp_entity\\]", webappName);
                    content = content + ("\r\n");
                }
                FileWriter writer = new FileWriter(targetFile, true);
                writer.write(content);
                writer.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateWebappFiles(String entity, String webappName, String webappChineseName) {

        // 创建文件夹
        File dir = new File(APP_WEB + "/src/main/resources/" + WEB_APP_PATH + webappName);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        // 首字母小写
        String firstWord = entity.substring(0, 1);
        String entitySmall = entity.replaceFirst(firstWord, firstWord.toLowerCase());


        try {

            Set<String> pages = webappMap.keySet();
            for (String page : pages) {
                // 创建这个文件
                File targetFile = new File(APP_WEB + "/src/main/resources/" + WEB_APP_PATH + webappName, page +
                        WEB_APP_SUFFIX);
                System.out.println("创建管理后台视图文件" + page + WEB_APP_SUFFIX + "结果：" + targetFile.createNewFile());

                File sourcePath = new File(webappMap.get(page));

                FileReader read = new FileReader(sourcePath);
                BufferedReader br = new BufferedReader(read);
                String content = new String();

                while (br.ready() != false) {
                    content = content + br.readLine();
                    content = content.replaceAll("\\[entity_name\\]", entitySmall);
                    content = content.replaceAll("\\[webapp_entity\\]", webappName);
                    content = content.replaceAll("\\[webapp_entity_chinese\\]", webappChineseName);
                    content = content.replaceAll("\\[webapp_suffix\\]", WEB_APP_SUFFIX);
                    content = content + ("\r\n");
                }

                FileOutputStream in = new FileOutputStream(targetFile);
                FileWriter writer = new FileWriter(targetFile, true);
                writer.write(content);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param entity        entity的命名，将会用这个命名来创建各层的组建
     * @param webFoldName   webapp view层文件夹的命名，将会在webapp的视图目录新建一个文件夹
     * @param entityChinese 中文名称，将在所有的文件中使用这个中文名称
     */
    public static void addJavaAndWebapp(String entity, String webFoldName, String entityChinese) {
        generateJavaFiles(entity, entityChinese, webFoldName);
        generateWebappFiles(entity, webFoldName, entityChinese);
    }
}