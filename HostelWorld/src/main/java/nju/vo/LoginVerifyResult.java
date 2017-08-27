package nju.vo;

import nju.util.VerifyResult;

/**
 * Created by Srf on 2017/3/11
 */
public class LoginVerifyResult {

    private int id;
    private VerifyResult verifyResult;

    public LoginVerifyResult(int id, VerifyResult verifyResult){
        this.id = id;
        this.verifyResult = verifyResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VerifyResult getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(VerifyResult verifyResult) {
        this.verifyResult = verifyResult;
    }
}
