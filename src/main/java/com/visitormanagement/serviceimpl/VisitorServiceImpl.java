package com.visitormanagement.serviceimpl;

import com.visitormanagement.entity.Visitor;
import com.visitormanagement.payload.VisitorDto;
import com.visitormanagement.repository.VisitorRepository;
import com.visitormanagement.service.VisitorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.TextInputCallback;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public VisitorServiceImpl(VisitorRepository visitorRepository, ModelMapper modelMapper) {
        this.visitorRepository = visitorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Visitor registerVisitor(VisitorDto visitorDto) {
        if (visitorRepository.existsByEmail(visitorDto.getEmail()) || visitorRepository.existsByContact(visitorDto.getContact())){
            throw new IllegalArgumentException("Email or contact already registered");
        }
        Visitor visitor = mapDtoToEntity(visitorDto);
        Visitor savedUser = visitorRepository.save(visitor);
        return savedUser;
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visitor not found"));
    }

    @Override
    public Visitor updateVisitor(Long id, VisitorDto visitorDto) {
        Visitor visitor = getVisitorById(id);

        visitor.setFirstName(visitorDto.getFirstName());
        visitor.setLastName(visitorDto.getLastName());
        visitor.setEmail(visitorDto.getEmail());
        visitor.setContact(visitorDto.getContact());
        visitor.setCity(visitorDto.getCity());

        return visitorRepository.save(visitor);
    }

    @Override
    public void deleteVisitor(Long id) {
        getVisitorById(id);
        visitorRepository.deleteById(id);
    }

    public Visitor mapDtoToEntity(VisitorDto visitorDto){
        return modelMapper.map(visitorDto, Visitor.class);
    }

    public VisitorDto mapEntityToDto(Visitor visitor){
        return modelMapper.map(visitor, VisitorDto.class);
    }
}
