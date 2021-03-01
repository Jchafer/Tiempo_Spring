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
public class UsuarioController {
    
    @Autowired
    private DiaDAO diaDAO;
    @Autowired
    private CotanieveDAO cotanieveDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @RequestMapping({"/usuario.html"})
    public ModelAndView listado(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        try {
            List<Usuario> usuarios=usuarioDAO.findAll();
            boolean admin = false;
            boolean user = false;
            
            String nom=request.getParameter("txtnom");
            String correo=request.getParameter("txtcorreo");
            for (Usuario usuario : usuarios) {
                if (usuario.getNombres().equals(nom) && usuario.getCorreo().equals(correo)){
                    usuPass = usuario;
                    user = true;
                }
                if (nom.equals("admin") && correo.equals("admin@admin.com")){
                    usuPass = usuario;
                    admin = true;
                }
            }
            
            viewName = "login";
            
            if (admin) {
               viewName = "usuarioListado";               
            }else if (user){
                viewName = "diaListado";
                List<Dia> dias=diaDAO.findAll();
                List<Cotanieve> cotanieves=cotanieveDAO.findAll();
                model.put("dias",dias);
                model.put("cotanieves",cotanieves);
            }
            model.put("usuarios",usuarios);
            model.put("usuPass", usuPass);
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/usuarioListado.html"})
    public ModelAndView usuListado(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            List<Usuario> usuarios=usuarioDAO.findAll();
            viewName = "usuarioListado";
            model.put("usuPass", usuPass);
            model.put("usuarios",usuarios);
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/usuario/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            Usuario usuario = usuarioDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("usuario", usuario);
            model.put("usuPass", usuPass);
            viewName = "usuario";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/readForUpdate"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }

            Usuario usuario = usuarioDAO.get(id);
            if (usuario == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el usuario con id=" + id));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("usuario", usuario);
            model.put("usuPass", usuPass);
            viewName = "usuario";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/readForDelete"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }

            Usuario usuario = usuarioDAO.get(id);
            if (usuario == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el usuario con id=" + id));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("usuario", usuario);
            model.put("usuPass", usuPass);
            viewName = "usuario";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Usuario usuario = null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuario = usuarioDAO.create();

            usuario.setNombres(request.getParameter("txtnom"));
            usuario.setCorreo(request.getParameter("txtcorreo"));
            
            usuarioDAO.saveOrUpdate(usuario);
            model.put("usuPass", idUsu);
            viewName = "redirect:/usuarioListado.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (usuario!=null) {
                usuario.setId(0);
            }
            model.put("usuario", usuario);
            model.put("formOperation", FormOperation.Insert);
            viewName = "usuario";
        }



        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Usuario usuario = null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            int id;
            try {
                id = Integer.parseInt(request.getParameter("Id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            usuario = usuarioDAO.get(id);
            if (usuario == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el usuario."));
            }
            usuario.setNombres(request.getParameter("txtnom"));
            usuario.setCorreo(request.getParameter("txtcorreo"));
            model.put("usuPass", idUsu);
            usuarioDAO.saveOrUpdate(usuario);

            viewName = "redirect:/usuarioListado.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("usuario", usuario);
            model.put("formOperation", FormOperation.Update);
            viewName = "usuario";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        Usuario usuario=null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            int id;
            try {
                id = Integer.parseInt(request.getParameter("Id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            usuario = usuarioDAO.get(id);
            if (usuario == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el usuario a borrar"));
            }

            usuarioDAO.delete(id);
            model.put("usuPass", idUsu);
            viewName = "redirect:/usuarioListado.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("usuario", usuario);
            model.put("formOperation", FormOperation.Delete);
            viewName = "usuario";
        }

        return new ModelAndView(viewName, model);
    }

}
