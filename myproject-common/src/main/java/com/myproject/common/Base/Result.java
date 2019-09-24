/**
 * Software License Declaration.
 * <p>
 * wandaph.com, Co,. Ltd.
 * Copyright ? 2017 All Rights Reserved.
 * <p>
 * Copyright Notice
 * This documents is provided to wandaph contracting agent or authorized programmer only.
 * This source code is written and edited by wandaph Co,.Ltd Inc specially for financial
 * business contracting agent or authorized cooperative company, in order to help them to
 * install, programme or central control in certain myproject by themselves independently.
 * <p>
 * Disclaimer
 * If this source code is needed by the one neither contracting agent nor authorized programmer
 * during the use of the code, should contact to wandaph Co,. Ltd Inc, and get the confirmation
 * and agreement of three departments managers  - Research Department, Marketing Department and
 * Production Department.Otherwise wandaph will charge the fee according to the programme itself.
 * <p>
 * Any one,including contracting agent and authorized programmer,cannot share this code to
 * the third party without the agreement of wandaph. If Any problem cannot be solved in the
 * procedure of programming should be feedback to wandaph Co,. Ltd Inc in time, Thank you!
 */
package com.myproject.common.Base;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

//响应格式
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public static <T> Result success(T t){
        return new Result<>(Status.SUCCESS.code, Status.SUCCESS.message, t);
    }

    public static <T> Result success(T t, String message){
        return new Result<>(Status.SUCCESS.code, message, t);
    }

    public static <T> Result exception(T t){
        return new Result<>(Status.EXCEPTION.code, Status.EXCEPTION.message, t);
    }
    public static <T> Result exception(T t, String message){
        return new Result<>(Status.EXCEPTION.code, message, t);
    }
    public static <T> Result unLogin(T t){
        return new Result<>(Status.UNLOGIN.code, Status.UNLOGIN.message, t);
    }
    public static <T> Result invalid(String message, T t){
        return new Result<>(Status.INVALID.code, StringUtils.isBlank(message)? Status.INVALID.message : message, t);
    }

    enum Status {
        /**
         * 成功
         */
        SUCCESS(0,"SUCCESS"),
        EXCEPTION(1,"EXCEPTION"),
        UNLOGIN(2,"UNLOGIN"),
        INVALID(3,"INVALID");
        private Integer code;
        private String message;

        Status(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}