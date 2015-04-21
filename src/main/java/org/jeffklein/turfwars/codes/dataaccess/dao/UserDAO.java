package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Jeff
 * Date: 4/19/15
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {
    public Integer createUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User findByUsername(String username);

    public User findById(Integer id);

    public void associatePunchedTempCodeWithUser(TempCode code, User user);
}
