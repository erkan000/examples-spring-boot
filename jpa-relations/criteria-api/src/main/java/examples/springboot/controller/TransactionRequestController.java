package examples.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examples.springboot.search.FilterParamsDTO;
import examples.springboot.search.ResponseDTO;
import examples.springboot.service.TechnologyLabelSpecService;

@RestController
@Component
@RequestMapping("/api")
public class TransactionRequestController {

    @Autowired
    private TechnologyLabelSpecService transactionRequestService;


    @PostMapping("/spec")
    public ResponseEntity<?> findByTransactionRequestSpec( @RequestBody FilterParamsDTO searchCriteria) {
    	List<ResponseDTO> result  = transactionRequestService.findByTechnologyLabelSpec(searchCriteria);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/specJoinFetch")
    public ResponseEntity<?> findByTransactionRequestSpecJoinFetch( @RequestBody FilterParamsDTO searchCriteria) {
    	List<ResponseDTO> result  = transactionRequestService.findByTechnologyLabelSpecJoinFetch(searchCriteria);
        return ResponseEntity.ok(result);
    }

}


