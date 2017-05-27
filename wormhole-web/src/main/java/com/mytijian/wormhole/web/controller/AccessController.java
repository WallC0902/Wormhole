package com.mytijian.wormhole.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mytijian.wormhole.dao.constant.StatusEnum;
import com.mytijian.wormhole.dao.model.Access;
import com.mytijian.wormhole.service.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by wangchangpeng on 2017/5/8.
 */
@RestController
@RequestMapping("/wormhole")
public class AccessController {

//    @Autowired
//    private AccessService accessService;

    @RequestMapping(value = "/access/insert", method = RequestMethod.GET)
    public int insert(){
        Access access = new Access();
        access.setCompanyName("测试公司22");
        access.setCompanyWebsite("www.test22.com");
        access.setEmail("test22@test.com");
        access.setMtId(122);
        access.setStatus(StatusEnum.NORMAL);
        access.setPrivateKey("ASDQWE123JH1L234HK23J4H2K34K12J3H");
        access.setCreateDate(new Date());
        access.setUpdateDate(new Date());
        access.setCreateBy("123123");
//        accessService.insert(access);
        access.insert();
        return access.getId();
    }

    @RequestMapping("/access/{id}")
    public Page<Access> get(@PathVariable Integer id){
        if (StringUtils.isEmpty(id)) {
            return null;
        }
//        Access access = new Access();
//        Access access2 = access.selectById(id);
////        Access access = new Access().selectById(id);
////        access.setId(id);
////        access.selectById();
//        access2.setCompanyName("修改后的公司22");
//        access2.updateById();
        Access access3 = new Access();
        Page<Access> accessList = access3.selectPage(new Page<Access>(1, 10), new EntityWrapper<>());
        return accessList;
    }
}
