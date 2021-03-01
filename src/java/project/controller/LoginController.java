/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import bda.dao.BussinessException;
import bda.dao.BussinessMessage;
import project.model.Usuario;
import project.persistencia.dao.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.model.Cotanieve;
import project.model.Dia;
import project.persistencia.dao.CotanieveDAO;
import project.persistencia.dao.DiaDAO;

@Controller
public class LoginController {
    
    @RequestMapping({"/index.html"})
    public ModelAndView portada(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName = "portada";

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/login.html"})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName = "login";

        return new ModelAndView(viewName, model);
    }
    
    

}
