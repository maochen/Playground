import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CallingExample {

    public static List<User> query(Session sess) {
        Query query = sess.createQuery("from User where email = :code ");
        query.setParameter("code", "asdf@asdf.com");
        return query.list();
    }

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();

        SessionFactory sf = cfg.buildSessionFactory();
        Session sess = sf.openSession();
        Transaction tx = sess.beginTransaction();

        // ADD
        String time = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(java.util.Calendar.getInstance().getTime());
        User u = new User();
        u.setId(1);
        u.setUsername(time);
        u.setPassword("123");
        u.setEmail("asdf@asdf.com");
        sess.save(u);
        tx.commit();
        // -------------------

        // UPDATE
        tx = sess.beginTransaction();
        Query updateQuery = sess.createQuery("update User set username = :usrName" + " where id = :usrID");
        updateQuery.setParameter("usrName", "------UPDATEDDDDDD");
        updateQuery.setParameter("usrID", 1);
        int result = updateQuery.executeUpdate();
        System.out.println("Updated Rows: " + result);

        tx.commit();
        // ------------

        for (User s : query(sess))
            System.out.println(s);

        // -------------

        // DELETE
        tx = sess.beginTransaction();
        Query delQuery = sess.createQuery("delete User where password = :usrPassword");
        delQuery.setParameter("usrPassword", "123");
        int delResult = delQuery.executeUpdate();
        System.out.println("Deleted Rows: " + delResult);

        tx.commit();
        // --------------

        sf.close();

    }
}
