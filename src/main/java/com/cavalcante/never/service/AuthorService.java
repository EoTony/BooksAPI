package com.cavalcante.never.service;

import com.cavalcante.never.exceptions.DataBaseException;
import com.cavalcante.never.exceptions.InvalidEmailException;
import com.cavalcante.never.model.author.Author;
import com.cavalcante.never.model.author.AuthorRequestDTO;
import com.cavalcante.never.model.author.AuthorResponseDTO;
import com.cavalcante.never.repositories.AuthorRepository;
import com.cavalcante.never.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Page<AuthorResponseDTO> findAll(Pageable pageable){
        return authorRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public AuthorResponseDTO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return toResponse(author);
    }

    @Transactional
    public AuthorResponseDTO insert(AuthorRequestDTO authorRequestDTO){
        if(this.authorRepository.existsByEmail(authorRequestDTO.email())){
            throw new InvalidEmailException("Email invalido para cadastro:"+authorRequestDTO.email());
        }
        Author author = new Author();

        author.setName(authorRequestDTO.name());
        author.setEmail(authorRequestDTO.email());
        author.setBirthdate(authorRequestDTO.birthdate());
        author.setDescription(authorRequestDTO.description());
        author.setNacionality(authorRequestDTO.nacionality());

        authorRepository.save(author);

        return toResponse(author);
    }

    @Transactional
    public void deleteById(Long id){
        try {
            authorRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    @Transactional
    public AuthorResponseDTO update(AuthorRequestDTO authorRequestDTO, Long id){

        if(authorRepository.existsByEmailAndIdNot(authorRequestDTO.email(), id)){
            throw new InvalidEmailException("Email invalido: "+ authorRequestDTO.email());
        }

        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        author.setName(authorRequestDTO.name());
        author.setEmail(authorRequestDTO.email());
        author.setDescription(authorRequestDTO.description());
        author.setBirthdate(authorRequestDTO.birthdate());
        author.setNacionality(authorRequestDTO.nacionality());

        authorRepository.save(author);

        return toResponse(author);
    }

    private AuthorResponseDTO toResponse(Author author){
        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getDescription(),
                author.getBirthdate(),
                author.getNacionality());
    }


}
