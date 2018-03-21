package com.pengkt.common.request;

import java.io.Serializable;
import java.util.Date;

public class BaseRequest implements Serializable{

    private Date currentTime;

    private String token;

    private String userId;


}
