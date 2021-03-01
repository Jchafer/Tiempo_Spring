/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import bda.dao.BussinessException;
import bda.dao.BussinessMessage;
import project.model.Dia;
import project.persistencia.dao.DiaDAO;
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
import project.model.Usuario;
import project.persistencia.dao.CotanieveDAO;
import project.persistencia.dao.UsuarioDAO;

@Controller
public class DiaController {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private DiaDAO diaDAO;
    @Autowired
    private CotanieveDAO cotanieveDAO;
    
    @RequestMapping({"/dia.html"})
    public ModelAndView listado(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            List<Dia> dias=diaDAO.findAll();
            List<Cotanieve> cotanieves=cotanieveDAO.findAll();
            model.put("dias",dias);
            model.put("cotanieves",cotanieves);
            model.put("usuPass", usuPass);
            viewName = "diaListado";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/dia/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            Dia dia = diaDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("dia", dia);
            model.put("usuPass", usuPass);
            viewName = "dia";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/dia/readForUpdate"})
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

            Dia dia = diaDAO.get(id);
            if (dia == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el dia con id=" + id));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("dia", dia);
            model.put("usuPass", usuPass);
            viewName = "dia";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/dia/readForDelete"})
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

            Dia dia = diaDAO.get(id);
            if (dia == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el dia con id=" + id));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("dia", dia);
            model.put("usuPass", usuPass);
            viewName = "dia";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/dia/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;            
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Dia dia = null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            dia = diaDAO.create();

            dia.setPoblacion(request.getParameter("poblacion"));
            dia.setFechaDesdeString(request.getParameter("fecha"));
            dia.setTempMaxima(request.getParameter("tempMaxima"));
            dia.setTempMinima(request.getParameter("tempMinima"));

            diaDAO.saveOrUpdate(dia);
            model.put("usuPass", idUsu);
            viewName = "redirect:/dia.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (dia!=null) {
                dia.setIdDia(0);
            }
            model.put("dia", dia);
            model.put("formOperation", FormOperation.Insert);
            viewName = "dia";
        }



        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/dia/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Dia dia = null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            int id;
            try {
                id = Integer.parseInt(request.getParameter("idDia"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            dia = diaDAO.get(id);
            if (dia == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el dia."));
            }
            dia.setPoblacion(request.getParameter("poblacion"));
            dia.setFechaDesdeString(request.getParameter("fecha"));
            dia.setTempMaxima(request.getParameter("tempMaxima"));
            dia.setTempMinima(request.getParameter("tempMinima"));

            diaDAO.saveOrUpdate(dia);
            model.put("usuPass", idUsu);
            viewName = "redirect:/dia.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("dia", dia);
            model.put("formOperation", FormOperation.Update);
            viewName = "dia";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/dia/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        Dia dia=null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            int id;
            try {
                id = Integer.parseInt(request.getParameter("idDia"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            dia = diaDAO.get(id);
            if (dia == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el dia a borrar"));
            }

            diaDAO.delete(id);
            model.put("usuPass", idUsu);
            viewName = "redirect:/dia.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("dia", dia);
            model.put("formOperation", FormOperation.Delete);
            viewName = "dia";
        }

        return new ModelAndView(viewName, model);
    }

}
