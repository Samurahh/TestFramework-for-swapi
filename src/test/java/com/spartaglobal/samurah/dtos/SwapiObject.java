package com.spartaglobal.samurah.dtos;

import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public abstract class SwapiObject {
    public SwapiObject(){}

    protected abstract void setAPI(API api);

    protected abstract void setUrl(String url);

    public abstract URL getUrl();

}
