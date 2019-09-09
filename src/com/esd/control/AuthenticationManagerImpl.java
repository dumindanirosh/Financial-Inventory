package com.esd.control;

import com.esd.pojo.Login;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class AuthenticationManagerImpl implements AuthenticationManager {

    private EntityManagerFactory entityManagerFactory;
    private UserActivityImpl userActivityImpl;
    private EntityManager entityManager;

    public AuthenticationManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();

    }

    public Login login(String userName, String password) throws RuntimeException {


        Query q = entityManager.createQuery("SELECT r FROM Login r WHERE r.username = :username AND r.password = :password");
        q.setParameter("username", userName);
        q.setParameter("password", password);


        try {

            Login login = (Login) q.getSingleResult();


            return login;

        } catch (NoResultException e) {

            return null;
        }


    }

    public String addUser(Login login) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Login loginTmp = entityManager.find(Login.class, login.getUsername());

                if (loginTmp == null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.persist(login);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.USER_ADDED_SUCCESSFULLY;

                } else {

                    return Constants.USER_EXIST;

                }

            } else {

                Log4jUtil.logErrorMessage("public String addUser(Login login)  entity manager object null");
                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addUser(Login login) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String changePassword(Login login) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Login loginTmp = entityManager.find(Login.class, login.getUsername());

                if (loginTmp != null) {
                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    loginTmp.setPassword(login.getPassword());

                    entityManager.merge(loginTmp);

                    entityTransaction.commit();

                    return Constants.PASSWORD_CHANGED;

                } else {
                    return Constants.ERROR;
                }

            } else {
                Log4jUtil.logErrorMessage("public String changePassword(Login login)  entity manager object null");
                return Constants.ERROR;
            }


        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("    public String changePassword(Login login) " + ex);

            if (entityTransaction != null) {
                entityTransaction.rollback();

            }
            return Constants.ERROR;
        }

    }

    public List<Login> getusers() {


        try {

            if (entityManager != null) {

                Query q = entityManager.createQuery("SELECT l FROM Login l");


                return q.getResultList();
            } else {
                Log4jUtil.logErrorMessage("public List<Stores> getusers() entity manager object null ");
                return null;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<Stores> getusers() " + ex);

            return null;
        }

    }

    public String deleteUser(String username) {


        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Login loginTmp = entityManager.find(Login.class, username);

                if (loginTmp != null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.remove(loginTmp);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.USER_DELETED_SUCCESSFULLY;

                } else {

                    return Constants.ERROR;

                }

            } else {

                Log4jUtil.logErrorMessage("public String deleteUser(String username)  entity manager object null");
                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String deleteUser(String username) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String deleteUser2(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
