package br.gov.sp.fatec.projetoia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.dtos.UserDTO;
import br.gov.sp.fatec.projetoia.entity.PaperEntity;
import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.repository.PaperRepository;
import br.gov.sp.fatec.projetoia.repository.UserRepository;
import br.gov.sp.fatec.projetoia.utils.EmailSender;
import br.gov.sp.fatec.projetoia.utils.PasswordGenerator;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private EmailSender emailSender;

    public List<UserEntity> getAll() {
        return repo.findAll();
    }

    public Optional<UserEntity> getById(Long id) {
        return repo.findById(id);
    }

    public UserEntity insert(UserDTO data) {
        List<UserEntity> userWithEmail = repo.findByEmail(data.getEmail());
        if(userWithEmail.size() > 0){
            throw new EntityExistsException("Já existe um usuário com o email informado.");
        }

        PaperEntity paperEntity = paperRepository.findById(data.getIdPapel()).orElse(null);
        if(paperEntity == null) throw new EntityNotFoundException("Cargo não encontrado.");

        String password = passwordGenerator.generateRandomPassword(8);

        UserEntity entity = new UserEntity();
        entity.setEmail(data.getEmail());
        entity.setNome(data.getNome());
        entity.setPassword(password); 
        entity.setPapel(paperEntity);       

        repo.save(entity);

        // enviar email
        try{
            emailSender.sendNewUserEmail(data.getEmail(), password);
        }catch(Exception e){
            System.out.println(e);
        }

        return entity;
    }

    public void delete(Long id) {
        Optional<UserEntity> entity = repo.findById(id);
        if(entity.isEmpty()){
            throw new EntityNotFoundException("Usuário não encontrado.");
        }

        repo.delete(entity.get());
    }

    public UserEntity update(Long id, UserDTO data) {
        UserEntity user = repo.findById(id).orElse(null);
        if(user == null) throw new EntityNotFoundException("Usuário não encontrado.");
        
        user.setNome(data.getNome());
        
        if(!data.getEmail().equalsIgnoreCase(user.getEmail())){
            List<UserEntity> userWithEmail = repo.findByEmail(data.getEmail());
            if(userWithEmail.size() > 0){
                throw new EntityExistsException("Já existe um usuário com o email informado.");
            }

            user.setEmail(data.getEmail());
        }


        if(data.getIdPapel() != user.getPapel().getId()){
            PaperEntity paperEntity = paperRepository.findById(data.getIdPapel()).orElse(null);
            if(paperEntity == null) throw new EntityNotFoundException("Cargo não encontrado.");
            
            user.setPapel(paperEntity);
        }

        return repo.save(user);
    }
}
