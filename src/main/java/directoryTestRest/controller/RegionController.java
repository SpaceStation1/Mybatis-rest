package directoryTestRest.controller;

import directoryTestRest.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import directoryTestRest.service.RegionService;

import java.util.List;

@RestController
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    private  RegionService regionService;

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody Region region) {
        regionService.insertRegion(region);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Region>> read() {
        final List<Region> regions = regionService.getAllRegions();

        return regions != null &&  !regions.isEmpty()
                ? new ResponseEntity<>(regions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Region> read(@PathVariable(name = "id") int id) {
        final Region regions = regionService.getRegionById(id);

        return regions != null
                ? new ResponseEntity<>(regions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Region region) {
        final boolean updated = regionService.updateRegion(region);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = regionService.deleteRegion(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
