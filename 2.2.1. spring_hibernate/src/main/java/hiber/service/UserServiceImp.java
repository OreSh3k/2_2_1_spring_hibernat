package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }


   @Transactional
   @Override
   public String getUserByCar(String model, int series) {
      return Optional.ofNullable(userDao.getUserByCar(model, series))
              .filter(users -> !users.isEmpty())
              .map(users -> {StringBuilder sb = new StringBuilder();
                              for(User user : users) {
                                 sb.append(user.toString()).append("\n");
                              }
                              return sb.toString();
              }).orElse("Пользователь с машиной " + model + " и номером " + series + " не найден.");
   }
}
