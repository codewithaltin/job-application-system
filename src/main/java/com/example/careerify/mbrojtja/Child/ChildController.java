package com.example.careerify.mbrojtja.Child;

import com.example.careerify.mbrojtja.Parent.Parent;
import com.example.careerify.mbrojtja.Parent.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/children")
public class ChildController {


    private ChildRepository childRepository;
    private ParentRepository parentRepository;


    @GetMapping
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @PostMapping
    public Child createChild(@RequestBody Child child) {
        if (child.getParent() != null && child.getParent().getId() != null) {
            Parent parent = parentRepository.findById(child.getParent().getId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));

            child.setParent(parent);
        }
        return childRepository.save(child);
    }

    /*TODO: nese shtojme ndonje field ne model duhet ta bejme set edhe ktu ate field si fields e tjera, nese ndryshojme emrin e fields qe ekzitojme do kemi nje syntax error!*/
    @PutMapping("/{id}")
    public Child updateChild(@PathVariable Long id, @RequestBody Child childDetails) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        if (childDetails.getParent() != null && childDetails.getParent().getId() != null) {
            Parent parent = parentRepository.findById(childDetails.getParent().getId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));

            child.setParent(parent);
        }

        child.setName(childDetails.getName());
        return childRepository.save(child);
    }

    @DeleteMapping("/{id}")
    public void deleteChild(@PathVariable Long id) {
        childRepository.deleteById(id);
    }

    @PutMapping("/soft-delete/{id}")
    public Child softDeleteChild(@PathVariable Long id) {
        Child child = childRepository.findById(id).orElseThrow();
//        child.setDeleted(true);
        return childRepository.save(child);
    }
}
