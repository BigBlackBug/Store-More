package qbix.sm.server.aspectj.loggers;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
@Aspect
public class UserDaoLogger {

    static final Logger log=Logger.getLogger("UserDaoLogger");

    @Around("execution(* qbix.sm.server.dao.UserDao.getByName(..))")
    public Object logGetUserByName(ProceedingJoinPoint pjp) throws Throwable {
        String message="Searching user "+pjp.getArgs()[0];
        Object returN=null;
        try{ returN = pjp.proceed(); }
        catch(Exception ex){
            log.warning("UserDao.getByName throws "+ex);
            throw ex;
        }
        if(returN==null) message+=" => no user with this name";
        else message+=" => SUCCESS";
        log.info(message);
        return returN;
    }

    @Around("execution(* qbix.sm.server.dao.UserDao.getById(..))")
    public Object logGetById(ProceedingJoinPoint pjp) throws Throwable {
        String message="Searching user witn id="+pjp.getArgs()[0];
        Object returN=null;
        try{ returN=pjp.proceed(); }
        catch(Exception ex){
            log.warning("UserDao.getById throws "+ex);
            throw ex;
        }
        if(returN==null) message+=" => no user with this id";
        else message+=" => SUCCESS : "+((User)returN).getName()+" found";
        log.info(message);
        return returN;
    }

    @Around("execution(* qbix.sm.server.dao.UserDao.add(..))")
    public Object logAddUser(ProceedingJoinPoint pjp) throws Throwable {
        String message="Adding user "+((User)pjp.getArgs()[0]).getName();
        Object returN=null;
        try{ returN=pjp.proceed(); }
        catch(Exception ex){
            log.warning("UserDao.add throws "+ex);
            throw ex;
        }
        message+=" => SUCCESS";
        log.info(message);
        return returN;
    }

    
}
