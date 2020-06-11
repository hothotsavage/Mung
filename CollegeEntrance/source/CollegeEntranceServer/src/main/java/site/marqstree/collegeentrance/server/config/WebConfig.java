package site.marqstree.collegeentrance.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

//上传配置类
//图片放到/F:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 文件上传路径
    @Value("${upload.filePath}")
    private String uploadFilePath;

    //文件下载url
    @Value("${download.url}")
    private String downloadUrl;

    //将本地路径映射为静态文件下载路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.print("uploadFilePath============="+ uploadFilePath +"\n");
        registry.addResourceHandler(downloadUrl+"/**").addResourceLocations(uploadFilePath);
    }

}
