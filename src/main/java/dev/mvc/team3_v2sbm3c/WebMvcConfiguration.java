package dev.mvc.team3_v2sbm3c;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.bulletin.Bulletin;
import dev.mvc.notices.Notices;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      // Windows: path = "C:/kd/deploy/resort_v2sbm3c_blog/contents/storage";
      // ▶ file:///C:/kd/deploy/resort_v2sbm3c_blog/contents/storage
    
      // Ubuntu: path = "/home/ubuntu/deploy/resort_v2sbm3c_blog/contents/storage";
      // ▶ file:////home/ubuntu/deploy/resort_v2sbm3c_blog/contents/storage
    
      // JSP 인식되는 경로: http://localhost:9091/contents/storage";
      registry.addResourceHandler("/notices/storage/**").addResourceLocations("file:///" +  Notices.getUploadDir());
      
      // JSP 인식되는 경로: http://localhost:9091/contents/storage";
      registry.addResourceHandler("/bulletin/storage/**").addResourceLocations("file:///" +  Bulletin.getUploadDir());
      
      // JSP 인식되는 경로: http://localhost:9091/attachfile/storage";
      // registry.addResourceHandler("/attachfile/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
      
      // JSP 인식되는 경로: http://localhost:9091/member/storage";
      // registry.addResourceHandler("/member/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");
  }
}
