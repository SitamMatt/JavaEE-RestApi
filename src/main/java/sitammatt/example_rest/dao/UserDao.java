package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class UserDao extends BaseDao<User>{

    @PersistenceContext(unitName = "Tasks", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDao() {
        entityClass = User.class;
    }
}
