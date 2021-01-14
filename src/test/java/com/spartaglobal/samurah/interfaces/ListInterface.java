package com.spartaglobal.samurah.interfaces;

import com.spartaglobal.samurah.dtos.SwapiObject;

public interface ListInterface {
    SwapiObject next() throws Exception;
    boolean hasNext();
    SwapiObject previous() throws Exception;
    boolean hasPrevious();
}
