package com.erick.exception;

/**
 * 当尝试删除一个仍然有学生关联的班级时抛出
 */
public class ClassHasStudentsException extends RuntimeException {

    public ClassHasStudentsException(String message) {
        super(message);
    }
}
