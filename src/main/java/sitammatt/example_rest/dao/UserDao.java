package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserDao extends BaseDao<User>{

    @Inject
    public UserDao(EntityManager em) {
        super(em, User.class);
    }
}
