package com.exper.mysql.controller;

import com.exper.mysql.dao.TestDao;
import com.exper.mysql.model.TestRes;
import com.exper.mysql.model.req.TestReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 1E7753
 * @date 2022/8/13 13:39
 * @todo
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestDao testDao;

    @PostMapping("test")
    public String testDbMasterAndSlave(@RequestBody TestReq req) {
        //TestRes testRes = testDao.selectRes(req);
        testDao.insertRes(req);
        return "OK";
    }

    @PostMapping("testSharding")
    public String testSharding(@RequestBody TestReq req) {
        TestRes updateBefore = testDao.selectResInfo(req);
        testDao.hcwTest(req);
        TestRes updateAfter = testDao.selectResInfo(req);
        return "updateBefore=" + updateBefore.toString() + "=====》updateAfter=" + updateAfter.toString();
    }
}
