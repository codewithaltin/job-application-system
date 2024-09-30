package com.example.careerify.mbrojtja.Parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/parents")
public class ParentController {

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @PostMapping
    public Parent createParent(@RequestBody Parent parent) {
        return parentRepository.save(parent);
    }


    /*TODO: nese shtojme ndonje field ne model duhet ta bejme set edhe ktu ate field si fields e tjera, nese ndryshojme emrin e fields qe ekzitojme do kemi nje syntax error!*/
    @PutMapping("/{id}")
    public Parent updateParent(@PathVariable Long id, @RequestBody Parent parentDetails) {
        Parent parent = parentRepository.findById(id).orElseThrow();
        parent.setName(parentDetails.getName());
        parent.setDescription(parentDetails.getDescription());
        return parentRepository.save(parent);
    }

    @DeleteMapping("/{id}")
    public void deleteParent(@PathVariable Long id) {
        parentRepository.deleteById(id);
    }

    @PutMapping("/soft-delete/{id}")
    public Parent softDeleteParent(@PathVariable Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow();
        parent.setDeleted(true);
        return parentRepository.save(parent);
    }
}
