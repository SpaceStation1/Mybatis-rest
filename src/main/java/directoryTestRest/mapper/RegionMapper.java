package directoryTestRest.mapper;

import directoryTestRest.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RegionMapper {

    @Insert("INSERT INTO regions(name, shortName) VALUES(#{name}, #{shortName})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public void insertRegion(Region region);

    @Select("SELECT id AS id, name as name, shortName as shortName FROM regions WHERE id=#{id}")
    public Region getRegionById(Integer regionId);

    @Select("SELECT * FROM regions")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property="name", column="name"),
            @Result(property="shortName", column="shortName")
    })
    public List<Region> getAllRegions();

    @Update("UPDATE regions SET name=#{name}, shortName=#{shortName} WHERE id=#{id}")
    public void updateRegion(Region region);

    @Delete("DELETE FROM regions WHERE id=#{id}")
    public void deleteRegion(Integer regionId);
}
