package directoryTestRest;


import org.apache.ibatis.mapping.CacheBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableCaching
public class RestRegionServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(RestRegionServiceApplication.class, args);
    }


}
