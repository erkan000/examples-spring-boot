package examples.springboot.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examples.springboot.entity.TechnologyLabel;
import examples.springboot.repo.TechnologyLabelRepository;
import examples.springboot.search.FilterParamsDTO;
import examples.springboot.search.ResponseDTO;
import examples.springboot.spec.TechnologyLabelSpec;
import examples.springboot.spec.TechnologyLabelSpecJoinFetch;

@Service
public class TechnologyLabelSpecService {

    @Autowired
    TechnologyLabelRepository techLabelRepository;
    
	@Autowired
    private ModelMapper modelMapper;
	
    public List<ResponseDTO> findByTechnologyLabelSpecJoinFetch(FilterParamsDTO searchCriteria) {      
        
    	TechnologyLabelSpecJoinFetch spec = new TechnologyLabelSpecJoinFetch(searchCriteria);        
        
        List<TechnologyLabel> result = techLabelRepository.findAll(spec);
        
        return result.stream().map(new Function<TechnologyLabel, ResponseDTO>() {
            @Override
            public ResponseDTO apply(TechnologyLabel entity) {
                return convertEntityToDto(entity);
            }
        }).collect(Collectors.toList());
    }

    public List<ResponseDTO> findByTechnologyLabelSpec(FilterParamsDTO searchCriteria) {

        TechnologyLabelSpec spec = new TechnologyLabelSpec(searchCriteria);        
        
        List<TechnologyLabel> result = techLabelRepository.findAll(spec);
        
        return result.stream().map(new Function<TechnologyLabel, ResponseDTO>() {
            @Override
            public ResponseDTO apply(TechnologyLabel entity) {
                return convertEntityToDto(entity);
            }
        }).collect(Collectors.toList());
    }

    private ResponseDTO convertEntityToDto(TechnologyLabel transactionRequest){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseDTO transactionRequestResponse;
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        transactionRequestResponse = modelMapper.map(transactionRequest, ResponseDTO.class);
        return transactionRequestResponse;
    }

}
