/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import bda.dao.BussinessException;
import bda.dao.BussinessMessage;
import project.model.Cotanieve;
import project.persistencia.dao.CotanieveDAO;
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
import project.model.Usuario;
import project.persistencia.dao.DiaDAO;
import project.persistencia.dao.UsuarioDAO;

@Controller
public class CotanieveController {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private CotanieveDAO cotanieveDAO;
    @Autowired
    private DiaDAO diaDAO;
    
    @RequestMapping({"/cota.html"})
    public ModelAndView listado(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Usuario usuPass = new Usuario();
        int idUsu;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            usuPass = usuarioDAO.get(idUsu);
            List<Cotanieve> cotanieves=cotanieveDAO.findAll();
            model.put("cotanieves",cotanieves);
            model.put("usuPass", usuPass);
            viewName = "cotanieveListado";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/cotanieve/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
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

            Cotanieve cotanieve = cotanieveDAO.create();
            
            model.put("formOperation", FormOperation.Insert);
            model.put("cotanieve", cotanieve);
            model.put("idPass", id);
            model.put("usuPass", usuPass);
            viewName = "cotanieve";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cotanieve/readForUpdate"})
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

            Cotanieve cotanieve = cotanieveDAO.get(id);
            if (cotanieve == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe la cotanieve con id=" + id));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("cotanieve", cotanieve);
            model.put("idPass", cotanieve.getDia().getIdDia());
            model.put("usuPass", usuPass);
            viewName = "cotanieve";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cotanieve/readForDelete"})
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

            Cotanieve cotanieve = cotanieveDAO.get(id);
            if (cotanieve == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe la cotanieve con id=" + id));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("cotanieve", cotanieve);
            model.put("idPass", cotanieve.getDia().getIdDia());
            model.put("usuPass", usuPass);
            viewName = "cotanieve";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cotanieve/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Cotanieve cotanieve = null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            cotanieve = cotanieveDAO.create();

            cotanieve.setPeriodo(request.getParameter("periodo"));
            cotanieve.setDia(diaDAO.get(Integer.parseInt(request.getParameter("dia"))));

            cotanieveDAO.saveOrUpdate(cotanieve);
            model.put("usuPass", idUsu);
            viewName = "redirect:/cota.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (cotanieve!=null) {
                cotanieve.setIdCotaNieve(0);
            }
            model.put("cotanieve", cotanieve);
            model.put("formOperation", FormOperation.Insert);
            viewName = "cotanieve";
        }



        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cotanieve/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Cotanieve cotanieve = null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            int id;
            try {
                id = Integer.parseInt(request.getParameter("idCotaNieve"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            cotanieve = cotanieveDAO.get(id);
            if (cotanieve == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe la cotanieve."));
            }
            cotanieve.setPeriodo(request.getParameter("periodo"));
            cotanieve.setDia(diaDAO.get(Integer.parseInt(request.getParameter("dia"))));

            cotanieveDAO.saveOrUpdate(cotanieve);
            model.put("usuPass", idUsu);
            viewName = "redirect:/cota.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("cotanieve", cotanieve);
            model.put("formOperation", FormOperation.Update);
            viewName = "cotanieve";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cotanieve/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        int idUsu;
        Cotanieve cotanieve=null;
        try {
            idUsu = Integer.parseInt(request.getParameter("usuPass"));
            int id;
            try {
                id = Integer.parseInt(request.getParameter("idCotaNieve"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            cotanieve = cotanieveDAO.get(id);
            if (cotanieve == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe la cotanieve a borrar"));
            }

            cotanieveDAO.delete(id);
            model.put("usuPass", idUsu);
            viewName = "redirect:/cota.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("cotanieve", cotanieve);
            model.put("formOperation", FormOperation.Delete);
            viewName = "cotanieve";
        }

        return new ModelAndView(viewName, model);
    }

}
