package com.mycompany.empresa;

import java.util.List;

public interface MpDao {
    void add(Mp materiaPrima);
    void update(Mp materiaPrima);
    void delete(Integer id);
    Mp getById(Integer id);
    List<Mp> getAll();
}
