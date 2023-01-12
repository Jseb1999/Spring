package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWtUtil jWtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario usuarioLogueado =usuarioDao.obtenerUsuarioCredenciales(usuario);
        if(usuarioLogueado!=null){

            String tokenJwt = jWtUtil.create(String.valueOf(usuarioLogueado.getId()), usuario.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

}
