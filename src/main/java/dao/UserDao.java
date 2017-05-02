package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import util.HibernateUtil;

/**
 * Created by lucode on 2017/5/2.
 */
public class UserDao {
    @Test
    public void insert() throws Exception {
        // 对象
        User user = new User();
        user.setUsername("高玉良");
        user.setPassword("qweqweqwe");

        // 获取加载配置文件的管理类对象
        Configuration config = new Configuration();
        config.configure();  // 默认加载src/hibenrate.cfg.xml文件
        // 创建session的工厂对象
        SessionFactory sf = config.buildSessionFactory();
        // 创建session (代表一个会话，与数据库连接的会话)
        Session session = sf.openSession();
        // 开启事务
        Transaction tx = session.beginTransaction();
        //保存-数据库
        session.save(user);
        // 提交事务
        tx.commit();
        // 关闭
        session.close();
        sf.close();
    }

    @Test
    public void insert2() throws Exception{
        // 打开线程安全的Session对象
        Session session = HibernateUtil.currentSession();
        // 打开事务
        Transaction tx = session.beginTransaction();
        // 对象
        User user = new User();
        user.setUsername("沙瑞金");
        user.setPassword("qweqweqwe");
        session.save(user);

        tx.commit();
        //通过工具类关闭Session
        HibernateUtil.closeSession();
        HibernateUtil.sessionFactory.close();


    }

}
