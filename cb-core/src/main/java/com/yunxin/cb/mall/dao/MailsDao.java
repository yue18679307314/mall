package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Mails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MailsDao extends JpaRepository<Mails, Integer>, JpaSpecificationExecutor<Mails> {

}
