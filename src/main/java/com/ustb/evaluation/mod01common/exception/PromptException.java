package com.ustb.evaluation.mod01common.exception;

/**
 * 表示有程序员明确抛出的异常，提示信息非常有用
 */
public class PromptException extends RuntimeException{
    public PromptException(String msg)
    {
        super(msg);
    }
}
