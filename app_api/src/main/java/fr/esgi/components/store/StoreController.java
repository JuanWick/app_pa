package fr.esgi.components.store;

import entities.Store;
import fr.esgi.reporitories.stores.services.StoreData;
import fr.esgi.services.stores.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreService storeService;
    @Autowired
    StoreData storeData;

    public StoreController() {}

    @GetMapping("")
    public List<Store> getAll() {
        return storeService.getAll(storeData);
    }

    @GetMapping("/{id}")
    public Store getById(@PathVariable(value="id") int id) {
        return storeService.getById(storeData,id);
    }

    @PostMapping("")
    public Store addOrUpdate(@RequestBody final Store store){

        return store;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(value="id") String id){

        return true;
    }
}
