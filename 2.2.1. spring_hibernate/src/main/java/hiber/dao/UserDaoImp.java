package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getOwner(String model, int series) {
      String hql = "SELECT car.owner FROM Car car WHERE car.series = :series AND car.model = :model";
      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery(hql);
      query.setParameter("series", series);
      query.setParameter("model", model);
      List<User> users = query.getResultList();
      if (!users.isEmpty() && users.size() == 1) {
         return users.get(0);
      }
      return null;
   }

}
