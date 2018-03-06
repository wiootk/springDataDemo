package com.jun.util;

import com.alibaba.fastjson.JSON;
import com.jun.entity.RecoverEntity;
import com.jun.util.MyRepo;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Administrator on 2017-09-22.
 */
//@Repository
@Transactional
public class MyRepoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepo<T, ID> {
    private final EntityManager entityManager;
    public MyRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
    public MyRepoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }
    String insertSql = "insert into recover(deleteOperorId, deleteOperorName, deleteTime, entity, entityName,recoverTime,id) values (?, ?, ?, ?, ?, ?,?)";
    String updateSql = "update RecoverEntity as r set  r.recoberOperorName=:operorName,r.recoverOperorId=:operorId,r.recoverTime=:rtime  where r.id=:id";
    String selectSql = "select o from RecoverEntity o where o.id=:id";
//    @Autowired
//    private RecoverRepo recoverRepo;

    public void logicDelete(ID id) {
        T t = this.findOne(id);
        this.logicDelete(t);
    }
    //@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void logicDelete(T t) {
//        Query query = entityManager.createNativeQuery(insertSql, RecoverEntity.class);
//        query.setParameter(1, recover.getEntityName());
//        query.executeUpdate();
        RecoverEntity recover = new RecoverEntity();
        recover.setDeleteTime(System.currentTimeMillis());
        recover.setEntityName(t.getClass().getName());
        recover.setEntity(JSON.toJSONString(t));
        entityManager.persist(recover);
        entityManager.flush();
//      entityManager.merge(entity); update
        this.delete(t);
    }
    @Transactional
    public T recover(ID id) throws ClassNotFoundException {
//        Query query = entityManager.createQuery(selectSql, RecoverEntity.class);
//        query.setParameter("id", id);
//        RecoverEntity recover = (RecoverEntity) query.getResultList().get(0);
////        Assert.isNull(recover, ProjectErrorCodes.FAIL, "包车牌不存在！！！");
//        recover.setRecoverTime(System.currentTimeMillis());
//        Query query2 = entityManager.createQuery(updateSql);
//        query2.setParameter("id", recover.getId());
//        query2.executeUpdate();
        RecoverEntity recover = entityManager.find(RecoverEntity.class,id);
        recover.setRecoverTime(System.currentTimeMillis());
        entityManager.merge(recover);
//        Class <T>  entityClass  =  (Class <T> ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
        Class clazz = Class.forName(recover.getEntityName());
        T t = (T) JSON.parseObject(recover.getEntity(), clazz);
//        this.save(t);
        entityManager.persist(t);
        entityManager.flush();
        return t;
    }
}
