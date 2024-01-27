    package com.example.careerify.controller;

    import com.example.careerify.common.dto.ApplicantDTO;
    import com.example.careerify.common.dto.EmployeerDTO;
    import com.example.careerify.common.dto.JobPostingDTO;
    import com.example.careerify.service.ApplicantService;
    import com.example.careerify.service.EmployeerService;
    import jakarta.persistence.EntityNotFoundException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.UUID;

    @RestController
    @RequestMapping("/employeer")
    public class  EmployeerController {

        private final EmployeerService employeerService;
        @Autowired
        public EmployeerController(EmployeerService employeerService) {
            this.employeerService = employeerService;
        }

        @PostMapping
        public ResponseEntity<Object> createEmployeer(@RequestBody EmployeerDTO employeerDTO) {
            try {
                EmployeerDTO createdApplicant = employeerService.createEmployeer(employeerDTO);
                return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Error creating employeer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> getEmployeerById(@PathVariable UUID id) {
            EmployeerDTO employeerDTO = employeerService.getEmployeerById(id);
            return new ResponseEntity<>(employeerDTO, HttpStatus.OK);
        }
        @GetMapping
        public ResponseEntity<Page<EmployeerDTO>> getAllEmployeer(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size) {

            PageRequest pageable = PageRequest.of(page, size);
            Page<EmployeerDTO> employeers = employeerService.getAllEmployeers(pageable);
            return new ResponseEntity<>(employeers, HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> updateEmployeer(@PathVariable UUID id, @RequestBody EmployeerDTO updateDTO) {
            employeerService.updateEmployeer(id, updateDTO);
            return new ResponseEntity<>("Employeer updated successfully", HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteEmployeer(@PathVariable UUID id) {
            employeerService.deleteEmployeer(id);
            return new ResponseEntity<>("Employeer deleted successfully", HttpStatus.NO_CONTENT);
        }

      /*  @PostMapping("/{employeerId}/job-postings")
        public ResponseEntity<Long> createJobPosting(
                @PathVariable UUID employeerId,
                @RequestBody JobPostingDTO jobPostingDTO) {
            Long createdJobPostingId = employeerService.createJobPosting(employeerId, jobPostingDTO);
            return new ResponseEntity<>(createdJobPostingId, HttpStatus.CREATED);
        }*/

    }
