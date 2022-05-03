package directoryTestRest.service;


import directoryTestRest.Config.CachingConfig;
import directoryTestRest.Util.MyBatisUtil;
import directoryTestRest.mapper.RegionMapper;
import directoryTestRest.model.Region;


import org.apache.ibatis.session.SqlSession;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RegionService {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CachingConfig.class);

    public static void printNativeCache(ApplicationContext context) {    //метод создан для проверки работы кэша
        Cache cache = context.getBean(CacheManager.class).getCache("regions-cache");
        System.out.println("-- native cache --");
        System.out.println(cache.getNativeCache());
    }


    @Cacheable(value = "regions-cache", key = "#region.id")
    public void insertRegion(Region region) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try{
            RegionMapper regionMapper = sqlSession.getMapper(RegionMapper.class);
            regionMapper.insertRegion(region);
            sqlSession.commit();
        }finally{
            sqlSession.close();
            RegionService.printNativeCache(context);
        }
    }

    @Cacheable("regions-cache")
    public Region getRegionById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try{
            RegionMapper regionMapper = sqlSession.getMapper(RegionMapper.class);
            return regionMapper.getRegionById(id);
        }finally{
            sqlSession.close();
            RegionService.printNativeCache(context);
        }
    }

   @Cacheable("regions-cache")
    public List<Region> getAllRegions() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try{
            RegionMapper regionMapper = sqlSession.getMapper(RegionMapper.class);
            return regionMapper.getAllRegions();
        }finally{
            sqlSession.close();
            RegionService.printNativeCache(context);
        }
    }

    @CacheEvict (beforeInvocation = true) @Cacheable
    public boolean updateRegion(Region region) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try{
            RegionMapper regionMapper = sqlSession.getMapper(RegionMapper.class);
            regionMapper.updateRegion(region);
            sqlSession.commit();
        }finally{
            sqlSession.close();
            RegionService.printNativeCache(context);
        }
        return false;
    }

    @CacheEvict(value = "regions-cache", key = "#id")
    public boolean deleteRegion(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try{
            RegionMapper regionMapper = sqlSession.getMapper(RegionMapper.class);
            regionMapper.deleteRegion(id);
            sqlSession.commit();
        }finally{
            sqlSession.close();
            RegionService.printNativeCache(context);
        }

        return false;
    }

}