package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.dtos.UserDTO;
import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.entity.PaperEntity;
import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.entity.UserPasswordTokenEntity;
import br.gov.sp.fatec.projetoia.repository.AreaRepository;
import br.gov.sp.fatec.projetoia.repository.PaperRepository;
import br.gov.sp.fatec.projetoia.repository.RedZoneRepository;
import br.gov.sp.fatec.projetoia.repository.UserPasswordTokenRepository;
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
    private AreaRepository areaRepository;
    @Autowired
    private RedZoneRepository redZoneRepository;
    @Autowired
    private UserPasswordTokenRepository userPasswordTokenRepository;
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

        Set<AreaEntity> areas = new HashSet<>();
        Set<RedZoneEntity> redzones = new HashSet<>();

        if(data.getAreas() != null && !data.getAreas().isEmpty()){
            data.getAreas().forEach(r-> {
                AreaEntity area = areaRepository.findById(r).orElse(null);
                if(area != null) areas.add(area);
            });
        }

        if(data.getRedzones() != null && !data.getRedzones().isEmpty()){
            data.getRedzones().forEach(r-> {
                RedZoneEntity redzone = redZoneRepository.findById(r).orElse(null);
                if(redzone != null) redzones.add(redzone);
            });
        }

        PaperEntity paperEntity = paperRepository.findById(data.getIdPapel()).orElse(null);
        if(paperEntity == null) throw new EntityNotFoundException("Cargo não encontrado.");

        String password = passwordGenerator.generateRandomPassword(8);

        UserEntity entity = new UserEntity();
        entity.setEmail(data.getEmail());
        entity.setNome(data.getNome());
        entity.setSenha(password); 
        entity.setPapel(paperEntity);   
        entity.setAreas(areas);
        entity.setRedzones(redzones);    

        repo.save(entity);

        // enviar email
        try{
            emailSender.sendEmailNewUser(data.getEmail(), password);
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

        Set<AreaEntity> areas = new HashSet<>();
        Set<RedZoneEntity> redzones = new HashSet<>();

        if(data.getAreas() != null && !data.getAreas().isEmpty()){
            data.getAreas().forEach(r-> {
                AreaEntity area = areaRepository.findById(r).orElse(null);
                if(area != null) areas.add(area);
            });
        }

        if(data.getRedzones() != null && !data.getRedzones().isEmpty()){
            data.getRedzones().forEach(r-> {
                RedZoneEntity redzone = redZoneRepository.findById(r).orElse(null);
                if(redzone != null) redzones.add(redzone);
            });
        }

        user.setAreas(areas);
        user.setRedzones(redzones);

        return repo.save(user);
    }

    public void createPasswordResetTokenForUser(Long id, String token) {
        UserEntity user = repo.findById(id).orElse(null);
        if(user == null) throw new EntityNotFoundException("Usuário não encontrado.");

        UserPasswordTokenEntity userPasswordTokenEntity = new UserPasswordTokenEntity();
        userPasswordTokenEntity.setToken(token);
        userPasswordTokenEntity.setUser(user);
        userPasswordTokenEntity.setExpiryDate(LocalDateTime.now().plusHours(24));
        userPasswordTokenRepository.save(userPasswordTokenEntity);
    }

    public UserEntity getUserByPasswordResetToken(String token) {
        return userPasswordTokenRepository.findByToken(token)
                .filter(r-> r.getExpiryDate().isAfter(LocalDateTime.now()))
                .map(UserPasswordTokenEntity::getUser)
                .orElse(null);
    }

    public void changeUserPassword(Long id, String newPassword) {
        UserEntity user = repo.findById(id).orElse(null);
        if(user == null) throw new EntityNotFoundException("Usuário não encontrado.");

        user.setSenha(newPassword);

        repo.save(user);
    }
}
