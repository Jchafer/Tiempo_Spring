package project.persistencia.dao.impl;

import project.persistencia.dao.UsuarioDAO;
import project.model.Usuario;
import bda.dao.impl.GenericDAOImplHibernate;

public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario,Integer> implements  UsuarioDAO {

}
