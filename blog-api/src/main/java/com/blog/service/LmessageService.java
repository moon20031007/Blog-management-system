package com.blog.service;

import com.blog.pojo.Lmessage;
import com.blog.util.result.Result;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * ClassName: LmessageService
 * Package:
 * Description:
 *
 * @Author: Yixiao
 * @Create: - 15:18
 * @Version: v1.0
 */
public interface LmessageService {

    void insert(Lmessage lmessage) throws Exception;

    List<Lmessage> getAll();
}
