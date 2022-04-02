package com.library.resources;

import com.library.models.Borrower;
import com.library.services.implementation.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/borrower")
public class BorrowerResource {

    private final BorrowerService borrowerService;

    public BorrowerResource(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/list")
    public ResponseEntity<Optional<List<Borrower>>> fetchAllBorrowers(){
        return new ResponseEntity<>(borrowerService.retrieveAllBorrower(), OK);
    }

    @GetMapping("/{borrowerId}")
    public ResponseEntity<Optional<Borrower>> borrowerById(@PathVariable Long borrowerId){
        return new ResponseEntity<>(borrowerService.getBorrowerById(borrowerId), OK);
    }

    @GetMapping()
    public ResponseEntity<Optional<Borrower>> getBorrowerByEmail(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(borrowerService.getBorrowerByEmail(email), OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Optional<Borrower>> saveBorrower(@RequestBody Borrower borrower){
        ResponseEntity<Optional<Borrower>> response ;
        if(borrower.getId() != null){
            Optional<Borrower> updateBorrowerInfo = borrowerService.updateBorrower(borrower);
            response = new ResponseEntity<>(updateBorrowerInfo, OK);
        }else{
            Optional<Borrower> saveABorrower = borrowerService.saveBorrower(borrower);
            response = new ResponseEntity<>(saveABorrower, CREATED);
        }
        return response;
    }

    @PutMapping("/delete")
    public ResponseEntity<Optional<Borrower>> removeBorrower(@RequestParam(value = "id") Long id){
        return new ResponseEntity<>(borrowerService.deleteBorrowerById(id), OK);
    }

    @PutMapping("/activate/account")
    public ResponseEntity<Optional<Borrower>> activateAccount(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(borrowerService.retrieveAccount(email), OK);
    }
}
