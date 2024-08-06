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
    Result insert(Lmessage lmessage, Subject currentUser);
    List<Lmessage> getAll();
}
