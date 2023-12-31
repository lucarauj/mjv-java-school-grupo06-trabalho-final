package com.jobby.g6.api.controller;

import com.jobby.g6.api.dto.assemblers.CadastroAssembler;
import com.jobby.g6.api.dto.assemblers.CadastroDisassembler;
import com.jobby.g6.api.dto.model.CadastroModel;
import com.jobby.g6.api.dto.model.input.CadastroInput;
import com.jobby.g6.domain.exception.CadastroNaoEncontradoException;
import com.jobby.g6.domain.exception.NegocioException;
import com.jobby.g6.domain.model.Cadastro;
import com.jobby.g6.domain.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private CadastroAssembler cadastroAssembler;

    @Autowired
    private CadastroDisassembler cadastroDisassembler;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CadastroModel> listarTodos() {
        List<Cadastro> cadastros = cadastroService.buscarTodos();
        return cadastroAssembler.toCollectionModel(cadastros);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CadastroModel buscarPorId(@PathVariable Integer id){
        Cadastro cadastro = cadastroService.buscar(id);
        return cadastroAssembler.toModel(cadastro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        cadastroService.deletar(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroModel criar(@RequestBody @Valid CadastroInput cadastroInput){
        try{
            Cadastro cadastro = cadastroDisassembler.toDomainObject(cadastroInput);
            return cadastroAssembler.toModel(cadastroService.salvar(cadastro));
        }catch (CadastroNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CadastroModel atualizar(@PathVariable Integer id, @RequestBody @Valid CadastroInput cadastroInput){
        Cadastro cadastroAtual = cadastroService.buscar(id);
        cadastroDisassembler.copyToDomainObject(cadastroInput, cadastroAtual);
        try{
            return cadastroAssembler.toModel(cadastroService.salvar(cadastroAtual));
        }catch (CadastroNaoEncontradoException e){
            throw new CadastroNaoEncontradoException(e.getMessage());
        }
    }
}
