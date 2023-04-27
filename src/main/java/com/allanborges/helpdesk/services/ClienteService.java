package com.allanborges.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.allanborges.helpdesk.domain.Pessoa;
import com.allanborges.helpdesk.domain.Cliente;
import com.allanborges.helpdesk.domain.dtos.ClienteDTO;
import com.allanborges.helpdesk.repositories.PessoaRepository;
import com.allanborges.helpdesk.repositories.ClienteRepository;
import com.allanborges.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.allanborges.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorNifEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorNifEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validaPorNifEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByNif(objDTO.getNif());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("NIF já cadastado no sistema! " + objDTO.getNif());
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastado no sistema! " + objDTO.getEmail());
		}
	}

}