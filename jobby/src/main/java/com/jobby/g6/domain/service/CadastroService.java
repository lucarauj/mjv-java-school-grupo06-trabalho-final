package com.jobby.g6.domain.service;

import com.jobby.g6.domain.exception.CadastroNaoEncontradoException;
import com.jobby.g6.domain.exception.NegocioException;
import com.jobby.g6.domain.model.Cadastro;
import com.jobby.g6.domain.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroService{

    @Autowired
    private CadastroRepository cadastroRepository;

    @Transactional
    public Cadastro salvar(Cadastro cadastro) {
        Optional<Cadastro> cadastroExistente = cadastroRepository.findById(cadastro.getId());

        if(cadastroExistente.isPresent() && cadastroExistente.get().equals(cadastro)) {
            throw new NegocioException(
                    String.format("Já existe um cadastro para esse código: %d", cadastroExistente)
            );
        }

        return cadastroRepository.save(cadastro);
    }

    @Transactional
    public void deletar(Integer cadastroId){
        try{
            cadastroRepository.deleteById(cadastroId);
            cadastroRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new CadastroNaoEncontradoException(cadastroId);
        }
    }

    @Transactional
    public Cadastro atualizar(Integer cadastroId, Cadastro novoCadastro){
        Cadastro cadastroExistente = buscar(cadastroId);

        if(cadastroExistente!=null){
            cadastroExistente.setCpf(novoCadastro.getCpf());
            cadastroExistente.setNome(novoCadastro.getNome());
            cadastroExistente.setDataNascimento(novoCadastro.getDataNascimento());
            cadastroExistente.setTelefone(novoCadastro.getTelefone());
            cadastroExistente.setEmail(novoCadastro.getEmail());
            cadastroExistente.setHabilidades(novoCadastro.getHabilidades());
            cadastroExistente.setProfissao(novoCadastro.getProfissao());
            cadastroExistente.setPretensaoSalarial(novoCadastro.getPretensaoSalarial());
            cadastroExistente.setSexoEnum(novoCadastro.getSexoEnum());
            cadastroExistente.setCelular(novoCadastro.getCelular());
            cadastroExistente.setEndereco(novoCadastro.getEndereco());
            cadastroExistente.setCadastroExperiencia(novoCadastro.getCadastroExperiencia());

            return salvar(cadastroExistente);
        }else{
            throw new CadastroNaoEncontradoException(cadastroId);
        }
    }

    public Cadastro buscar(Integer cadastroId){
        return cadastroRepository.findById(cadastroId)
                .orElseThrow(() -> new CadastroNaoEncontradoException(cadastroId));
    }
}