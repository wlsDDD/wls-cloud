package cn.erectpine.common.generator.doc;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 实体类md文档生成器
 *
 * @Author wls
 * @Date 2021/5/17 15:47
 */
@Slf4j
public class MdGenerator {
    
    static String entityPath = "ftl/doc/entity.md.ftl";
    static String paramPath = "ftl/doc/param.md.ftl";
    static String outputPath = System.getProperty("user.dir") + "/src/test/{}.md";
    
    /**
     * 实体文档
     *
     * @param clazz clazz
     */
    public static void genMd(Class<?> clazz) {
        genMd(clazz, entityPath);
    }
    
    /**
     * 参数文档
     *
     * @param clazz clazz
     */
    public static void genMd(Class<?> clazz, String templatePath) {
        genMd(clazz, templatePath, outputPath);
    }
    
    /**
     * 生成文档
     */
    private static void genMd(Class<?> clazz, String templatePath, String outputPath) {
        try {
            WLsProperty wLsProperty = AnnotationUtil.getAnnotation(clazz, WLsProperty.class);
            outputPath = StrUtil.format(outputPath, wLsProperty.value());
            Map<String, Object> objectMap = new LinkedHashMap<>();
            objectMap.put("fields", getFields(clazz));
            objectMap.put("entityName", wLsProperty.value());
            Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            config.setDefaultEncoding(ConstVal.UTF8);
            config.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
            Template template = config.getTemplate(templatePath);
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
                template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
            }
            log.debug("模板:" + templatePath + ";  文件:" + outputPath);
        } catch (Exception e) {
            log.error("生成文档异常", e);
        }
    }
    
    /**
     * 获取字段列表
     *
     * @param clazz clazz
     * @return {@link List<WlsField>}
     */
    private static List<WlsField> getFields(Class<?> clazz) {
        List<WlsField> list = new LinkedList<>();
        BeanDesc beanDesc = BeanUtil.getBeanDesc(clazz);
        beanDesc.getProps().forEach(propDesc -> {
            Field field = propDesc.getField();
            WLsProperty wLsProperty = field.getAnnotation(WLsProperty.class);
            if (wLsProperty != null) {
                WlsField filed = new WlsField()
                        .setPropertyName(field.getName())
                        .setPropertyType(field.getType().getSimpleName())
                        .setComment(wLsProperty.value())
                        .setRequired(wLsProperty.required() ? "是" : "否");
                list.add(filed);
            }
        });
        return list;
    }
    
}
