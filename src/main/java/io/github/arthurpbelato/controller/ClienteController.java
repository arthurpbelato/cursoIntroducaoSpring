package io.github.arthurpbelato.controller;

import io.github.arthurpbelato.model.Cliente;
import io.github.arthurpbelato.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> cadastroCliente(@RequestBody @Valid Cliente cliente, HttpServletResponse response){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(clienteSalvo.getCodCliente()).toUri();

        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Cliente>> buscarPeloCodigo(@PathVariable BigInteger codigo){
        Optional<Cliente> cliente = clienteRepository.findById(codigo);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity buscarPorFiltro(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> clientes = clienteRepository.findAll(example);
        return ResponseEntity.ok(clientes);
    }
}
