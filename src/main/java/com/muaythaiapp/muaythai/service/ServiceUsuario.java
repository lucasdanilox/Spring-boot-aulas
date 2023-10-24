package com.muaythaiapp.muaythai.service;

import com.muaythaiapp.muaythai.respositorio.RepositorioUsuario;
import com.muaythaiapp.muaythai.usuario.ModeloUsuario;
import com.muaythaiapp.muaythai.util.SenhaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;


    public List<ModeloUsuario> listModel() {
        return repositorioUsuario.findAll();
    }

    public ModeloUsuario senhaC(ModeloUsuario modeloUsuario){
        String senhaNaoCriptografada = modeloUsuario.getPassword();
        String senhaCriptografada = SenhaUtil.criptografar(senhaNaoCriptografada);
        modeloUsuario.setPassword(senhaCriptografada);
        return repositorioUsuario.save(modeloUsuario);
    }
    public boolean verificarSenha(String senhaDigitada, String senhaArmazenada){
     return SenhaUtil.verificarSenha(senhaDigitada, senhaArmazenada);
    }

}
