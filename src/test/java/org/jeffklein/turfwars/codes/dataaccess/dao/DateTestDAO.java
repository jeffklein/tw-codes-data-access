package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.DateTest;

/**
 * Created by jklein on 5/4/15.
 */
public interface DateTestDAO {
    public Integer insertDateTest(DateTest dateTest);
    public DateTest findById(Integer id);
}
