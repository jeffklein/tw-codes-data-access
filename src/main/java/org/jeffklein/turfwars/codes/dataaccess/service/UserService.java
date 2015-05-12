package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.User;

import java.util.List;

/**
 * Created by jklein on 5/7/15.
 */
public interface UserService {
    public User login(String username, String password);

    public Integer createUser(User user);

    public void updateUser(User user);

    public void punchTempCode(User user, TempCode tempCode);

    public void punchTempCodeBatch(User user, List<TempCode> tempCodeBatch);
}
